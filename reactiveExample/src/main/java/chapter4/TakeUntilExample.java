package chapter4;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class TakeUntilExample {
	public static void main(String[] args) {
		TakeUntilExample.marbleDiagram();
	}
	
	public static void marbleDiagram() {
		String[] data = {"1", "2", "3", "4", "5"};
		
		Observable<String> source = Observable.fromArray(data)
				.zipWith(Observable.interval(150L, TimeUnit.MILLISECONDS), (val, notUsed) -> val)
				.takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS)).doOnComplete(()->Log.d("onComplete()"));
		source.subscribe(Log::i);
		CommonUtils.sleep(2000);
	}
}
