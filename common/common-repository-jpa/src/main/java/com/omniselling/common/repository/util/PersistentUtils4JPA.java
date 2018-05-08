package com.omniselling.common.repository.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BizBaseModel;

/**
 * 
 * @author deer
 *
 */
public class PersistentUtils4JPA
{

    /**
     * @param RootNode
     * @param isAdd
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void resolvAllModel(BizBaseModel rootNode, boolean isAdd, BaseOperatorInfo o)
    {
        try
        {
            ModelOperType type = ModelOperType.Update;

            if (isAdd && rootNode.getId() == null)
            {// 新增
                type = ModelOperType.Create;
            }
            constructModel(rootNode, o, type);
            iteratorTreeDillModel(rootNode, type, o);

        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 没有把根节点添加进去
     * 
     * @param beanLinkedList
     * @param node
     * @param isAdd（区分
     *            新增、非新增（修改、删除））
     * @param stack
     *            存储 带优先级注解的bean
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void iteratorTreeDillModel(BizBaseModel node, ModelOperType type, BaseOperatorInfo o) throws Exception
    {
        Class<?> cls = node.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields)
        {
            field.setAccessible(true);
            String fieldGetName = parGetName(field.getName());
            if (!checkGetMet(methods, fieldGetName))
            {
                continue;
            }
            Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});

            // 判断是否需要递归赋值
            if (!checkRecursion(field))
            {
                continue;
            }

            if (isBizBaseModel(fieldGetMet.invoke(node, new Object[] {})))
            {// 单一属性
                BizBaseModel model = (BizBaseModel) fieldGetMet.invoke(node, new Object[] {});

                constructModel(model, o, type);
                iteratorTreeDillModel(model, type, o);
            }
            else if (field.getType().getName().equals("java.util.List"))
            {
                // 列表，取值
                List<BizBaseModel> nodeList = (List<BizBaseModel>) fieldGetMet.invoke(node, new Object[] {});
                if (nodeList == null || nodeList.size() < 1)
                {
                    continue;
                }
                for (BizBaseModel bm : nodeList)
                {

                    constructModel(bm, o, type);
                    iteratorTreeDillModel(bm, type, o);
                }

            }
        }
    }

    /**
     * 判断是否需要递归
     */
    private static boolean checkRecursion(Field field)
    {
        boolean need = true;

        // 注释优先级的bean必须非空
        CascadeType[] cascadeType = null;
        if (field.isAnnotationPresent(ManyToOne.class))
        {
            cascadeType = field.getAnnotation(ManyToOne.class).cascade();
        }
        if (field.isAnnotationPresent(ManyToMany.class))
        {
            cascadeType = field.getAnnotation(ManyToMany.class).cascade();
        }
        if (field.isAnnotationPresent(OneToMany.class))
        {
            cascadeType = field.getAnnotation(OneToMany.class).cascade();
        }

        if (field.isAnnotationPresent(OneToOne.class))
        {
            cascadeType = field.getAnnotation(OneToOne.class).cascade();
        }

        if (cascadeType != null && cascadeType.length > 0)
        {
            List<CascadeType> list = Arrays.asList(cascadeType);
            if (!list.contains(CascadeType.ALL) && !list.contains(CascadeType.MERGE)
                    && !list.contains(CascadeType.PERSIST))
            {
                need = false;
            }
        }
        if (cascadeType == null || cascadeType.length == 0)
        {
            need = false;
        }
        return need;
    }

    /**
     * 是否为业务model
     * 
     * @param cls
     * @return
     */
    private static boolean isBizBaseModel(Object o)
    {
        return o instanceof BizBaseModel;
    }

    /**
     * 判断是否存在某属性的 get方法
     * 
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    private static boolean checkGetMet(Method[] methods, String fieldGetMet)
    {
        for (Method met : methods)
        {
            if (fieldGetMet.equals(met.getName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 拼接某属性的 get方法
     * 
     * @param fieldName
     * @return String
     */
    private static String parGetName(String fieldName)
    {
        if (null == fieldName || "".equals(fieldName))
        {
            return null;
        }
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * 本来放到common的，先放这里
     * 
     * @param model
     * @param o
     * @param type
     */
    public static void constructModel(BizBaseModel<Long> model, BaseOperatorInfo o, ModelOperType type)
    {

        switch (type)
        {
        case Create:
        case Update:
            if (model.getCreatedBy() == null)
            {
                model.setCreatedBy(o.getOperatorId());
            }
            if (model.getCreatedDate() == null)
            {
                model.setCreatedDate(new Date());
            }
            if (model.getModifiedBy() == null)
            {
                model.setModifiedBy(o.getOperatorId());
            }
            if (model.getModifiedDate() == null)
            {
                model.setModifiedDate(new Date());
            }
            break;
        default:
            break;
        }
    }
}
