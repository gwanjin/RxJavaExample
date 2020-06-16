package chapter7;

import common.Log;
import io.reactivex.Observable;

public class ExceptionHandling {
	public static void main(String[] args) {
		ExceptionHandling handling = new ExceptionHandling();
//		handling.cannotCatch();
//		handling.onErrorReturn();
//		handling.onError();
		handling.onErrorItem();
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
}
