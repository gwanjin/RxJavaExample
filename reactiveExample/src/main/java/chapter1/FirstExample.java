package chapter1;
import io.reactivex.Observable;

public class FirstExample {
	public void emit() {
		Observable.just("HELLO", "RXJAVA2")
		.subscribe(System.out::println);
	}
}
