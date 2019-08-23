package web.kit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;

import entity.Account;

/**
 * 将表单传与之字符串转为Account类型
 * 
 * @author gzh
 *
 */
public class FormDataStrToAccountUtil {
    /*
     * static代码块实现单例模式
     */
    private static FormDataStrToAccountUtil instance = null;

    public FormDataStrToAccountUtil() {
    }

    static {
	instance = new FormDataStrToAccountUtil();
    }

    public static FormDataStrToAccountUtil getInstance() {
	return instance;
    }

    /*
     * 以下为具体代码
     */
    private HashMap<String, String> map = new HashMap<String, String>();
    private static HashMap<String, String> map00 = new HashMap<String, String>();

    public Account settsIn(HashMap<String, String> map00) {
	Account t = new Account();

	t.setAcname(map00.get("acname"));
	t.setEmail(map00.get("email"));
	t.setGender(Integer.parseInt(map00.get("gender")));
	t.setPassword(map00.get("password"));

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	try {
	    Date birth = sdf.parse(map00.get("birth"));
	    t.setBirth(birth);
	} catch (ParseException e) {
	    e.printStackTrace();
	}

	System.out.println(t.toString());
	return t;

    }

    // "acname=eyelyn&email=postman&gender=2&password=123123&birth=2019-07-20";
    public HashMap<String, String> generateMap(String string) {
	String[] sp = string.split("&");

	map00 = stringPutsHashMap(sp[0]);
	map00 = stringPutsHashMap(sp[1]);
	map00 = stringPutsHashMap(sp[2]);
	map00 = stringPutsHashMap(sp[3]);
	map00 = stringPutsHashMap(sp[4]);

	System.out.println(map00);
	return map00;

    }

    public HashMap<String, String> stringPutsHashMap(String str) {
	String[] strs = {};
	strs = str.split("=");

	if (strs.length < 2) {
	    map.put(strs[0], "该用户无代理箱子");
	    return map;
	}

	map.put(strs[0], strs[1]);
	return map;
    }

}