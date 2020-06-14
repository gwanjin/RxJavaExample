package chapter7;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import hu.akarnokd.rxjava2.async.DisposableObservable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DoOnExample {
	public static void main(String[] args) {
		DoOnExample example = new DoOnExample();
//		example.basic();
//		example.withError();
//		example.doOnEach();
//		example.doOnEachObserver();
//		example.doOnSubscribeAndDispose();
//		example.doOnLifecycle();
		example.doOnTerminate();
	}

	public void basic() {
		String[] args = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(args);
		
		source.doOnNext(s -> Log.d("onNext()", s))
				.doOnComplete(() -> Log.d("doOnComplete"))
				.doOnError(e -> Log.d("onError()", e.getMessage()))
				.subscribe(Log::i);
	}
	
	public void withError() {
		Integer[] dividers = {10, 5, 0};
		Observable.fromArray(dividers)
					.map(div -> 1000/div)
					.doOnNext(data -> Log.d("doOnNext()", data))
					.doOnComplete(() -> Log.d("doOnComplete"))
					.doOnError(error -> Log.d("doOnError()", error.getMessage()))
					.subscribe(Log::i);
	}
	
	public void doOnEach() {
		String[] data = {"ONE", "TWO", "THREE"};
		Observable<String> source = Observable.fromArray(data);
		
		source.doOnEach(noti -> {
			if(noti.isOnNext()) Log.d("doOnNext()", noti.getValue());
			if(noti.isOnError()) Log.d("doOnError()", noti.getError().getMessage());
			if(noti.isOnComplete()) Log.d("doOnComplete");
		}).subscribe(System.out::println);
	}
	
	public void doOnEachObserver() {
		String[] data = {"ONE", "TWO", "THREE"};
		Observable<String> source = Observable.fromArray(data);
		source.doOnEach(new Observer<String>() {
			@Override
			public void onNext(String t) {
				Log.d("doOnNext()", t);
			}
			
			@Override
			public void onError(Throwable e) {
				Log.d("onError()", e.getMessage());
			}
			
			@Override
			public void onComplete() {
				Log.d("onComplete");
			}

			@Override
			public void onSubscribe(Disposable d) {
				// doOnEach에서 onSubscribe함수는 호출되지 않음 
			}
		}).subscribe(System.out::println);
	}
	
	public void doOnSubscribeAndDispose() {
		String[] args = {"1", "3", "5", "2", "6"};
		Observable<String> source = Observable.fromArray(args)
										.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b)->a)
										.doOnSubscribe(d -> Log.d("doOnsubscribe()"))
										.doOnDispose(() -> Log.d("doOnDispose()"));
		Disposable disposable = source.subscribe(Log::i);
		CommonUtils.sleep(200);
		disposable.dispose();
		CommonUtils.sleep(300);
	}
	
	public void doOnLifecycle() {
		String[] args = {"1", "3", "5", "2", "6"};
		Observable<String> source = Observable.fromArray(args)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b)->a)
				.doOnComplete(() -> Log.d("doOnComplete"))	// 다 발행되기 전에 dispose()함수를 호출하기 때문에 호출되지 않음
				.doOnLifecycle(d -> Log.d("doOnsubscribe()"), () -> Log.d("doOnDispose()"));
		
		Disposable disposable = source.subscribe(Log::i);
		CommonUtils.sleep(200);
		disposable.dispose();
		CommonUtils.sleep(300);
	}
	
	public void doOnTerminate() {
		String[] args = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(args);
		
		source.doOnTerminate(() -> Log.d("doOnTerminate()"))
				.doOnComplete(() -> Log.d("doOnComplete()"))
				.doOnError(e -> Log.d("onError()", e.getMessage()))
				.subscribe(Log::i);
	}
}
