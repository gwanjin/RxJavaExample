package chapter4;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;

public class CombineLastestExample {
	public static void main(String[] args) {
//		CombineLastestExample.marbleDiagram();
		CombineLastestExample.ReactiveSum();
	}
	
	public static void marbleDiagram() {
		String[] data1 = {"6", "7", "4", "2"};
		String[] data2 = {"DIAMOND", "STAR", "PENTAGON"};
		
		Observable<String> source1 = Observable.zip(Observable.fromArray(data1), Observable.interval(100L, TimeUnit.MILLISECONDS), (data, i) -> data);
		Observable<String> source2 = Observable.zip(Observable.fromArray(data2), Observable.interval(150L, 200L, TimeUnit.MILLISECONDS), (data, i) -> data);
		Observable.combineLatest(source1, source2, (val1, val2) -> val1+val2).subscribe(Log::i);
		
		
		CommonUtils.sleep(2000);
	}
	
	public static void ReactiveSum() {
		ConnectableObservable<String> source = userInput();
		Observable<Integer> a = source.filter(str -> str.startsWith("a:"))
				.map(str -> str.replace("a:", ""))
				.map(Integer::parseInt);
		Observable<Integer> b = source.filter(str -> str.startsWith("b:"))
				.map(str -> str.replace("b:", ""))
				.map(Integer::parseInt);
		Observable.combineLatest(a.startWith(0), b.startWith(0), (x,y) -> x+y).subscribe(res->System.out.println("Result: " + res));
		source.connect(); 
	}
	
	public static ConnectableObservable<String> userInput() {
		return Observable.create((ObservableEmitter<String> emitter) -> {
			Scanner scan = new Scanner(System.in);
			while(true) {
				System.out.println("Input: ");
				String line = scan.nextLine();
				emitter.onNext(line);
				
				if(line.indexOf("exit") >= 0) {
					scan.close();
					emitter.onComplete();
					break;
				}
			}
		}).publish();
	}
}
