package chapter4;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class IntervalExample {
	public void printNumbers() {
		CommonUtils.exampleStart();
		Observable<Long> source = Observable.interval(100L, TimeUnit.MICROSECONDS)
				.map(data -> (data + 1)*100).take(5);
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
	}
	
	public void initailDelay() {
		CommonUtils.exampleStart();
		Observable<Long> source = Observable.interval(0L, 100L, TimeUnit.MICROSECONDS)
				.map(data -> (data + 1)*100).take(5);
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
	}
}
