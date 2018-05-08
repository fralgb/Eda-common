package com.omniselling.common.parallet;

import java.util.Collection;
import java.util.function.Consumer;

public interface OmniParalletTaskHandler<T> {

	public OmniParalletTaskHandler<T> setDatas(Collection<T> datas);

	public OmniParalletTaskHandler<T> addTask(Consumer<T> task);

	public void excute();
}
