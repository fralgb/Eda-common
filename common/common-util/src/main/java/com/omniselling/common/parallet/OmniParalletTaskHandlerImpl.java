package com.omniselling.common.parallet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class OmniParalletTaskHandlerImpl<T> implements OmniParalletTaskHandler<T> {

	private Collection<T> datas;
	private List<Consumer<T>> taskList = new ArrayList<Consumer<T>>();

	public OmniParalletTaskHandlerImpl() {
	}

	public OmniParalletTaskHandlerImpl(Collection<T> datas) {
		this.datas = datas;
	}

	public OmniParalletTaskHandler<T> setDatas(Collection<T> datas) {
		this.datas = datas;
		return this;
	}

	public OmniParalletTaskHandler<T> addTask(Consumer<T> task) {
		this.taskList.add(task);
		return this;
	}

	public void excute() {
		beforeProduce(datas);
		try {
			datas.parallelStream().forEach(t -> {
				taskList.parallelStream().forEach(t2 -> {
					t2.accept(t);
				});
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			afterProduce(datas);
		}
	}

	protected void beforeProduce(Collection<T> e) {
		int currentForkJoinPoolCommonThreadSize = System
				.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism") == null ? 0
						: Integer.valueOf(System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
		currentForkJoinPoolCommonThreadSize += e.size() * taskList.size();
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
				currentForkJoinPoolCommonThreadSize + "");
	}

	protected void afterProduce(Collection<T> e) {
		int currentForkJoinPoolCommonThreadSize = System
				.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism") == null ? 0
						: Integer.valueOf(System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
				(currentForkJoinPoolCommonThreadSize - e.size() * taskList.size()) + "");
	}

}
