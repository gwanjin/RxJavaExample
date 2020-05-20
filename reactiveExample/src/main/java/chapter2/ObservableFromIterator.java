package chapter2;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import io.reactivex.Observable;

public class ObservableFromIterator {
	public void emit() {
//		List<String> names = new ArrayList<>();
//		names.add("aika");
//		names.add("jin");
//		Observable<String> ob = Observable.fromIterable(names);
//		ob.subscribe(System.out::println);
		
		BlockingQueue<Person> queue = new ArrayBlockingQueue<>(100);
		queue.add(new Person("aika", 23));
		queue.add(new Person("jin", 28));
		
		Observable<Person> o = Observable.fromIterable(queue);
		o.subscribe(person -> System.out.println(person));
	}
}
