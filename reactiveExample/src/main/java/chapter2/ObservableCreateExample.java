package chapter2;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class ObservableCreateExample {
	public void emit() {
		Observable<Integer> source = Observable.create( (ObservableEmitter<Integer> emitter) -> {
			emitter.onNext(100);
			emitter.onNext(200);
			emitter.onNext(300);
		});
		source.subscribe(System.out::println);
	}
}
