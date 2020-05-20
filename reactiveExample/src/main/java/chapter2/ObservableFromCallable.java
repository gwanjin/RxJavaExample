package chapter2;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class ObservableFromCallable {
	public void emit() {
		Callable<String> callable = () -> {
			Thread.sleep(1000);
			return "Hello Callable";
		};
		
		Observable<String> ob = Observable.fromCallable(callable);
		ob.subscribe(System.out::println);
	}
}
