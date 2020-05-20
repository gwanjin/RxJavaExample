package chapter4;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class intervalRangeExample {
	public void printNumbers() {
		Observable<Long> source = Observable.intervalRange(1, 5, 100L, 100L, TimeUnit.MILLISECONDS);
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}
}
