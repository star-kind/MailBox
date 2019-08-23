package web.ex;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 自定义异常类
 * 
 * @author gzh
 *
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /*** 错误码枚举 */
    private ErrorCodeEnum errorCode;

    /**
     * 详细错误信息
     */
    private Map<String, String> errorMap = new HashMap<String, String>();

    /**
     * 带参构造器.
     *
     * @param errorCode
     */
    public ServiceException(ErrorCodeEnum errorCode) {
	super(errorCode.getDesc());
	this.setErrorCode(errorCode);
    }

    /**
     * 带参构造器.
     *
     * @param errorCode
     * @param message
     */
    public ServiceException(ErrorCodeEnum errorCode, String message) {
	super(StringUtils.isNotBlank(message) ? message : errorCode.getDesc());
	this.setErrorCode(errorCode);
    }

    /**
     * 带参构造器.
     *
     * @param errorCode
     * @param errorMap
     */
    public ServiceException(ErrorCodeEnum errorCode, Map<String, String> errorMap) {
	this(errorCode);
	this.errorMap = errorMap;
    }

    /**
     * 带参构造器.
     *
     * @param message
     */
    public ServiceException(String message) {
	super(message);
	this.setErrorCode(ErrorCodeEnum.UNKNOWN_ERROR);
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public ErrorCodeEnum getErrorCode() {
	return errorCode;
    }

    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(ErrorCodeEnum errorCode) {
	this.errorCode = errorCode;
    }

    /**
     * Gets error map.
     *
     * @return the error map
     */
    public Map<String, String> getErrorMap() {
	return errorMap;
    }

    /**
     * Sets error map.
     *
     * @param errorMap the error map
     */
    public void setErrorMap(Map<String, String> errorMap) {
	this.errorMap = errorMap;
    }

    private static String findMessage(Map<String, String> errorMap) {
	if (errorMap.isEmpty()) {
	    return null;
	}
	return errorMap.values().iterator().next();
    }

}