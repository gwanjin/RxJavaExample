package chapter4;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class DelayExample {
	public static void main(String[] args) {
		DelayExample.marbleDiagram();
	}
	
	public static void marbleDiagram() {
		String[] data = {"1", "7", "5", "4", "3"};
		CommonUtils.exampleStart();
		Observable.fromArray(data).delay(100L, TimeUnit.MILLISECONDS).subscribe(Log::it);
		CommonUtils.sleep(1000);
	}
}
