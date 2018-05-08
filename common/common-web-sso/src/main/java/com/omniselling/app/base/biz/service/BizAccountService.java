package com.omniselling.app.base.biz.service;

import java.util.List;

import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.ms.core.model.Account;
import com.omniselling.ms.core.model.AccountCond;

/**
 * 账号服务
 *
 */
public interface BizAccountService
{
    /**
     * 批量激活账户
     * 
     * @param accountIds
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> activeAccounts(List<Long> accountIds, BaseOperatorInfo operatorInfo);

    /**
     * 创建账户
     * 
     * @param account
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> createAccount(Account account, BaseOperatorInfo operatorInfo);

    /**
     * 更新账户
     * 
     * @param account
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> updateAccount(Account account, BaseOperatorInfo operatorInfo);

    /**
     * 禁用账户
     * 
     * @param accountIds
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> disableAccounts(List<Long> accountIds, BaseOperatorInfo operatorInfo);

    /**
     * 通过id获取账户
     * 
     * @param accountId
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Account> getAccountById(Long accountId, BaseOperatorInfo operatorInfo);
    
    /**
     * 通过loginId获取账户
     * @param loginId
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Account> getAccountByLoginId(String loginId, BaseOperatorInfo operatorInfo);

    /**
     * 根据指定的id获取父账户信息
     * 
     * @param accountId
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Account> getParentAccount(Long accountId, BaseOperatorInfo operatorInfo);

    /**
     * 根据指定的id获取子账户信息
     * 
     * @param accountId
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<Account>> listSubAccount(Long accountId, BaseOperatorInfo operatorInfo);

    /**
     * 根据条件查询账户列表
     * 
     * @param accCond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<Account>> listAccounts(AccountCond accCond, BaseOperatorInfo operatorInfo);

    /**
     * 查询账户黑名名单状态
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<BizEnumSpec>> listResourcePermitType(BaseOperatorInfo operatorInfo);
    
    /**
     * 查询账户状态
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<BizEnumSpec>> listAccountStatus(BaseOperatorInfo operatorInfo);

}
