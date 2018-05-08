package com.omniselling.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 公共排序，分页 model
 */
public class BasePageInfo implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -1188267678110858874L;

    /**
     * 降序
     */
    public static String DESC = "DESC";

    /**
     * 升序
     */
    public static String ASC = "ASC";

    /**
     * 每页记录数
     */
    private Integer rowsPerPage;

    /**
     * 查找记录的偏移量
     */
    private Integer offset;

    /**
     * 排序规则 格式："字段名 ASC" 或者 "字段名 DESC"
     */
    private List<String> orderColumns;

    /**
     * 增加排序方式
     * 
     * @param columnName
     * @param orderBy
     */
    public void addOrderColumn(String columnName, String orderBy)
    {
        if (orderColumns == null)
        {
            orderColumns = new ArrayList<String>();
        }

        if (columnName != null)
        {
            // 指定降序
            if (DESC.equalsIgnoreCase(orderBy))
            {
                orderColumns.add(String.format("%s %s", columnName, DESC));
            }
            // 没指定排序方式或者指定升序
            else
            {
                orderColumns.add(String.format("%s %s", columnName, ASC));
            }
        }
    }

    /**
     * 排序字符串（针对数据库order by语法）
     * 
     * @return
     */
    public String getOrderByString()
    {
        StringBuilder stringBuilder = new StringBuilder("");
        if (orderColumns == null || orderColumns.isEmpty())
        {
            return stringBuilder.toString();
        }

        for (String column : orderColumns)
        {
            stringBuilder.append(column).append(",");
        }

        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    public void setOrderByString(String orderByString)
    {
        if (orderByString == null || orderByString.length() == 0)
            return;
        String[] items = orderByString.replaceAll(" +", " ").split(",");
        for (String s : items)
        {
            String[] t = s.split(" ");
            String order = t.length == 1 ? ASC : t[1];
            addOrderColumn(t[0], order);
        }
    }

    @JsonIgnore
    public Integer getPage()
    {

        if (offset == null || rowsPerPage == null)
        {
            return null;
        }

        if (rowsPerPage == 0)
        {
            return null;
        }

        return offset / rowsPerPage ;
    }

    public Integer getRowsPerPage()
    {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage)
    {
        this.rowsPerPage = rowsPerPage;
    }

    public Integer getOffset()
    {
        return offset;
    }

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

    public List<String> getOrderColumns()
    {
        return orderColumns;
    }

    public void setOrderColumns(List<String> orderColumns)
    {
        this.orderColumns = orderColumns;
    }

}
