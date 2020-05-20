package chapter4;

import common.Log;
import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class MathFunctionExample {
	public static void main(String[] args) {
		MathFunctionExample.marbleDiagram();
	}
		
	public static void marbleDiagram() {
		Integer[] data = {1,2,3,4,10};
		
		// count function
		Observable.fromArray(data).count().subscribe(count -> Log.i(count));
		
		// max && min
		Flowable.fromArray(data).to(MathFlowable::max).subscribe(max -> Log.i(max));
		
		Flowable.fromArray(data).to(MathFlowable::min).subscribe(min -> Log.i(min));
		
		// sum & average
		Flowable.fromArray(data).to(MathFlowable::sumInt).subscribe(sum->Log.i(sum));
		
		Observable.fromArray(data).toFlowable(BackpressureStrategy.BUFFER).to(MathFlowable::averageDouble).subscribe(avg -> Log.i(avg));
	}
}
