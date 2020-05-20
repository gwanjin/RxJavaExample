package chapter4;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class ZipExample {
	static int index= 0;
	
	public static void main(String[] args) {
//		ZipExample.marbleDiagram();
//		ZipExample.zipNumbers();
//		ZipExample.zipInterval();
//		ZipExample.electricBillV1();
		ZipExample.zipWith();
	}
	
	public static void zipWith() {
		Observable<Integer> addNumber = Observable.zip(
				Observable.just(100, 200, 300),
				Observable.just(10, 20, 30),
				(a,b) -> a+b)
				.zipWith(Observable.just(1, 2, 3), (ab, c) -> ab+c);
			
			addNumber.subscribe(System.out::println);
	}
	
	public static void electricBillV1() {
		String[] data = {"100", "318"};
		
		
		Observable<Integer> basePrice = Observable.fromArray(data)
				.map(Integer::parseInt)
				.map(val -> {
					if (val <= 200) return 910;
					else if (val <= 400) return 1600;
					else return 7300;
				});
		
		Observable<Double> usagePrice = Observable.fromArray(data)
				.map(Double::parseDouble)
				.map(val -> {
					double range1 = Math.min(200, val)*93.3;
					double range2 = Math.min(200, Math.max(val-200,0))*187.9;
					double range3 = Math.min(0, Math.max(val-400, 0))*280.6;
					return range1+range2+range3;
				});
		
		
		Observable.zip(
				basePrice,
				usagePrice,
				(base, usage) -> base + usage
			).map(val -> new DecimalFormat("#,###").format(val))
			.subscribe(resultPrice -> {
				StringBuilder sb = new StringBuilder();
				sb.append("Usage : " + data[index] + "kWh => ");
				sb.append("Price : " + resultPrice + " Won");
				System.out.println(sb.toString());
				index++;
			});
	}
	
	public static void zipInterval() {
		Observable<String> zipInterval = Observable.zip(
			Observable.just("RED", "BLUE", "GREEN"),
			Observable.interval(200L, TimeUnit.MICROSECONDS),
			(color , i) -> "color : " + color + ", i : " + i
		).doOnComplete(() -> System.out.println("onComplete()"));
		zipInterval.subscribe(System.out::println);
		CommonUtils.sleep(3000);
	}
	
	public static void zipNumbers() {
		Observable<String> addNumber = Observable.zip(
			Observable.just(100, 200, 300),
			Observable.just(10, 20, 30),
			Observable.just(1, 2, 3),
			(a,b,c) -> "a+b+c : " + (a+b+c)
		);
		
		addNumber.subscribe(System.out::println);
	}
	
	public static void marbleDiagram() {
		String[] shapes = {"Ball", "Square", "Triangle"};
		String[] colors = {"Red", "Blue", "Yellow" };
		
		Observable<String> source = Observable.zip(
				Observable.just("Ball", "Square"),
				Observable.just("Red", "Blue", "Green"),
				(suffix, color) -> { return color + suffix; });
		
		source.subscribe(Log::i);
	}
}
