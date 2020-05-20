package chapter3;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import io.reactivex.Maybe;
import io.reactivex.Observable;


public class QuerySales {
	public void run() {
		List<Pair<String, Integer>> list = new ArrayList<>();
		
		list.add(Pair.of("TV", 2500));
		list.add(Pair.of("Camera", 300));
		list.add(Pair.of("TV", 1600));
		list.add(Pair.of("Phone", 800));
		
		Maybe<Integer> sales = Observable.fromIterable(list).filter(sale -> "TV".equals(sale.getLeft())).map(pair -> pair.getRight())
				.reduce((sale1, sale2) -> sale1 + sale2);
		sales.subscribe(total -> System.out.println("TV sales total : " + total));
		
	}
}
