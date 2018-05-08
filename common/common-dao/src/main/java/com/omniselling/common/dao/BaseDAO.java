package com.omniselling.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDAO<T, S extends LimitOffsetExample, ID> {

	long countByExample(S example);

	int deleteByExample(S example);

	int deleteByPrimaryKey(ID id);

	int insert(T record);

	int insertSelective(T record);

	List<T> selectByExample(S example);

	T selectByPrimaryKey(ID id);

	int updateByExampleSelective(@Param("record") T record, @Param("example") S example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

}
