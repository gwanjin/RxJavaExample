package chapter2;

import io.reactivex.subjects.BehaviorSubject;

public class BehaviorSubjectExample {
	public void marbleDiaglam() {
		BehaviorSubject<String> subject = BehaviorSubject.createDefault("6");
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext("1");
		subject.onNext("3");
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.onNext("5");
		subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));
		subject.onComplete();
		subject.onNext("9");
		subject.subscribe(data -> System.out.println("Subscriber #4 => " + data));
	}
}
