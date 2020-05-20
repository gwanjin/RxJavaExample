package chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class DeferExample {
	List<String> colors;
	Iterator<String> iter;
	int count = 0;
	
	Iterator<String> colorList = Arrays.asList(Shape.RED, Shape.GREEN, Shape.BLUE, Shape.PUPPLE).iterator();
	
	public void marbleDiagram() {
		colors = new ArrayList<>();
		colors.add("RED");
		colors.add("ORANGE");
		iter = colors.iterator();
		
		Callable<Observable<String>> callable = () -> {
			if (iter.hasNext()) {
				String color = iter.next();
				return Observable.just(color, getCount());
			}
			
			return Observable.just("PINK", getCount());
		};
		
		Observable<String> source = Observable.defer(callable);
		source.subscribe(color -> System.out.println("Subscriber#1 color : " + color));
		source.subscribe(color -> System.out.println("Subscriber#2 color : " + color));
		source.subscribe(color -> System.out.println("Subscriber#3 color : " + color));
		source.subscribe(color -> System.out.println("Subscriber#4 color : " + color));
	}
	
	public String getCount() {
		return Integer.toString(++count);
	}
	
	public void marbleDiagram2() {
		Callable<Observable<String>> supplier = () -> getObservable();		
		Observable<String> source = Observable.defer(supplier);
		
		source.subscribe(val -> Log.i("Subscriber #1:" + val));
		source.subscribe(val -> Log.i("Subscriber #2:" + val));
		source.subscribe(val -> Log.i("Subscriber #3:" + val));
		source.subscribe(val -> Log.i("Subscriber #4:" + val));
		source.subscribe(val -> Log.i("Subscriber #5:" + val));
		CommonUtils.exampleComplete();
	}
	
	private Observable<String> getObservable() { 
		if (colorList.hasNext()) { 
			String color = colorList.next();
			return Observable.just(
				Shape.getString(color, Shape.BALL), 
				Shape.getString(color, Shape.RECTANGLE), 
				Shape.getString(color, Shape.PENTAGON)); 			
		}
		
		return Observable.empty();		
	}
}
