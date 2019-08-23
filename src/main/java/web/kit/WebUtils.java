package web.kit;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WEB工具类：封装了获取请求参数、Cookie等功能。
 */
public class WebUtils {

	/**
	 * Get the request parameter and convert it to a int.
	 */
	public static Integer getParaToInt(HttpServletRequest req, String name) {
		return getParaToInt(req, name, null);
	}

	/**
	 * Get the request parameter and convert it to a int.
	 */
	public static Integer getParaToInt(HttpServletRequest req, String name,
			Integer defaultValue) {
		try {
			return Integer.parseInt(req.getParameter(name));
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Get the request parameter and convert it to a bean.
	 *
	 * @see BeanUtils#populate(java.lang.Object, java.util.Map)
	 */
	public static <T> T getParaToBean(HttpServletRequest req, Class<T> clz) {
		try {
			T bean = clz.getDeclaredConstructor().newInstance();
			BeanUtils.populate(bean, req.getParameterMap());
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get cookie object by cookie name.
	 */
	public static Cookie getCookieObject(HttpServletRequest req, String name) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals(name))
					return cookie;
		return null;
	}

	/**
	 * Get cookie value by cookie name.
	 */
	public static String getCookieValue(HttpServletRequest req, String name) {
		Cookie cookie = getCookieObject(req, name);
		return cookie != null ? cookie.getValue() : null;
	}

	/**
	 * Set Cookie.
	 */
	public static void setCookie(HttpServletResponse resp, String name,
			String value, int maxAgeInSeconds, String path) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAgeInSeconds);
		if (path == null) {
			// set the default path value to "/"
			path = "/";
		}
		cookie.setPath(path);
		resp.addCookie(cookie);
	}

	/**
	 * Remove Cookie.
	 */
	public static void removeCookie(HttpServletResponse resp, String name,
			String path) {
		setCookie(resp, name, null, 0, path);
	}

}
