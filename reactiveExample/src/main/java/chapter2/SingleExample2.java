package chapter2;
import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleExample2 {
	public void emit() {
		
		// 기존 Observable에서 Single객체로 변환
		Observable<String> source = Observable.just("Singl1-1");
		Single.fromObservable(source)
		.subscribe(System.out::println);
		
		// Single() 함수를 호출해 Single 객체생성하기
		Observable.create(s -> {
//			s.onNext("Hello Single2");
			s.onComplete();
		}).single("default item")
		.subscribe(System.out::println);
		
		// first함수를 호출해 Single 객체 생성하기.
		String[] colors = {"Red", "Blue", "White"};
		Observable.fromArray(colors)
		.first("default item")
		.subscribe(System.out::println);
		
		// empty Observable에서 Single객체 생성하기
		Observable.empty().single("default item")
		.subscribe(System.out::println);
		
		// take() 함수에서 Single 객체 생성하기
		Observable.just(new Person("aika", 23), new Person("jin", 28))
		.take(1)
		.single(new Person("default", 0))
		.subscribe(person -> System.out.println(person));
	}
}
