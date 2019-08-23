package web.kit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

import dao.TransmitLetterDAO;
import entity.TransmitLetter;

/**
 * 发信箱servlet工具类
 * 
 * 懒汉式
 * 
 * @author gzh
 *
 */
public class TransmiterLetterServletUtil {
    private volatile static TransmiterLetterServletUtil instance = null;// volatile不稳定的，易变的

    private TransmiterLetterServletUtil() {
    }

    public static TransmiterLetterServletUtil getInstance() {
	if (instance == null) {
	    synchronized (TransmiterLetterServletUtil.class) {
		if (instance == null) {
		    instance = new TransmiterLetterServletUtil();
		}
	    }
	}

	return instance;
    }

    /**
     * 
     * @param param
     * @param status
     * @return
     */
    public Integer revampTransmiterLetter(Map<String, String> param, Integer status) {
	TransmitLetterDAO dao = new TransmitLetterDAO();

	TransmitLetter letter = new TransmitLetter();

	// 箱中信件id
	Integer id = Integer.valueOf(param.get("id"));
	letter.setId(id);

	// 发件者邮箱
	String transmitter = param.get("transmitter");
	letter.setTransmitter(transmitter);

	// 收信者邮箱
	String receiver = param.get("receiver");
	letter.setReceiver(receiver);

	// 标题
	String title = param.get("title");
	letter.setTitle(title);

	// 正文
	String content = param.get("content");
	letter.setContent(content);

	// 状态
	letter.setStatus(status);

	Date now = new Date();

	// 发送时间为现在
	letter.setLaunchTime(now);

	// 上次编辑时间为现在
	letter.setLastEditTime(now);

	// 附件名
	String attach = param.get("attach");
	System.out.println("attach:" + attach);
	letter.setAttachmentFileName(attach);

	Integer row = dao.updateAnTransmitLetterById(letter);
	System.out.println("row:" + row);
	return row;
    }

    /**
     * 截取邮箱主域名
     * 
     * @param postbox 例如:951753@aliyun.com
     * @return
     */
    public String cutOutStr(String postbox) {
	StringBuilder builder = new StringBuilder(postbox);

	int at = builder.indexOf("@");
	int point = builder.lastIndexOf(".");
	System.out.println(at + "," + point);

	String domainName = builder.substring(at + 1, point);
	System.out.println("domainName:" + domainName);

	return domainName;
    }

    /**
     * 根据主域名获取SMTP服务器地址
     * 
     * @param args
     * @return
     */
    public String getServerAddressByName(String args) {

	String domainName = cutOutStr(args);

	EnumPostboxSMTPServer instance = EnumPostboxSMTPServer.INSTANCE;

	// 循环比对
	String serverAddressByName = instance.getServerAddressByName(domainName);

	System.out.println(serverAddressByName);

	return serverAddressByName;
    }

    /**
     * 根据forwardWhichBoxs决定进哪个箱子，即设置不同的状态<br>
     * <p>
     * ("unread");//receiveLetter.status=2
     * </p>
     * <p>
     * ("garbage");//receiveLetter.status=0 || transmitLetter.status=0
     * </p>
     * <p>
     * ("transmited");//transmitLetter.status=1
     * </p>
     * <p>
     * ("received");//receiveLetter.status=1
     * </p>
     * <p>
     * ("draft");//transmitLetter.status=2
     * </p>
     * 
     * @param forwardWhichBoxs
     * @param ids
     * @return
     * @throws ParseException
     */
    public Integer forwardBoxOnCondition(String forwardWhichBoxs, Integer[] ids) {
	int effect = 0;

	TransmitLetterDAO dao = new TransmitLetterDAO();

	String dateFormatToStr = dateFormatToStr(new Date());

	switch (forwardWhichBoxs) {
	case "unread":
	    // TODO 发信表之信进未读之箱
	    break;
	case "garbage":
	    effect = dao.setStatusByIds(ids, 0);
	    effect = dao.updateMoveInRecycleTimeByIds(ids, dateFormatToStr);
	    // TODO 发信表之信进回收站
	    break;
	case "transmited":
	    effect = dao.setStatusByIds(ids, 1);
	    break;
	case "received":
	    // TODO 发信表之信为已读
	    break;
	case "draft":
	    effect = dao.setStatusByIds(ids, 2);
	    break;

	default:
	    System.err.println("effect: " + effect);
	    break;
	}

	return effect;
    }

    /**
     * string conversion as String[],and the next String[] conversion as Integer[]
     * 
     * @param str
     * @return
     */
    public Integer[] conversion(String str) {
	String[] strArr = str.split(",");

	Integer[] integerArr = new Integer[strArr.length];

	for (int i = 0; i < strArr.length; i++) {
	    integerArr[i] = Integer.parseInt(strArr[i]);
	    System.out.print("[i]:" + integerArr[i] + " ");
	}

	return integerArr;
    }

    /**
     * 
     * @param now
     * @return
     * @throws ParseException
     */
    public String dateFormatToStr(Date now) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	return sdf.format(now);
    }

    /**
     * 据箱子而抉择页面
     * 
     * @param sourceBox
     * @param letterId
     * @return
     */
    public HashMap<String, Object> selectJspWhereSrcBox(String sourceBox, Integer letterId) {
	String pageName = null;

	TransmitLetterDAO dao = new TransmitLetterDAO();

	HashMap<String, Object> map = new HashMap<>();

	TransmitLetter transmitLetter = dao.selectAnTransmitLetterById(letterId);
	map.put("letter", transmitLetter);

	switch (sourceBox) {
	case "garbage":
	    pageName = "SurveyThisGarbageLetter";

	    map.put("pageName", pageName);

	    break;

	case "draft":
	    pageName = "SurveyThisDraftLetter";

	    map.put("pageName", pageName);

	    break;
	}

	return map;
    }

}