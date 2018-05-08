package com.omniselling.common.parallet;

import java.util.Collection;

public interface OmniParalletTaskHandlerFactory {

	public  <T> OmniParalletTaskHandler<T> getHandler(Class<T> tclass);

	public  <T> OmniParalletTaskHandler<T> getHandlerWithDatas(Collection<T> datas);

	public  <T> OmniParalletTaskHandler<T> getHandlerWithData(T data);
	
}
