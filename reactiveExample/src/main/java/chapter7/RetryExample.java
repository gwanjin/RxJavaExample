package chapter7;

import common.CommonUtils;
import common.Log;
import common.OKHttpHelper;
import io.reactivex.Observable;
import io.reactivex.functions.BiPredicate;

public class RetryExample {
	public static void main(String[] args) {
		RetryExample retryExample = new RetryExample();
//		retryExample.retry();
		retryExample.retryWithDelay();
	}
	
	public void retry() {
		CommonUtils.exampleStart();
		
		String url = "https://api.github.com/zen";
		Observable<String> source = Observable.just(url)
						.map(OKHttpHelper::getT)
						.retry(5)
						.onErrorReturn(e -> CommonUtils.ERROR_CODE);
		
		source.subscribe(data -> Log.it("result : " + data));
	}
	
	public void retryWithDelay() {
		final int RETRY_MAX = 5;
		final int RETRY_DELAY = 1000;
		
		CommonUtils.exampleStart();
		String url = "https://api.github.com/zen";
//		Observable<String> source = Observable.just(url)
//						.map(OKHttpHelper::getT)
//						.retry(new BiPredicate<Integer, Throwable>() {
//							@Override
//							public boolean test(Integer retryCnt, Throwable e) throws Exception {
//								Log.e("retryCnt : " + retryCnt);
//								CommonUtils.sleep(RETRY_DELAY);
//								
//								return retryCnt < RETRY_MAX ? true : false;
//							}
//						})
//						.onErrorReturn(e -> CommonUtils.ERROR_CODE);
		
		Observable<String> source = Observable.just(url).map(OKHttpHelper::getT).retry((retryCnt, e) -> {
								Log.e("retryCnt : " + retryCnt);
								CommonUtils.sleep(RETRY_DELAY);
								return retryCnt < RETRY_MAX ? true : false;
							}).onErrorReturn(e -> CommonUtils.ERROR_CODE);
		
		
		source.subscribe(data -> Log.it("result : " + data));
	}
}
