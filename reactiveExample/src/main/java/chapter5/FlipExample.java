package chapter5;

import common.CommonUtils;
import common.Log;
import common.Shape;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class FlipExample {
	public static void main(String[] args) {
		FlipExample.marbleDiagram();
	}
	
	public static void marbleDiagram() {
		String[] data = {"1-S", "2-T", "3-P"};
		Observable<String> source = Observable.fromArray(data)
				.subscribeOn(Schedulers.newThread())
				.doOnNext(shape -> Log.v("Original Data : " + shape))
				.subscribeOn(Schedulers.newThread())
				.observeOn(Schedulers.newThread())
				.map(Shape::flip);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(500);
	}
}
