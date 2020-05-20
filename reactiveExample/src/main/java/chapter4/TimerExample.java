package chapter4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class TimerExample {
	public static void marbleDiagram() {
		CommonUtils.exampleStart();
		Observable<String> source = Observable.timer(500L, TimeUnit.MILLISECONDS)
				.map(data -> { return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()); });
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
	}
	
	public static void main(String[] args) {
		TimerExample.marbleDiagram();
	}
}
