package chapter4;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;

public class TimeIntervalExample {
	public static void main(String[] args) {
		TimeIntervalExample.marbleDiagram();
	}
	
	public static void marbleDiagram() {
		String[] data = {"1", "3", "7"};
		
		CommonUtils.exampleStart();
		Observable<Timed<String>> source = Observable.fromArray(data)
				.delay(item->{	// delayする内容をカスタマイズ
					CommonUtils.doSomething();	// random time sleep
					return Observable.just(item);
				}).timeInterval();
		// Timed toString()
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}
}
