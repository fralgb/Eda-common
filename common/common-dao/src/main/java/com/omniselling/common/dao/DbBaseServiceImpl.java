package com.omniselling.common.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.protocol.OmniException;

public abstract class DbBaseServiceImpl<T extends DbBaseModel<ID>, S extends LimitOffsetExample, ID> {

	public abstract BaseDAO<T, S, ID> getDAO();
	
	public abstract Class<T> getModelClass();

	@SuppressWarnings("unchecked")
	public Long count() {
		return getDAO().countByExample((S) LimitOffsetExample.EMPTY);
	}

	public T getById(ID id) {
		return getDAO().selectByPrimaryKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<T> list(Integer offset, Integer limit) {
		LimitOffsetExample example = new LimitOffsetExample();
		if (offset != null) {
			example.setOffset(offset);
		}
		if (limit != null) {
			example.setLimit(limit);
		}
		return getDAO().selectByExample((S) example);
	}

	@SuppressWarnings("unchecked")
	public List<T> list() {
		return getDAO().selectByExample((S) LimitOffsetExample.EMPTY);
	}

	public Integer save(T record, Long operatorId) {
		if (record == null)
			return -1;
		record.setModifiedDate(new Date());
		record.setModifiedBy(operatorId);
		if (record.getId() != null) {
			return getDAO().updateByPrimaryKey(record);
		} else {
			if(record.getCreatedBy() == null)
				record.setCreatedBy(operatorId);
			if(record.getCreatedDate() == null)
				record.setCreatedDate(new Date());
			return getDAO().insert(record);
		}
	}

	public Integer delete(T record, Long operatorId) {
		return getDAO().deleteByPrimaryKey(record.getId());
	}

	public Integer deleteById(ID id, Long operatorId) {
		return getDAO().deleteByPrimaryKey(id);
	}
	
	public List<T> listByProperty(Map<String, Object> condition,Integer offset,Integer limit ,String orderByClause) {
		try {
			return MyBatisExampleHelper.selectByMapCondition(getModelClass(), getDAO(), condition, offset, limit, orderByClause);
		} catch (Exception e) {
			e.printStackTrace();
			handleExcetion(e);
		}
		return null;
	}
	
	private void handleExcetion(Exception e) {
		if(e instanceof OmniException){
			throw (OmniException)e;
		}else  if(e instanceof InvocationTargetException){
			throw new OmniException(CommonErrorCode.DB_ERROR, ((InvocationTargetException) e).getTargetException());
		}
		throw new OmniException(CommonErrorCode.DB_ERROR, e);
	}

	public Long count(Map<String, Object> condition) {
		try {
			return MyBatisExampleHelper.countByMapCondition(getModelClass(), getDAO(), condition);
		} catch (Exception e) {
			e.printStackTrace();
			handleExcetion(e);
		}
		return null;
	}

	public T getByProperty(Map<String, Object> condition) {
		List<T> list = listByProperty(condition, 0 , 1, null);
		if(list != null && list.size() >0)
			return list.get(0);
		return null;
	}
}
