package chapter3;

import java.util.Scanner;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Gugudan {
	public void version1() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Gududan Input: ");
		int dan = scan.nextInt();
		
		Observable<Integer> source = Observable.range(1, 9);
		source.subscribe(data -> System.out.println(dan + "*" + data +"= " + (dan*data)));
	}
	
	public void version2() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Gududan Input: ");
		int dan = scan.nextInt();
		
		Function<Integer, Observable<String>> gugudan = num -> Observable.range(1,9).map(row -> num + "*" + row + " = " + (row*num));
		Observable<String> ob = Observable.just(dan).flatMap(gugudan);
		ob.subscribe(System.out::println);
	}
	
	public void version3() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Gududan Input: ");
		int dan = scan.nextInt();
		
		
		Observable<String> source = Observable.just(dan).flatMap(num -> Observable.range(1,9).map(row -> num + "*" + row + " = " + (row*num)));
		Observable<String> source2 = Observable.just(dan).flatMap(num -> Observable.range(1, 9), (gugu, i) -> gugu+"*"+i+" = " + (gugu*i));
		source2.subscribe(System.out::println);
	}
}
