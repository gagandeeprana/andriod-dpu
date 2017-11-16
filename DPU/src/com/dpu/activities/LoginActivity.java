package com.dpu.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dpu.bean.Failed;
import com.dpu.services.util.AndroidHttpRequestSender;
import com.dpu.services.util.Config;

public class LoginActivity extends Activity implements OnClickListener {

	Button btnLogin;
	EditText etUsername, etPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login);
		initialize();
	}

	private void initialize() {
		btnLogin = (Button) findViewById(R.id.loginBtn);
		etUsername = (EditText) findViewById(R.id.login_emailid);
		etUsername.setText("Admin");
		etPassword = (EditText) findViewById(R.id.login_password);
		etPassword.setText("Admin");
		btnLogin.setOnClickListener(this);
	}

	final String ROOT_URL = Config.url;

	private String convertJsonToBean(String jsonString) {
		String res = null;
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			res = jsonObject.getString("error-message");
		} catch (Exception e) {
			Log.e("convertJsonToBean()", e.toString());
		}
		return res;
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.loginBtn:
			if (isNetworkAvailable()) {
				String username = etUsername.getText().toString();
				String pass = etPassword.getText().toString();
				btnLogin.setEnabled(false);
//				startActivity(new Intent(this, MainActivity.class));
				new MainActivityBackgroundProcess().execute(username, pass);
			} else {
				Toast.makeText(LoginActivity.this, "No internet connection",
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			finish();
		}

	}

	public String authUser(String username, String password) {
		String response = null;
		try {
			String url = ROOT_URL + "employee/loginuser";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));
			response = new AndroidHttpRequestSender().putRequest(url, params);// Json
																				// string
																				// is
																				// stored
																				// here
			if (response != null && response.contains("error-message")) {
				Failed f = new ObjectMapper().readValue(response, Failed.class);
				return f.getMessage();
			}
		} catch (Exception e) {

		}
		return null;
	}

	class MainActivityBackgroundProcess extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			return authUser(params[0], params[1]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null) {
				Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT)
						.show();
			} else {
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
			}
			btnLogin.setEnabled(true);
		}
	}
}
