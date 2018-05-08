package com.omniselling.common.dao;

import java.util.ArrayList;
import java.util.List;

public class LimitOffsetExample {

	final static LimitOffsetExample EMPTY = new LimitOffsetExample();

	protected Integer limit;
	protected Integer offset;

	public String getOrderByClause() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List getOredCriteria() {
		return new ArrayList();
	}

	public boolean isDistinct() {
		return false;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

}
