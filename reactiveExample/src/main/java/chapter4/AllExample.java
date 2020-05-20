package chapter4;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AllExample {
	public static void main(String[] args) {
		AllExample.marbleDiagram();
	}
	
	public static void marbleDiagram() {
		String[] data = {"ball", "triangle", "ball"};
		Single<Boolean> source = Observable.fromArray(data).doOnNext(val->System.out.println(val)).doOnComplete(()->System.out.println("onComplete()")).all("ball"::equalsIgnoreCase);
		source.subscribe(val -> System.out.println("Result : " + val));
	}
}
