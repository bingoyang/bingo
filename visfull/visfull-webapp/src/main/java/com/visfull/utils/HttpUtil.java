package com.visfull.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpStatusCodeException;

public class HttpUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HttpUtil.class);

	private HttpUtil() {
	}

	/**
	 * post data to a given url.
	 * 
	 * @param url
	 * @param nameValuePairs
	 * @return
	 * @throws HttpStatusException
	 * @throws IOException
	 */
	public static String post(final String url,
			Map<String, String> nameValuePairs) throws HttpStatusException,
			IOException {
		return post(url, nameValuePairs, HTTP.UTF_8);
	}

	/**
	 * post data to a given url.
	 * 
	 * @param url
	 * @param nameValuePairs
	 * @return
	 * @throws HttpStatusException
	 * @throws IOException
	 */
	public static String post(final String url,
			Map<String, String> nameValuePairs, final String charset)
			throws HttpStatusException, IOException {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("url:" + url);
			for (Entry<String, String> e : nameValuePairs.entrySet()) {
				LOGGER.info("params key:" + e.getKey() + ",value:"
						+ e.getValue());
			}
		}
		HttpPost httpost = new HttpPost(url);
		try {
			if (nameValuePairs.size() > 0) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>(
						nameValuePairs.size());
				for (Entry<String, String> e : nameValuePairs.entrySet()) {
					nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
				}

				httpost.setEntity(new UrlEncodedFormEntity(nvps, charset));
			}

			return execute(createHttpClient(charset), httpost, charset, null);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("UnsupportedEncodingException, url:" + url, e);
		} catch (HttpStatusException e) {
			LOGGER.error("response status code:" + e.getStatusCode() + ",body:"
					+ e.getBody(), e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		return "";
	}

	public static String postByCookie(final String url,
			Map<String, String> nameValuePairs, final String charset,
			HttpContext httpContext) {

		HttpPost httpost = new HttpPost(url);
		try {
			if (nameValuePairs.size() > 0) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>(
						nameValuePairs.size());
				for (Entry<String, String> e : nameValuePairs.entrySet()) {
					nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
				}
				httpost.setEntity(new UrlEncodedFormEntity(nvps, charset));
			}
			return execute(createHttpClient(charset), httpost, charset,
					httpContext);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("UnsupportedEncodingException, url:" + url, e);
		} catch (HttpStatusException e) {
			LOGGER.error("response status code:" + e.getStatusCode() + ",body:"
					+ e.getBody(), e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		return "";
	}

	/**
	 * get data from a given url.
	 * 
	 * @param url
	 * @return
	 * @throws HttpStatusException
	 * @throws IOException
	 */
	public static String get(final String url) throws HttpStatusException,
			IOException {
		return execute(createHttpClient(HTTP.UTF_8), new HttpGet(url),
				HTTP.UTF_8, null);
	}

	/**
	 * get data from a given url.
	 * 
	 * @param url
	 * @param charset
	 * @return
	 * @throws HttpStatusException
	 * @throws IOException
	 */
	public static String get(final String url, final String charset)
			throws HttpStatusException, IOException {
		return execute(createHttpClient(charset), new HttpGet(url), charset,
				null);
	}

	private static String execute(final HttpClient httpclient,
			final HttpUriRequest request, final String charset,
			HttpContext httpContext) throws HttpStatusException, IOException {
		try {
			HttpResponse response = null;
			if (httpContext != null) {
				response = httpclient.execute(request, httpContext);
			} else {
				response = httpclient.execute(request);
			}
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				int statusCode = response.getStatusLine().getStatusCode();

				String body = EntityUtils.toString(entity, charset);

				if (statusCode == HttpStatus.SC_OK) {
					return body;
				} else {
					throw new HttpStatusException(statusCode, body);
				}
			} else {
				throw new IOException("get null content for this request");
			}
		} catch (ClientProtocolException e) {
			LOGGER.error(
					"ClientProtocolException:" + request.getProtocolVersion(),
					e);
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	private static HttpClient createHttpClient(final String charset) {
		return createHttpClient(charset, false);
	}

	private static HttpClient createHttpClient(final String charset,
			boolean isGZIP) {

		HttpParams params = new SyncBasicHttpParams();
		HttpProtocolParams.setContentCharset(params, charset);

		DefaultHttpClient httpclient = new DefaultHttpClient(params);

		if (isGZIP) {
			httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
				public void process(final HttpRequest request,
						final HttpContext context) throws HttpException,
						IOException {
					if (!request.containsHeader("Accept-Encoding")) {
						request.addHeader("Accept-Encoding", "gzip");
					}
				}
			});

			httpclient.addResponseInterceptor(new HttpResponseInterceptor() {
				public void process(final HttpResponse response,
						final HttpContext context) throws HttpException,
						IOException {
					HttpEntity entity = response.getEntity();
					Header ceheader = entity.getContentEncoding();
					if (ceheader != null) {
						HeaderElement[] codecs = ceheader.getElements();
						for (int i = 0; i < codecs.length; i++) {
							if (codecs[i].getName().equalsIgnoreCase("gzip")) {
								response.setEntity(new GzipDecompressingEntity(
										response.getEntity()));
								return;
							}
						}
					}
				}
			});
		}

		return httpclient;
	}

	public static String postXmlEntity(final String url, final String xml)
			throws HttpStatusCodeException, IOException {
		return postEntity(url, xml, HTTP.UTF_8, "text/xml",
				true);
	}

	/**
	 * post data to a given url.
	 * 
	 * @param url
	 * @param xml
	 * @param charset
	 * @param contentType
	 * @param isChunked
	 * @return
	 * @throws HttpStatusCodeException
	 * @throws IOException
	 */
	public static String postEntity(final String url, final String xml,
			final String charset, final String contentType, boolean isChunked)
			throws HttpStatusCodeException, IOException {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("url:" + url);
			LOGGER.info("params xml body:" + xml);
		}
		HttpPost httpost = new HttpPost(url);
		try {
			if (StringUtils.isNotBlank(xml)) {
				StringEntity reqEntity = new StringEntity(xml, charset);
				reqEntity.setContentType(contentType);
				reqEntity.setChunked(isChunked);
				httpost.setEntity(reqEntity);
			}

			return execute(createHttpClient(charset), httpost, charset);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("UnsupportedEncodingException, url:" + url, e);
		} catch (Exception e) {
			LOGGER.error("IOException", e);
		}
		return "";
	}

	private static String execute(final HttpClient httpclient,
			final HttpUriRequest request, final String charset)
			throws Exception {
		if (LOGGER.isDebugEnabled()) {

		}
		try {
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				int statusCode = response.getStatusLine().getStatusCode();

				String body = EntityUtils.toString(entity, charset);

				if (statusCode == HttpStatus.SC_OK) {
					return body;
				} else {
					throw new Exception(body);
				}
			} else {
				throw new IOException("get null content for this request");
			}
		} catch (ClientProtocolException e) {
			LOGGER.error(
					"ClientProtocolException:" + request.getProtocolVersion(),
					e);
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	public static void main(String[] args) throws HttpStatusException,
			IOException {
		Map<String, String> nameValuePairs = new HashMap<String, String>();
		 String jsonString = "{'serverName': '杨柳冰sssssssss','serverCode': 'dddddddddd','gender': 'MALE','age': 10,'identity': 'ddddddddddd','province': 'dddddddddd','phone': 'ddddddddddd','skillIntroduce': 'dddddddddd','spId': 7,'status': 'NORMAL','posters': [{'id': 1,'owner': 4,'posterUrl': 'http://www.sohu.com','posterDesc': 'good good!','createDate': '2013-01-26 00:31:33'}]}";//"[{'id': 4,'customerPhone': '客户电话号码33333','targetCode': '1111','targetName': '服务人员名称3333333','posters': [{'id': 1,'owner': 4,'posterUrl': 'http://www.sina.com','posterDesc': 'good good!','createDate': '2013-01-26 00:31:33'}]}]";//"{targetType=SERVER, targetId=5, customerServiceInfo=[{'customerCode':'13911014449','customerPhone':'13911014449','serviceDate':'2013-01-25 15:11:11','mIsLoad':true,'iTop':0,'bModified':0,'submit':0,'posters':[{'posterUrl':'http://www.baidu.com','posterDesc':'good good!'}]}], targetName=13911111111}";//"{targetId=5, customerServiceInfo=[{'customerPhone':'13121318129','serviceDate':'2013-01-11 17:57:18'}], targetName='asdasd', targetType=SERVER} ";//"[{'callingNum':'callingnum0','calledNum':'callednum0','callingTime':1358050986328,'callType':'CALLIN'},{'callingNum':'callingnum1','calledNum':'callednum1','callingTime':1358050986328,'callType':'CALLIN'},{'callingNum':'callingnum2','calledNum':'callednum2','callingTime':1358050986328,'callType':'CALLIN'},{'callingNum':'callingnum3','calledNum':'callednum3','callingTime':1358050986328,'callType':'CALLIN'}]";//"{'serverName':'刘德华','serverCode':'dddddddddd','age':10,'identity':'ddddddddddd','province':'dddddddddd','phone':'ddddddddddd','skillIntroduce':'dddddddddd','spId':7,'status':'NORMAL','modifyTime':'2012-12-13 15:47:26','createDate':'2012-12-13 15:47:25'}";//"{'serverCode': '13911111111','pwd': '000000',  'serverName': '  sss       ','gender': 'MALE','age': 10,'identity: '33333333333','province': 'shanxi','phone': '1380000','skillIntroduce': 'dddddddddd','spId': 9,'status': 'NORMAL'}";//"{'id':3,'opName':'ssssssssssd','opHours':'cccccccccc','opPoster':'http:\\\\114.247.94.161:8090\\visfull-site\\posters\\1354963867062.jpg','opHomepage':'sssssssssss','opPhone':'ccccccccccc','opAddress':'cccccccccccc','opMotto':'ssssssssss','opServiceintroduce':'cccccccccccccccccc','status':'NORMAL','initDate':'2012-12-08 00:00:00','joinDate':'2012-12-08 00:00:00','modifyTime':'2012-12-08 18:51:18','createDate':'2012-12-08 18:51:18'}";//"{'id':4,'serverName':'刘德华','serverCode':'dddddddddd','age':10,'identity':'ddddddddddd','province':'dddddddddd','phone':'ddddddddddd','skillIntroduce':'dddddddddd','spId':7,'status':'NORMAL','modifyTime':'2012-12-13 15:47:26','createDate':'2012-12-13 15:47:25'}";
		// "{'targetId':10,'targetName':'服务人员名称','targetType':'SERVER','customerServiceInfo':[{'customerName':'客户1','customerPhone':'客户电话号码','serviceInfo':'客户服务相关描述','serviceDate':'2012-12-01 21:21:38'},{'customerName':'客户2','customerPhone':'客户电话号码','serviceInfo':'客户服务相关描述','serviceDate':'2012-12-01 21:21:38'},{'customerName':'客户3','customerPhone':'客户电话号码','serviceInfo':'客户服务相关描述','serviceDate':'2012-12-01 21:21:38'}]}";
		
//		 CookieStore cookieStore = new BasicCookieStore();
//		 HttpContext httpContext = new BasicHttpContext();
//		 httpContext.setAttribute(ClientContext.COOKIE_STORE,cookieStore);
//		
//		 nameValuePairs.put("code", "dddddddddd111");
//		 nameValuePairs.put("password", "123123");
//		 postByCookie("http://127.0.0.1:8080/visfull-webapp/app/login",
//		 nameValuePairs,HTTP.UTF_8,httpContext);
//		 nameValuePairs.clear();
		 
		 
//		 nameValuePairs.put("targetCode", "13911111111");
//		 nameValuePairs.put("startDate", "2013-01-28 00:00:00");
//		 nameValuePairs.put("endDate", "2013-01-31 23:59:59");
//		 nameValuePairs.put("targetType", "SERVER");
//		 postByCookie("http://114.247.94.161:8080/visfull-webapp/findservicerecord",
//		 nameValuePairs,HTTP.UTF_8,httpContext);
//		 nameValuePairs.clear();
		 
//		 nameValuePairs.put("jsonString", jsonString);
//		 postByCookie("http://127.0.0.1:8080/visfull-webapp/app/addserver",
//				 nameValuePairs,HTTP.UTF_8,httpContext);
		 
//		 nameValuePairs.put("phone", "13911014449");
//		 postByCookie("http://114.247.94.161:8080/visfull-webapp/findbinderrecord",
//		 nameValuePairs,HTTP.UTF_8,httpContext);
//		 nameValuePairs.clear();
		 
//		 postByCookie("http://127.0.0.1:8080/visfull-webapp/app/servers/7",
//		 nameValuePairs,HTTP.UTF_8,httpContext);
//		 nameValuePairs.clear();
		 
//		 nameValuePairs.put("jsonString", jsonString);
//		 postByCookie("http://127.0.0.1:8080/visfull-webapp/uploadservicerecord",
//				 nameValuePairs,HTTP.UTF_8,httpContext);
		 
//		File file = new File("C:\\3.jpg"); 
//		Thumbnails.of(file).size(200,200)
//			.toFile(new File(file.getPath().replace(file.getName(), "s_"+file.getName())));
		uploadFile("http://114.247.94.161:8080/visfull-webapp/file/upload","C:\\3.jpg","123");
		 
//		String data = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><File Version='1.0'><DeviceNumber>3</DeviceNumber><DeviceInfo><Device ID='060126002001031' AI='12624854569114659682'/><Device ID='060126002001032' AI='27714507779657347844'/><Device ID='060126002001033' AI='94273108631275163397'/></DeviceInfo></File>";
//		postXmlEntity("http://10.168.64.14:8000/prodrm/reg-batch",data);
	}
	
    /* 上传文件至Server的方法 */
	private static void uploadFile(String actionUrl,String uploadFile,String newName) {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		try {
			URL url = new URL(actionUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			/* 允许Input、Output，不使用Cache */
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			/* 设置传送的method=POST */
			con.setRequestMethod("POST");
			/* setRequestProperty */
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			/* 设置DataOutputStream */
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; "
					+ "name=\"file\";filename=\"" + newName + "\"" + end);
			ds.writeBytes(end);

			/* 取得文件的FileInputStream */
			FileInputStream fStream = new FileInputStream(uploadFile);
			/* 设置每次写入1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			int length = -1;
			/* 从文件读取数据至缓冲区 */
			while ((length = fStream.read(buffer)) != -1) {
				/* 将资料写入DataOutputStream中 */
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

			/* close streams */
			fStream.close();
			ds.flush();

			/* 取得Response内容 */
			   InputStream in = con.getInputStream();  
			   BufferedReader breader = new BufferedReader(new InputStreamReader(in, "utf-8"));  
			   String resp = "";
			   String str = breader.readLine();  
			   while (str != null) {  
			    resp+=str;  
			    str= breader.readLine();  
			   }  
			/* 将Response显示于Dialog */
			System.out.println("上传成功" + resp.toString().trim());
			/* 关闭DataOutputStream */
			ds.close();
		} catch (Exception e) {
			System.out.println("上传失败" + e);
		}
	}
}
