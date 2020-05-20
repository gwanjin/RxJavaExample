package chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class AmbExample {
	public static void main(String[] args) {
		AmbExample.marbleDiagram();
	}
	
	public static void marbleDiagram() {
		String[] data1 = {"1", "2", "3"};
		String[] data2 = {"4", "5", "6"};
		
		List<Observable<String>> list = Arrays.asList(
//				Observable.fromArray(data1).doOnComplete(() -> Log.d("Observable#1 onComplete")),
				Observable.interval(200L, TimeUnit.MILLISECONDS).map(Long::intValue).map(idx -> data1[idx]).take(data1.length).doOnComplete(()->Log.d("Observable#1 onComplete")),
				Observable.interval(500L, TimeUnit.MILLISECONDS).map(Long::intValue).map(idx -> data2[idx]).take(data2.length).doOnComplete(()->Log.d("Observable#2 onComplete"))
			);
		
		Observable.amb(list).doOnComplete(()->Log.d("Result : onComplete")).subscribe(Log::i);
		CommonUtils.sleep(3000);
	}
}
