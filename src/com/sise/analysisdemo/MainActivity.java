package com.sise.analysisdemo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.sise.utils.ParseJsonUtils;
import com.sise.utils.ParseXmlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button btn_xml;
	Button btn_json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_xml = (Button) findViewById(R.id.btn_xml);
		btn_json = (Button) findViewById(R.id.btn_json);
		
		btn_xml.setOnClickListener(this);
		btn_json.setOnClickListener(this);

	}

	private void sendRequestWithHttpClientXML() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					HttpClient httpClient = new DefaultHttpClient();
					// 指定访问的服务器地址是电脑本机
					HttpGet httpGet = new HttpGet(
							"http://10.0.3.2/get_data.xml");
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						// 请求和响应都成功了
						HttpEntity entity = httpResponse.getEntity();
						String response = EntityUtils.toString(entity, "utf-8");

						// ParseXmlUtils.parseXMLWithPull(response);
						ParseXmlUtils.parseXMLWithSAX(response);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void sendRequestWithHttpClientJSON() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					HttpClient httpClient = new DefaultHttpClient();
					// 指定访问的服务器地址是电脑本机
					HttpGet httpGet = new HttpGet(
							"http://10.0.3.2/get_data.json");
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						// 请求和响应都成功了
						HttpEntity entity = httpResponse.getEntity();
						String response = EntityUtils.toString(entity, "utf-8");
						
//						ParseJsonUtils.parseJSONWithJSONObject(response);
						ParseJsonUtils.parseJSONWithGSON(response);
						
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_xml:
			sendRequestWithHttpClientXML();
			break;
		case R.id.btn_json:
			sendRequestWithHttpClientJSON();
			break;
		default:
			break;
		}

	}

}
