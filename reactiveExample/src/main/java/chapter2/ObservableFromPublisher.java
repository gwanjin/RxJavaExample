package chapter2;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class ObservableFromPublisher {
	public void emit() {
		Publisher<String> publisher = (Subscriber<? super String> s) -> {
			s.onNext("Hello");
			s.onComplete();
		};
		
	}
}
