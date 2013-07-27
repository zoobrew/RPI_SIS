package com.zoobrew.rpi.sis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Application;
import android.util.Log;

public class HttpHelper extends Application{

	public static final int HTTP_TIMEOUT = 30 * 1000; // milliseconds
	private static DefaultHttpClient mHttpClient = null;
	private static HttpContext mContext = null;
	private static BasicCookieStore mCookieStore = null;
	public static Cookie cookie;


	public HttpHelper(ArrayList<NameValuePair> params){
		initClient();
	}

	public static DefaultHttpClient getClient(){
		initClient();
		return mHttpClient;

	}

	public static DefaultHttpClient returnClient(){
		return mHttpClient;
	}

	private static void initClient()
	{

		if (mHttpClient == null)
		{
			//sets up parameters
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, "utf-8");
			params.setBooleanParameter("http.protocol.expect-continue", false);

			//registers schemes for both http and https
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
			sslSocketFactory.setHostnameVerifier(SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			registry.register(new Scheme("https", sslSocketFactory, 443));

			ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(params, registry);
			mHttpClient = new DefaultHttpClient(manager, params);
		}
		if (mCookieStore == null)
		{

			mCookieStore = new BasicCookieStore();
			mHttpClient.getCookieStore().getCookies();

		}
		if(mContext == null)
		{
			mContext = new BasicHttpContext();
			mContext.setAttribute(ClientContext.COOKIE_STORE, mCookieStore);
		}

	}

	/**
	 * Performs an HTTP Post request to the specified url with the
	 * specified parameters.
	 * 
	 * @param url The web address to post the request to
	 * @param postParameters The parameters to send via the request
	 * @return The result of the request
	 * @throws Exception
	 */
	public static boolean executeHttpPost(final String url, final ArrayList<NameValuePair> postParameters) // throws Exception
	{
		initClient();
		BufferedReader in = null;
		int status = 0;
		String result = null;
		try {
			HttpPost request = new HttpPost(url);
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
			request.setEntity(formEntity);
			//doesn't login on first try
			mHttpClient.execute(request, mContext);
			Log.d("CONTEXT:", mContext.toString());
			Log.d("REQUEST:", request.getAllHeaders().toString());
			HttpResponse response = mHttpClient.execute(request, mContext);
			status = response.getStatusLine().getStatusCode();
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null)
				sb.append(line + NL);
			in.close();

			result = sb.toString();
			Log.d("HTTP RESULT:", result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		Log.d("Http Status:", String.valueOf(status));
		if(result.contains("Login"))
			return false;

		return true;

	}

	/**
	 * Performs an HTTP GET request to the specified url.
	 * 
	 * @param url The web address to post the request to
	 * @return The result of the request
	 * @throws Exception
	 */
	public static String executeHttpGet(String url) throws Exception
	{
		BufferedReader in = null;
		try {
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			mHttpClient.execute(request, mContext);
			HttpResponse response = mHttpClient.execute(request, mContext);

			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null)
				sb.append(line + NL);
			in.close();

			String result = sb.toString();
			return result;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static HttpResponse executeHttpGet2(String url) throws Exception
	{
		BufferedReader in = null;
		try {
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			mHttpClient.execute(request, mContext);
			return mHttpClient.execute(request, mContext);

		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void clearCookies() {

		mCookieStore.clear();

	}

}
