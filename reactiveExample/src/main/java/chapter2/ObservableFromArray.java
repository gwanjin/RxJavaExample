package chapter2;
import java.util.Arrays;
import java.util.stream.IntStream;

import io.reactivex.Observable;

public class ObservableFromArray {
	public void emit() {
		int[] arr = {1,2,3};
//		Observable<Integer> ob = Observable.fromArray(IntStream.of(arr).boxed().toArray(Integer[]::new));
		Observable<Integer> ob = Observable.fromArray(Arrays.stream(arr).boxed().toArray(Integer[]::new));
		ob.subscribe(System.out::println);
	}
}
