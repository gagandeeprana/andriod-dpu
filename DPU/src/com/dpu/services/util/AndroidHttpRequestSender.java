package com.dpu.services.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.internal.http.HttpMethod;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;

import android.util.Log;

public class AndroidHttpRequestSender {

public String sendRequest(String url, List<NameValuePair> params){
	StringBuilder response=new StringBuilder();
	try{
		DefaultHttpClient client= new DefaultHttpClient();
		//it will act as android client,just like in web google chrome acts as a client
		HttpPost request= new HttpPost(url);
		Log.e("URL ", url+"");

		//get or post and we have to define url in its constructor
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
//		request.setHeader("Content-Type", "application/json");
		//all name value pairs are stored in urlEncodedFormEntity class
		request.setEntity(entity);
		//now set this entity into request object
		HttpResponse httpResponse=client.execute(request);
		Log.e("HTTP RES ", httpResponse+"");
		//we will execute the request using defaultHttpClient and then we get HttpResponse
		InputStream is=httpResponse.getEntity().getContent();
		//we will fetch inputStream from this httpResponse object
		InputSource iSource= new InputSource(is);
		//we can also use directly inputStream but it sometimes give streamCorrupted exception.
		//So for reliable data transfer,we should use inputSource class.
		//then we get byte stream from input source and pass it into inputSource object
		//and pass inputStreamReader into bufferedReader
		BufferedReader readFromServer= new BufferedReader(new InputStreamReader(iSource.getByteStream()));
		String temp="";
		while((temp= readFromServer.readLine())!=null){
			response.append(temp);
		}
		Log.e("RES.... " , response+"");
		is.close();
	} catch (Exception e) {
		Log.e("In SendRequest()",e.toString());
	}
	return response.toString();
}

public String sendUpdateRequest(String url, List<NameValuePair> params){
	StringBuilder response=new StringBuilder();
	try{
		DefaultHttpClient client= new DefaultHttpClient();
		//it will act as android client,just like in web google chrome acts as a client
		HttpPut request= new HttpPut(url);
		//get or post and we have to define url in its constructor
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
//		request.setHeader("Content-Type", "application/json");
		//all name value pairs are stored in urlEncodedFormEntity class
		request.setEntity(entity);
		//now set this entity into request object
		HttpResponse httpResponse=client.execute(request);
		Log.e("HTTP RES ", httpResponse+"");
		//we will execute the request using defaultHttpClient and then we get HttpResponse
		InputStream is=httpResponse.getEntity().getContent();
		//we will fetch inputStream from this httpResponse object
		InputSource iSource= new InputSource(is);
		//we can also use directly inputStream but it sometimes give streamCorrupted exception.
		//So for reliable data transfer,we should use inputSource class.
		//then we get byte stream from input source and pass it into inputSource object
		//and pass inputStreamReader into bufferedReader
		BufferedReader readFromServer= new BufferedReader(new InputStreamReader(iSource.getByteStream()));
		String temp="";
		while((temp= readFromServer.readLine())!=null){
			response.append(temp);
		}
		Log.e("RES.... " , response+"");
		is.close();
	} catch (Exception e) {
		Log.e("In SendRequest()",e.toString());
	}
	return response.toString();
}

public String getRequest(String url, List<NameValuePair> params){
	StringBuilder response=new StringBuilder();
	try{
		DefaultHttpClient client= new DefaultHttpClient();
		//it will act as android client,just like in web google chrome acts as a client
		HttpGet request= new HttpGet(url);
		//get or post and we have to define url in its constructor
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
		//all name value pairs are stored in urlEncodedFormEntity class
//		request.setEntity(entity);
		//now set this entity into request object
		HttpResponse httpResponse=client.execute(request);
		Log.e("HTTP RES ", httpResponse+"");
		//we will execute the request using defaultHttpClient and then we get HttpResponse
		InputStream is=httpResponse.getEntity().getContent();
		//we will fetch inputStream from this httpResponse object
		InputSource iSource= new InputSource(is);
		//we can also use directly inputStream but it sometimes give streamCorrupted exception.
		//So for reliable data transfer,we should use inputSource class.
		//then we get byte stream from input source and pass it into inputSource object
		//and pass inputStreamReader into bufferedReader
		BufferedReader readFromServer= new BufferedReader(new InputStreamReader(iSource.getByteStream()));
		String temp="";
		while((temp= readFromServer.readLine())!=null){
			response.append(temp);
		}
		Log.e("RES.... " , response+"");
		is.close();
	} catch (Exception e) {
		Log.e("In SendRequest()",e.toString());
	}
	return response.toString();
}

public String putRequest(String url, List<NameValuePair> params){
	StringBuilder response=new StringBuilder();
	try{
		DefaultHttpClient client= new DefaultHttpClient();
		//it will act as android client,just like in web google chrome acts as a client
		HttpPut request= new HttpPut(url);
		Log.e("URL: ", url+"");

		//get or post and we have to define url in its constructor
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
		//all name value pairs are stored in urlEncodedFormEntity class
		for(NameValuePair nvp : params) {
			request.addHeader(nvp.getName(), nvp.getValue());
		}
		request.setEntity(entity);
		//now set this entity into request object
		HttpResponse httpResponse=client.execute(request);
		//we will execute the request using defaultHttpClient and then we get HttpResponse
		InputStream is=httpResponse.getEntity().getContent();
		//we will fetch inputStream from this httpResponse object
		InputSource iSource= new InputSource(is);
		//we can also use directly inputStream but it sometimes give streamCorrupted exception.
		//So for reliable data transfer,we should use inputSource class.
		//then we get byte stream from input source and pass it into inputSource object
		//and pass inputStreamReader into bufferedReader
		BufferedReader readFromServer= new BufferedReader(new InputStreamReader(iSource.getByteStream()));
		String temp="";
		while((temp= readFromServer.readLine())!=null){
			response.append(temp);
		}
		Log.e("RES.... " , response+"");
		is.close();
	} catch (Exception e) {
		Log.e("In SendRequest()",e.toString());
	}
	return response.toString();
}

}
