package chapter4;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class ConcatMapExample {
	public void marbleDiagram() {
		CommonUtils.exampleStart();
		
		String[] balls = {"1", "2", "3"};
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> balls[idx]).take(balls.length)
				.concatMap(
						ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
						.map(notUsed -> ball+ "◇").take(2));
		source.subscribe(Log::it);
		CommonUtils.sleep(2000);				
	}
}
