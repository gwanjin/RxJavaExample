package chapter5;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TrampolineSchedulerExample {
	public static void main(String[] args) {
		TrampolineSchedulerExample.basic();
	}
	
	public static void basic() {
		String[] args = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(args);
		
		source.subscribeOn(Schedulers.trampoline())
		.map(data -> "<<"+data+">>")
		.subscribe(Log::i);
		
		source.subscribeOn(Schedulers.trampoline())
		.map(data -> "##"+data+"##")
		.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}
}
