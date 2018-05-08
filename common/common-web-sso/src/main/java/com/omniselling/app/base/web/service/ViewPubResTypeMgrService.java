package com.omniselling.app.base.web.service;

import java.util.List;

import com.omniselling.app.base.web.model.ViewResourceType;
import com.omniselling.app.base.web.model.ViewResourceTypeCond;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;

/**
 * 接口：公共资源类型
 * @author Administrator
 *
 */
public interface ViewPubResTypeMgrService
{
    /**
     * 根据条件查询资源类型列表
     * 
     * @param importRequest
     *            传入参数
     * @return 查询成功，返回 List<ViewResourceType> 集合信息，查询失败，返回 错误信息
     */
    public BaseResponse<List<ViewResourceType>> listTypesByCond(ViewResourceTypeCond importRequest,
            BaseOperatorInfo operatorInfo);
    /**
     * 根据条件查询资源类型列表
     * 
     * @param importRequest
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<ViewResourceType>> listTypes(ViewResourceTypeCond importRequest,
            BaseOperatorInfo operatorInfo);

    /**
     * 保存对资源类型所做的修改
     * 
     * @param importRequest
     *            保存信息
     * @return 保存成功，返回true；保存失败，返回false
     */
    public BaseResponse<Boolean> saveType(ViewResourceType importRequest, BaseOperatorInfo operatorInfo);

    /**
     * 删除公共资源类型信息
     * 
     * @param importRequest
     *            删除信息
     * @return 保存成功，返回true；保存失败，返回false
     */
    public BaseResponse<Boolean> deleteType(String importRequest, BaseOperatorInfo operatorInfo);

    /**
     * 激活当前资源类型
     * 
     * @param importRequest
     *            激活信息
     * @return 激活成功，返回true；激活失败，返回false
     */
    public BaseResponse<Boolean> activeType(String importRequest, BaseOperatorInfo operatorInfo);

    /**
     * 禁用当前资源类型
     * 
     * @param importRequest
     *            禁用信息
     * @return 禁用成功，返回true；禁用失败，返回false
     */
    public BaseResponse<Boolean> disableType(String importRequest, BaseOperatorInfo operatorInfo);

}
