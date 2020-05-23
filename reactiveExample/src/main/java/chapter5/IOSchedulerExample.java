package chapter5;

import java.io.File;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class IOSchedulerExample {
	public static void main(String[] args) {
		IOSchedulerExample.basic();
	}
	
	public static void basic() {
		String root = "/Users/jeong/Desktop/private";
		File[] files = new File(root).listFiles();
		Observable<String> source = Observable.fromArray(files)
				.filter(f -> !f.isDirectory())
				.map(file -> file.getAbsolutePath())
				.subscribeOn(Schedulers.io());
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}
}