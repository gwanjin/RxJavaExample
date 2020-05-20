package chapter3;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class FlatMapExample {
	public void marbleDiagram() {
		Function<String, Observable<String>> func
					= number -> Observable.just(number+"◇", number+"◇");
		
		String[] arr = {"1", "3", "5" };
		Observable<String> source = Observable.fromArray(arr).flatMap(func);
		source.subscribe(System.out::println);
	}
}
