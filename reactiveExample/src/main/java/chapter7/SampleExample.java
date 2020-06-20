package chapter7;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class SampleExample {
	public static void main(String[] args) {
		SampleExample example = new SampleExample();
		example.marbleDiagram();
	}
	
	public void marbleDiagram() {
		String[] data = {"1", "7", "2", "3", "4"};
		
		CommonUtils.exampleStart();
		
		// 앞의 4개는100ms간격으로 발행
		Observable<String> earlySource = Observable.fromArray(data).take(4)
											.zipWith(Observable.interval(100, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		// 마지막데이터는 300ms후에 발행
		Observable<String> lastSource = Observable.just(data[4]).zipWith(Observable.timer(300, TimeUnit.MILLISECONDS), (a,b)->a);
		
		// 두 Observable을 결합하고 300ms로 샘플링
		// concat함수는 2개이상의 Observable을 붙여줌. 첫번째 Observable의 onComplete 이벤트가 발생해야 두번째 Observable을 구독.
		Observable<String> source = Observable.concat(earlySource, lastSource).sample(300L, TimeUnit.MILLISECONDS);
		
		//sample()함수의 실행이 끝나기 전에 Observable이 종료되는 경우, 마지막 값을 발행하기 위해 true를 인자로 넘겨줌.
//		Observable<String> source = Observable.concat(earlySource, lastSource).sample(300L, TimeUnit.MILLISECONDS, true);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
	}
}
