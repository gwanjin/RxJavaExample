package chapter5;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ComputationSchedulerExample {
	public static void main(String[] args) {
		ComputationSchedulerExample.basic();
	}
	
	public static void basic() {
		String[] args = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(args)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		source.map(data -> "<<" + data + ">>")
			.subscribeOn(Schedulers.computation())
			.subscribe(Log::i);
		
		source.map(data -> "##" + data + "##")
		.subscribeOn(Schedulers.computation())
		.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}
}
