package chapter5;

import common.CommonUtils;
import common.Log;
import common.OKHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class CallbackRxjava {
	private static final String FIRST_URL = "https://api.github.com/zen";
	private static final String SECOND_URL = CommonUtils.GITHUB_ROOT + "/samples/callback_hell";
	
	public static void main(String[] args) {
		CallbackRxjava rxjava = new CallbackRxjava();
//		rxjava.run();
		rxjava.useZip();
	}
	
	public void run() {
		CommonUtils.exampleStart();
		Observable<String> source = Observable.just(FIRST_URL)
				.subscribeOn(Schedulers.io())
				.map(OKHttpHelper::get)
				.concatWith(Observable.just(SECOND_URL).map(OKHttpHelper::get));
		source.subscribe(Log::it);
		CommonUtils.sleep(5000);
	}
	
	public void useZip() {
		// concatwithより２倍性能が良い
		CommonUtils.exampleStart();
		Observable.zip(
				Observable.just(FIRST_URL).subscribeOn(Schedulers.io()).map(OKHttpHelper::get),
				Observable.just(SECOND_URL).subscribeOn(Schedulers.io()).map(OKHttpHelper::get),
				(a,b) -> ("\n>> " + a + "\n>> " + b)).subscribe(Log::it);
		CommonUtils.sleep(5000);
	}
}
