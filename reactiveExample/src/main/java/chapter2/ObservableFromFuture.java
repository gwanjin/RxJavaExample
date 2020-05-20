package chapter2;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.Observable;

public class ObservableFromFuture {
	public void emit() {
		Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
			Thread.sleep(1000);
			return "Hello";
		});
		
		Observable<String> ob = Observable.fromFuture(future);
		ob.subscribe(System.out::println);
	}
}
