package com.omniselling.common.dao;

import java.util.List;
import java.util.Map;

public interface DbBaseService<T extends DbBaseModel<ID>, ID>
{

	Long count();

	T getById(ID id);

	List<T> list(Integer offset, Integer limit);

	List<T> list();

	Integer save(T record, Long operatorId);

	Integer delete(T record, Long operatorId);

	Integer deleteById(ID id, Long operatorId);

	List<T> listByProperty(Map<String, Object> condition, Integer offset, Integer limit, String orderByClause);

	Long count(Map<String, Object> condition);

	T getByProperty(Map<String, Object> condition);
}
