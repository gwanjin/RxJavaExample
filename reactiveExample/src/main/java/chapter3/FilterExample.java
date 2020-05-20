package chapter3;

import java.util.Arrays;
import java.util.stream.IntStream;

import io.reactivex.Observable;
import io.reactivex.Single;

public class FilterExample {
	public void marbleDiagram() {
		String[] objs = {"circle", "square", "triangle" };
		Observable<String> source = Observable.fromArray(objs);
		source.filter(obj -> obj.equalsIgnoreCase("circle")).subscribe(System.out::println);
	}
	
	public void otherFilter() {
		int numbers[] = {100, 200, 300, 400, 500};
		Single<Integer> single;
		Observable<Integer> source;
		
		single = Observable.fromArray(Arrays.stream(numbers).boxed().toArray(Integer[]::new)).first(-1);
		single.subscribe(data -> System.out.println("first() value = " + data));
		
		single = Observable.fromArray(IntStream.of(numbers).boxed().toArray(Integer[]::new)).last(-1);
		single.subscribe(data -> System.out.println("last() value = " + data));
		
		source = Observable.fromArray(IntStream.of(numbers).boxed().toArray(Integer[]::new)).take(3);
		source.subscribe(data -> System.out.println("take(3) value = " + data));
		
		source = Observable.fromArray(IntStream.of(numbers).boxed().toArray(Integer[]::new)).takeLast(3);
		source.subscribe(data -> System.out.println("takeLast(3) value = " + data));
		
		source = Observable.fromArray(IntStream.of(numbers).boxed().toArray(Integer[]::new)).skip(2);
		source.subscribe(data -> System.out.println("skip(2) value = " + data));
		
		source = Observable.fromArray(IntStream.of(numbers).boxed().toArray(Integer[]::new)).skipLast(2);
		source.subscribe(data -> System.out.println("skipLast(2) value = " + data));
	}
}
