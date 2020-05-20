package chapter4;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class SkipUntilExample {
	public static void main(String[] args) {
		SkipUntilExample.marbleDiagram();
	}
	
	public static void marbleDiagram() {
		String[] data = {"1", "2", "3", "4", "5"};
		
		Observable<String> source = Observable.fromArray(data)
				.zipWith(Observable.interval(150L, TimeUnit.MILLISECONDS), (val, notUsed)->val)
				.skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS).doOnNext(val->Log.d("doOnNext")));
		source.subscribe(Log::i);
		CommonUtils.sleep(2000);
	}
}
