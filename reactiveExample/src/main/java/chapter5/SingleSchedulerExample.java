package chapter5;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SingleSchedulerExample {
	public static void main(String[] args) {
		SingleSchedulerExample.basic();
	}
	
	public static void basic() {
		Observable<Integer> numbers = Observable.range(100, 5);
		Observable<String> chars = Observable.range(0, 5).map(CommonUtils::numberToAlphabet);
		
		numbers.subscribeOn(Schedulers.single())
			.subscribe(Log::i);
		chars.subscribeOn(Schedulers.single())
			.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}
}
