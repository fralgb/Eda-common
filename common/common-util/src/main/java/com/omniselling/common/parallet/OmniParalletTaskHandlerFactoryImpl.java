package com.omniselling.common.parallet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OmniParalletTaskHandlerFactoryImpl implements OmniParalletTaskHandlerFactory{

	public <T> OmniParalletTaskHandler<T> getHandler(Class<T> tclass) {
		return new OmniParalletTaskHandlerImpl<T>();
	}

	public <T> OmniParalletTaskHandler<T> getHandlerWithDatas(Collection<T> datas) {
		return (new OmniParalletTaskHandlerImpl<T>().setDatas(datas));
	}
	
	public <T> OmniParalletTaskHandler<T> getHandlerWithData(T data) {
		List<T> datas = new ArrayList<T>();
		datas.add(data);
		return (new OmniParalletTaskHandlerImpl<T>().setDatas(datas));
	}
	
}
