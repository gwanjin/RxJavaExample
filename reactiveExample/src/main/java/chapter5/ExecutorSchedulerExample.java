package chapter5;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ExecutorSchedulerExample {
	public static void main(String[] args) {
		ExecutorSchedulerExample.basic();
	}
	
	public static void basic() {
		final int THREAD_NUM = 10;
		String[] data = {"1", "2", "3"};
		
		Observable<String> source = Observable.fromArray(data);
		Executor executor = Executors.newFixedThreadPool(THREAD_NUM);	// 10個のThreadを用意
		
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor))
			.subscribe(Log::i);
		
		CommonUtils.sleep(1000);
	}
}
