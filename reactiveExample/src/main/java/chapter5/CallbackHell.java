package chapter5;

import java.io.IOException;

import common.CommonUtils;
import common.Log;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CallbackHell {
	private static final String FIRST_URL = "https://api.github.com/zen";
	private static final String SECOND_URL = CommonUtils.GITHUB_ROOT + "/samples/callback_hell";
	
	private final OkHttpClient client = new OkHttpClient();
	private Callback onSuccess = new Callback() {
		
		@Override
		public void onResponse(Call call, Response response) throws IOException {
			Log.i("onSuccess " + response.body().string());
		}
		
		@Override
		public void onFailure(Call call, IOException e) {
			e.printStackTrace();
		}
	};
	
	public static void main(String[] args) {
		CallbackHell hell = new CallbackHell();
		hell.run();
	}
	
	public void run() {
		Request request = new Request.Builder().url(FIRST_URL).build();
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Log.i(response.body().string());
				
				Request request = new Request.Builder().url(SECOND_URL).build();
				client.newCall(request).enqueue(onSuccess);
			}
			
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}
		});
	}
}
