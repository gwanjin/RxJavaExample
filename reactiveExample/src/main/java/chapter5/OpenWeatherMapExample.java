package chapter5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.CommonUtils;
import common.Log;
import common.OKHttpHelper;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

public class OpenWeatherMapExample {
	private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=";
	
	public void run() {
		Observable<String> source = Observable.just(URL)
				.map(OKHttpHelper::getWithLog)
				.subscribeOn(Schedulers.io());
		
		Observable<String> temperature = source.map(this::parseTemperature);
		Observable<String> cityName = source.map(this::parseCityName);
		Observable<String> countryName = source.map(this::parseCountry);
		
		CommonUtils.exampleStart();
		
		Observable.concat(temperature, cityName, countryName)
			.observeOn(Schedulers.newThread())
			.subscribe(Log::it);
		
		CommonUtils.sleep(3000);
	}
	
	public void run2() {
		Observable<String> source = Observable.just(URL)
				.map(OKHttpHelper::getWithLog)
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.newThread());
		
		ConnectableObservable<String> connect = source.publish();
		connect.map(this::parseTemperature).subscribe(Log::it);
		connect.map(this::parseCityName).subscribe(Log::it);
		connect.map(this::parseCountry).subscribe(Log::it);
		CommonUtils.exampleStart();
		connect.connect();
		
		CommonUtils.sleep(3000);
	}
	
	public void useRefCount() {
		CommonUtils.exampleStart();
		Observable<String> source = Observable.just(URL)
				.map(OKHttpHelper::getWithLog)
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.newThread())
				.publish().refCount();
		
		source.map(this::parseTemperature).subscribe(Log::it);
		source.map(this::parseCityName).subscribe(Log::it);
		source.map(this::parseCountry).subscribe(Log::it);
		CommonUtils.sleep(2000);
	}
	
	public void userShare() {
		CommonUtils.exampleStart();
		Observable<String> source = Observable.just(URL)
				.map(OKHttpHelper::getWithLog)
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.newThread())
				.doOnComplete(() -> System.out.println("doConComplete"))
				.share(); // share() -> .publish()+refCount()
		// refCount()함수
		// 더이상 배출할 Observer가 없을시 refCount는 자동으로 자신을 해지를 하고 다시 새로운 Observer 이 오면 처음부터 자동으로 시작을 한다.
		source.map(this::parseTemperature).subscribe(Log::it);
		CommonUtils.sleep(2000);
		source.map(this::parseCityName).subscribe(Log::it);
		CommonUtils.sleep(2000);
		source.map(this::parseCountry).subscribe(Log::it);
		CommonUtils.sleep(2000);
	}
	
	private String parseTemperature(String jsonString) {
		return parse(jsonString, "\"temp\":[0-9]*.[0-9]*");
	}
	
	private String parseCityName(String jsonString) {
		return parse(jsonString, "\"name\":\"[a-zA-Z]*\"");
	}
	
	private String parseCountry(String jsonString) {
		return parse(jsonString, "\"country\":\"[a-zA-Z]*\"");
	}
	
	private String parse(String jsonString, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(jsonString);
		if(match.find()) {
			return match.group();
		}
		
		return "N/A";
	}
	
	public static void main(String[] args) {
		OpenWeatherMapExample example = new OpenWeatherMapExample();
		example.userShare();
	}
}
