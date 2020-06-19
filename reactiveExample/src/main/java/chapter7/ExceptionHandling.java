package chapter7;

import common.Log;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class ExceptionHandling {
	public static void main(String[] args) {
		ExceptionHandling handling = new ExceptionHandling();
//		handling.cannotCatch();
//		handling.onErrorReturn();
//		handling.onError();
//		handling.onErrorItem();
		handling.onErrorResumeNext();
	}
	
	public void cannotCatch() {
		
		try {
			Integer[] dividers = {10, 5, 0};
			Observable.fromArray(dividers)
						.map(div -> 1000/div)
						.subscribe(Log::i);
		} catch(Exception e) {
			Log.e(e.getMessage());
		}
	}
	
	public void onErrorReturn() {
		String[] datas = {"11", "22", "100", "$98", "91", "83"};
		
		Observable<Integer> source = Observable.fromArray(datas)
										.map(data -> Integer.parseInt(data))
										.onErrorReturn(e -> {
											if (e instanceof NumberFormatException) {
												e.printStackTrace();
											}
											return -1;
										});
		source.subscribe(result -> {
			if(result <0 ) Log.e("Wrong Data Found!");
			Log.i("result : " + result);
		});
	}
	
	public void onError() {
		String[] datas = {"11", "22", "100", "$98", "91", "83"};
		
		Observable<Integer> source = Observable.fromArray(datas)
										.map(data -> Integer.parseInt(data));
		source.subscribe(result -> Log.i("result : " + result),
							error -> {
									if (error instanceof NumberFormatException) 
										error.printStackTrace();
									Log.e("Wrong Data Found!");
								}
							);
	}
	
	public void onErrorItem() {
		String[] datas = {"11", "22", "100", "$98", "91", "83"};
		Observable.fromArray(datas).map(data -> Integer.parseInt(data))
					.onErrorReturnItem(-1)
					.subscribe(result -> {
						if(result < 0) {
							Log.e("Wrong Data Found");
						} else {
							Log.i("result : " + result);
						}
					});
	}
	
	public void onErrorResumeNext() {
		String[] salesData = {"100", "200", "A300"};
		Observable<Integer> parseError = Observable.defer(() -> {
			Log.d("send mail");
			return Observable.just(-1, 1000);
		}).subscribeOn(Schedulers.io());
		
		Observable<Integer> source = Observable.fromArray(salesData)
							.map(Integer::parseInt)
							.onErrorResumeNext(parseError);
		
		source.subscribe(data -> {
			if(data<0) {
				Log.e("Wrong Data found");
				return;
			}
			Log.i("Sales data : " + data);
		});
	}
}
