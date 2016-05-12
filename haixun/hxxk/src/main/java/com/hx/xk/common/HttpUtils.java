package com.hx.xk.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.http.client.HttpClient;

public abstract class HttpUtils {
	public final static <T> T post(Class<T> t, List<? extends NameValuePair> params, String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// List<NameValuePair> params = getParamsList(parameterMap);
		UrlEncodedFormEntity formEntity = null;
		HttpPost post = null;
		CloseableHttpResponse response = null;
		String res = null;
		post = new HttpPost(url);
		try {
			formEntity = new UrlEncodedFormEntity(params, "UTF-8");
			post.setEntity(formEntity);
			response = httpClient.execute(post);
			res = EntityUtils.toString(response.getEntity());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeResponse(response);
		}
		return JSON.parseObject(res, t);
	}

	public final static <T> T post(Class<T> reClass, String data, String contentType, String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = null;
		String res = null;
		post = new HttpPost(url);

		try {
			StringEntity myEntity = new StringEntity(data, ContentType.create(contentType, "UTF-8"));
			post.setEntity(myEntity);

			HttpResponse response = httpClient.execute(post);
			res = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return JSON.parseObject(res, reClass);
	}

	public final static String post(String data, String contentType, String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = null;
		String res = null;
		post = new HttpPost(url);

		try {
			StringEntity myEntity = new StringEntity(data, ContentType.create(contentType, "UTF-8"));
			post.setEntity(myEntity);

			HttpResponse response = httpClient.execute(post);
			res = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return res;
	}

	public final static String get(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		// get.addHeader("Content-type" , "text/html; charset=GBK");

		CloseableHttpResponse response = null;
		String res = null;
		try {
			// get.setURI(new URI(get.getURI().toString() + "?" +
			// URLEncoder.encode(data, "UTF-8")));// data));
			response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String charset = "UTF-8";
			charset = getContentCharSet(entity);//解决中文乱码问题ok
			res = EntityUtils.toString(entity, charset);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
			// } catch (URISyntaxException e) {
			// throw new RuntimeException(e);
		} finally {
			closeResponse(response);
		}
		return res;
	}

	public final static <T> T get(Class<T> t, List<? extends NameValuePair> params, String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = null;
		String res = null;
		try {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
			get.setURI(new URI(get.getURI().toString() + "?" + str));
			response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			res = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		} finally {
			closeResponse(response);
		}
		return JSON.parseObject(res, t);
	}

	private static void closeResponse(CloseableHttpResponse response) {
		if (response != null) {
			try {
				response.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static String getContentCharSet(final HttpEntity entity) throws ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}

		if (StringUtils.isNull(charset)) {
			charset = "UTF-8";
		}
		return charset;
	}
}
