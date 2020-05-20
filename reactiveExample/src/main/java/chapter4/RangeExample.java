package chapter4;

import common.Log;
import io.reactivex.Observable;

public class RangeExample {
	public void marbleDiagram() {
		Observable<Integer> source = Observable.range(1, 10).filter(num -> (num%2==0));
		source.subscribe(Log::it);
	}
}
