package chapter4;

import io.reactivex.Observable;

public class ScanExample {
	public static void marbleDiagram() {
		String[] balls = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(balls).scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(System.out::println);
	}
	
	public static void main(String[] args) {
		ScanExample.marbleDiagram();
	}
}
