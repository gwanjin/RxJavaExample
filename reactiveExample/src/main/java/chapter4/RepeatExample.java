package chapter4;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RepeatExample {
	public void marbleDiagram() {
		String[] balls = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(balls).repeat(3);
		source.doOnComplete(() -> {Log.d("onComplete()");}).subscribe(Log::i);
	}
	
	public void heartBeat() {
		CommonUtils.exampleStart();
		String serverURL = "https://api.github.com/zen";
		
		Observable.timer(1000, TimeUnit.MILLISECONDS).map(val -> serverURL)
		.map(url -> OkHttpHelper.get(url)).repeat()
		// .map(OkHttpHelper::get) 자동으로 파라미터가 들어감
		.doOnComplete(() -> System.out.println("onComplete"))
		.subscribe(res -> Log.it("Ping Result = " + res));
		CommonUtils.sleep(10000);
	}
}

class OkHttpHelper {
	private static OkHttpClient client = new OkHttpClient();
	public static String ERROR = "ERROR";
	
	public static String get(String url) throws IOException { 
		Request request = new Request.Builder()
		        .url(url)
		        .build();
		try {
			Response res = client.newCall(request).execute();
			return res.body().string();
		} catch (IOException e) {
			Log.e(e.getMessage());
			throw e;
		} 
	}

	public static String getT(String url) throws IOException { 
		Request request = new Request.Builder()
		        .url(url)
		        .build();
		try {
			Response res = client.newCall(request).execute();
			return res.body().string();
		} catch (IOException e) {
			Log.et(e.getMessage());
			throw e;
		} 
	}
	
	public static String getWithLog(String url) throws IOException { 
		Log.d("OkHttp call URL = " + url);
		return get(url);
	}	
}