package chapter7;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import common.OKHttpHelper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.BiPredicate;
import io.reactivex.schedulers.Schedulers;

public class RetryExample {
	public static void main(String[] args) {
		RetryExample retryExample = new RetryExample();
//		retryExample.retry();
//		retryExample.retryWithDelay();
//		retryExample.retryUntil();
		retryExample.retryWhen();
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
	
	public void retryUntil() {
		// 시간표시용
		CommonUtils.exampleStart();
		
		String url = "http://api.github.com/zen";
		Observable<String> source = Observable.just(url)
								.map(OKHttpHelper::getT)
								.subscribeOn(Schedulers.io())
								.retryUntil(() -> {
									if(CommonUtils.isNetworkAvailable())
										return true;
									CommonUtils.sleep(1000);
									return false;
								});
		source.subscribe(Log::i);
		// IO스케줄러에서 실행되기 때문에 main스케줄러에 sleep를 이
		CommonUtils.sleep(5000);
		
	}
	
	public void retryWhen() {
		/**
		 * retry를 보다 세밀하고 처리하기 위한 함수 -> retryWhen
		 * 
		 */
		Observable.create((ObservableEmitter<String> emitter) -> {
			Log.d("Create()");
			emitter.onNext("1");
			emitter.onError(new Throwable());
		})
		.doOnNext(data -> Log.d("doOnNext : " + data))
		.doOnComplete(() -> Log.d("doOnComplete"))
		.retryWhen(
				/**
				 * retry후 일어나는 error를 Observable로 받아, 변환해서 Observable로 돌려줌
				 * callback으로 돌려준 특정값이 onNext()되고 그 타이밍에 retry가 됨
				 * 돌려준 Observable 에서 onComplete() 혹은 onError()를 호출하면 원래의 Observable가 onComplete() 혹은 onError()종료
				 */
				attempts -> {
			return attempts
					.zipWith(Observable.range(1, 3), (n,i)->i) // zipWith함수로 결합했기 때문에 range()가 끝나면 원래의 Observable도 종료됨
					.flatMap(i -> {
						Log.d("delay retry by " + i + " seconds");
						return Observable.timer(3, TimeUnit.SECONDS);
					});
			
		}).blockingForEach(s -> Log.d("blockingForEach : " + s)); // 아이템을 한개씩 꺼내서 출력
		
	}
}
