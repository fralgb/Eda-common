package com.omniselling.common.repository.jpa.sqlsupport.util;

import com.omniselling.common.repository.jpa.sqlsupport.JPASQLQueryFactory;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;

/**
 * 
 * @Description: JPASQLQuery查询工具类 
 * @author chenlong longchen@edayun.cn
 * @version 创建时间    2017年4月19日 下午4:05:09 
 *
 */
public class JPASQLQueryHelper
{
    
    /**
     * 
     * @Description: 根据查询统计查询结果总数
     * @author chenlong
     * @version 2017年4月19日 下午4:04:37 
     * @param queryFactory
     * @param query
     * @return
     *
     */
    public static long count(JPASQLQueryFactory queryFactory,JPASQLQuery<Tuple> query){
        long count = queryFactory.newQuery().select(SQLExpressions.countAll).from(ExpressionUtils.as(query, "co")).fetchOne();
        return count;
    }
}
