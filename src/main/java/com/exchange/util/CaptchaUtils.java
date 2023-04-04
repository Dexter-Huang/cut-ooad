package com.exchange.util;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
public class CaptchaUtils {
	/**
	 * 获取缓存的key
	 * @param request
	 * @return
	 */
	public static String getCaptchaKey(HttpServletRequest request){
		String ip = CaptchaUtils.getIpAddr(request);
		String userAgent = request.getHeader("User-Agent");
		String key = "user-service:captcha:"+ CaptchaUtils.MD5(ip+userAgent);
		return key;
	}
	public static String getCouponCaptchaKey(HttpServletRequest request){
		String ip = CaptchaUtils.getIpAddr(request);
		String userAgent = request.getHeader("User-Agent");
		String key = "coupon-seckill-service:captcha:"+ CaptchaUtils.MD5(ip+userAgent);
		return key;
	}
	public static String getSeckillRoomCaptchaKey(HttpServletRequest request){
		String ip = CaptchaUtils.getIpAddr(request);
		String userAgent = request.getHeader("User-Agent");
		String key = "room-seckill-service:captcha:"+ CaptchaUtils.MD5(ip+userAgent);
		return key;
	}
	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if
				(ipAddress.equals("127.0.0.1")) {
					// 根据⽹卡取本机配置的IP
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress =
							inet.getHostAddress();
				}
			}
			// 对于通过多个代理的情况，第⼀个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null &&
					ipAddress.length() > 15) {
				// "***.***.***.***".length()
				// = 15
				if (ipAddress.indexOf(",") > 0)
				{
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
		} catch (Exception e) {
			ipAddress="";
		}
		return ipAddress;
	}
	public static String MD5(String data) {
		try {
			java.security.MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(data.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (byte item : array) {
				sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString().toUpperCase();
		} catch (Exception exception) {

		}
		return null;
	}
}
