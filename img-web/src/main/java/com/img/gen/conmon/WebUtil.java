package com.img.gen.conmon;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.UrlPathHelper;

/**
 * 
 */
public class WebUtil {

	private final static Logger logger = LoggerFactory.getLogger(WebUtil.class);

	private static UrlPathHelper urlPathHelper = new UrlPathHelper();

	private static PathMatcher pathMatcher = new AntPathMatcher();

	/**
	 * 是否contentType为json请求或者ajax请求
	 * 
	 * @return
	 */
	public static boolean isJsonRequest(HttpServletRequest req) {
		String contentType = req.getContentType();
		if (StringHelper.contains(contentType, "json")) {
			return true;
		}
		String requestType = req.getHeader("X-Requested-With");
		if (StringHelper.isNotEmpty(requestType)) {
			return true;
		}
		// 上传文件是ajax 的请求， 对应的contentType 为  Content-Type:multipart/form-data; boundary=----WebKitFormBoundaryugckYFBKEyzA6etX
		if (StringHelper.contains(contentType, "multipart/form-data")) {
			return true;
		}
		return false;
	}

	public final static String REQ_URL_PATH = "REQ_URL_PATH";

	/**
	 * 获取用户访问 web访问path路径
	 * 
	 * @param req
	 * @return
	 */
	public static String getPath(HttpServletRequest request) {
		String path = (String) request.getAttribute(REQ_URL_PATH);
		if (StringHelper.isEmpty(path)) {
			path = urlPathHelper.getPathWithinApplication(request);
			request.setAttribute(REQ_URL_PATH, path);
		}
		return path;
	}

	/**
	 * 类似 ant的匹配模式 例如 /test/* 可以匹配所有 /test/a.do 等路径
	 * 支持**配置
	 * 支持*.jsp   
	 * 详细，查看  org.springframework.util.AntPathMatcher.AntPathMatcher()
	 * @param pattern
	 * @param lookupPath
	 * @return
	 */
	public static boolean match(String pattern, String lookupPath) {
		return pathMatcher.match(pattern, lookupPath);
	}

	/**
	 * http post 发送请求,每次请求关闭资源，不保持连接
	 * 
	 * @param url
	 * @param requestBody
	 * @return
	 */
	public static String post(String url, String requestBody) {
		HttpClient client = null;
		HttpPost httpPost = null;
		HttpResponse response;
		try {
			httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(requestBody, "UTF-8"));
			httpPost.addHeader("Accept-Charset", "utf-8");
			httpPost.addHeader("Accept", "application/json");
			httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
			client = new DefaultHttpClient();
			response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String str = EntityUtils.toString(entity, "UTF-8");
			return str;
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (httpPost != null) {
				httpPost.abort();
			}
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return null;
	}

	// jar 包冲突，无法试用 http 4.4.x api
	// public static String post(String url,String requestBody){
	// CloseableHttpClient httpclient = HttpClients.createDefault();
	// HttpPost httpPost = new HttpPost(url);
	// httpPost.setEntity(new StringEntity(requestBody, "UTF-8"));
	// httpPost.addHeader("Accept-Charset", "utf-8");
	// httpPost.addHeader("Accept", "application/json");
	// httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
	// CloseableHttpResponse response = null;
	// try {
	// response = httpclient.execute(httpPost);
	// HttpEntity entity = response.getEntity();
	// return EntityUtils.toString(entity, "UTF-8");
	// } catch (Exception e) {
	// logger.error("",e);
	// throw new
	// BusinessException(FilterExceptionCode.ERROR_UNKOWNER.getCode(),null);
	// }finally{
	// if(response!=null){
	// try {
	// response.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// }
	
	/**
	 * 获取真实ip ，经过f5 或者 nginx 分发后，获取实际的访问ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return "unknown";
		}
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void main(String[] args) {

	}

}
