package chapter3;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MapExample {
	public void mapMarbleDiagram() {
		String[] balls = {"1", "2", "3", "4" };
		Observable<String> ob = Observable.fromArray(balls).map(ball -> ball + "◇");
		ob.subscribe(System.out::println);
	}
	
	public void mapFunction() {
		Function<String, String> func = ball -> ball + "◇";
		String[] balls = {"5", "6", "7", "8" };
		Observable<String> source = Observable.fromArray(balls).map(func);
		source.subscribe(System.out::println);
	}
	
	public void mappingType() {
		Function<String, Integer> ballToIndex = ball -> {
			switch(ball) {
				case "RED":		return 1;
				case "BLUE":	return 2;
				case "YELLOW":	return 3;
				default:  		return -1;
			}
		};
		
		String arr[] = {"RED", "BLUE", "YELLOW", "GREEN" };
		Observable<Integer> source = Observable.fromArray(arr).map(ballToIndex);
		source.subscribe(System.out::println);
	}
}
