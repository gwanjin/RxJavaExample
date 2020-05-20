package chapter2;

import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectExample {
	public void marbleDiagram() {
		ReplaySubject<String> replay = ReplaySubject.create();
		replay.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		replay.onNext("1");
		replay.onNext("3");
		replay.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		replay.onNext("5");
		replay.onComplete();
	}
}
