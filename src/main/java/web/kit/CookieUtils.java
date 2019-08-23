package web.kit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 */
public class CookieUtils {
    /**
     * 根据cookie名称获取cookie的值
     * 
     * @param request
     * @param name
     * @return {@link String}
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
	Cookie cookie = getCookie(request, name);
	if (cookie != null) {
	    return cookie.getValue();
	}
	return null;
    }

    /**
     * 根据cookie的名称获取cookie
     * 
     * @param request
     * @param name
     * @return {@link Cookie}
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
	if (name == null || name.trim().length() == 0) {
	    return null;
	}
	Cookie[] cookies = request.getCookies();
	if (cookies == null || cookies.length == 0) {
	    return null;
	}
	for (int i = 0; i < cookies.length; i++) {
	    if (name.equals(cookies[i].getName())) {
		return cookies[i];
	    }
	}
	return null;
    }

    /**
     * 删除cookie
     * 
     * @param request
     * @param response
     * @param cookie
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, Cookie cookie) {
	if (cookie != null) {
	    cookie.setValue("");
	    cookie.setMaxAge(0);
	    cookie.setPath(getPath(request));
	    response.addCookie(cookie);
	}
    }

    /**
     * 设置cookie
     * 
     * @param request
     * @param response
     * @param name
     * @param value
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
	setCookie(request, response, name, value, 0x278d00);
    }

    /**
     * 设置cookie
     * 
     * @param request
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
	    int maxAge) {
	if (name == null) {
	    return;
	}
	Cookie cookie = new Cookie(name, value == null ? "" : value.replaceAll("\r\n", ""));
	cookie.setMaxAge(maxAge);
	cookie.setPath(getPath(request));
	response.addCookie(cookie);
    }

    /**
     * 获取路径
     * 
     * @param request
     * @return
     */
    private static String getPath(HttpServletRequest request) {
	String path = request.getContextPath();
	return (path == null || path.trim().length() == 0) ? "/" : path;
    }

}
