package chapter2;


import io.reactivex.Single;

public class SingleExample {
	public void emit() {
		Single<String> single = Single.just("test");
		single.subscribe(System.out::println);
	}
}
