package chapter4;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class GroupByExample {
	public static void marbleDiagram() {
		String[] objects = {"6-B", "4-B", "2-T", "2-B", "6-T", "4-T"};
		Observable<GroupedObservable<String, String>> source = Observable.fromArray(objects).groupBy(obj -> {
			if (obj.endsWith("-T")) {
				return "Triangle";
			} else {
				return "Ball";
			}
		});
		source.subscribe(obj -> {
			obj.filter(val -> obj.getKey().equals("Ball")).subscribe(val -> System.out.println("Group : " + obj.getKey() + " , Value : " + val));
//			obj.subscribe(val -> System.out.println("Group : " + obj.getKey() + " , Value : " + val));
		});
	}
	
	public static void main(String[] args) {
		GroupByExample.marbleDiagram();
	}
}
