package chapter5;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewThreadSchedulerExample {
	public static void main(String[] args) {
		NewThreadSchedulerExample.basic();
	}
	
	public static void basic() {
		Log.i("basic");
		String[] args = {"1", "3", "5"};
		Observable.fromArray(args)
			.doOnNext(data -> Log.v("Original data : " + data))
			.map(data -> "<<" + data + ">>")
			.subscribeOn(Schedulers.newThread())
			.subscribe(Log::i);
		CommonUtils.sleep(500);
		
		Observable.fromArray(args)
		.doOnNext(data -> Log.v("Original data : " + data))
		.map(data -> "##" + data + "##")
		.subscribeOn(Schedulers.newThread())
		.subscribe(Log::i);
		CommonUtils.sleep(500);
	}
}
