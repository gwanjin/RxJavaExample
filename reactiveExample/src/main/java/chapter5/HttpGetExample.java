package chapter5;

import java.io.IOException;

import common.Log;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpGetExample {
	public static final String URL = "https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md";
	private OkHttpClient client = new OkHttpClient();
	
	public static void main(String[] args) {
		HttpGetExample example = new HttpGetExample();
		example.run();
	}
	
	public void run() {
		Request request = new Request.Builder().url(URL).build();
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Log.i(response.body().string());				
			}
			
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}
		});
	}
}
