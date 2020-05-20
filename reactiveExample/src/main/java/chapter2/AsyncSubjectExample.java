package chapter2;

import java.util.Arrays;
import java.util.stream.IntStream;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class AsyncSubjectExample {
	public void marbleDiagram() {
		AsyncSubject<String> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext("1");
		subject.onNext("3");
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.onNext("5");
		subject.onComplete();
	}
	
	public void asSubscriber() {
		Float[] temperature = { 10.1f, 13.4f, 12.5f };
		Observable<Float> object = Observable.fromArray(temperature);
		
		AsyncSubject<Float> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		
		object.subscribe(subject);
	}
	
	public void afterComplete() {
		AsyncSubject<Integer> subject = AsyncSubject.create();
		subject.onNext(11);
		subject.onNext(12);
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext(13);
		subject.onNext(14);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.onComplete();
		subject.onNext(15);
		subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));
	}
}
