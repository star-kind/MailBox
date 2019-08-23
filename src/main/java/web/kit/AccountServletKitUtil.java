package web.kit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;

import entity.Account;

/**
 * 帐号模块会用到的一些工具函数类,主要是为了写单例模式
 * 
 * @author gzh
 *
 */
public class AccountServletKitUtil {
	// 饿汉式
	private static AccountServletKitUtil instance = new AccountServletKitUtil();

	private AccountServletKitUtil() {
	}

	public static AccountServletKitUtil getInstance() {
		return instance;
	}

	/**
	 * 成功码
	 */
	public final String successCode = 200 + "";

	/**
	 * 帐号名重复
	 */
	public final String duplicateAccountName = 600 + "";

	/**
	 * 提交之参数未齐全
	 */
	public final String paraNotEnough = 601 + "";

	/**
	 * 清点提交之参数个数,应为4
	 * 
	 * @param a
	 * @return
	 */
	public Integer checkCommittedAccounts(Account a) {
		LinkedList<Object> list = new LinkedList<Object>();

		if (a.getAcname() == null || ("".equals(a.getAcname()))) {
			System.out.println("time for her to pay up");
		} else {
			list.add(a.getAcname());
		}

		if (a.getGender() == null || ("".equals(a.getGender()))) {
			System.out.println("time for her to pay up");
		} else {
			list.add(a.getGender());
		}

		if (a.getPassword() == null || ("".equals(a.getPassword()))) {
			System.out.println("time for her to pay up");
		} else {
			list.add(a.getPassword());
		}

//		if (a.getEmail() == null || ("".equals(a.getEmail()))) {
//			System.out.println("time for her to pay up");
//		} else {
//			list.add(a.getEmail());
//		}

		if (a.getBirth() == null || ("".equals(a.getBirth()))) {
			System.out.println("time for her to pay up");
		} else {
			list.add(a.getBirth());
		}

		return list.size();
	}

	/**
	 * 提取盐
	 *
	 * @return
	 */
	public String extractSalt() {
		Random random = new Random();
		StringBuilder builder = new StringBuilder(16);
		builder.append(random.nextInt(99999999));

		int length = builder.length();

		if (length < 16) {
			for (int i = 0; i < 16 - length; i++) {
				int n = random.nextInt(9);
				builder.append(n + "");
			}
		}

		return builder.toString();
	}

	/**
	 * 获取十六进制字符串形式的MD5摘要(digest)
	 *
	 * @param src
	 * @return
	 */
	private String getMd5Hex(String src) {
		MessageDigest md5 = null;

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] bs = md5.digest(src.getBytes());

		byte[] encode = new Hex().encode(bs);

		return new String(encode);

	}

	/**
	 * 代入页提码,生成并返回密文
	 * 
	 * @param pwd 原始素材
	 * @param salt
	 * @return
	 */
	public String generateKeywordText(String pwd, String salt) {
		// 撒盐,并在MD5hex方法内均匀搅拌
		String hex = getMd5Hex(salt + pwd);

		char[] cs = new char[48];
		// 再加密
		for (int i = 0; i < 48; i += 3) {
			cs[i] = hex.charAt(i / 3 * 2);
			cs[i + 1] = salt.charAt(i / 3);
			cs[i + 2] = hex.charAt(i / 3 * 2 + 1);
		}
		
		return new String(cs);

	}

	/**
	 * 校验加盐后是否和原文一致,逆向解密
	 *
	 * @param password
	 *            提交之密码
	 * @param text
	 *            原文
	 * @return
	 */
	public boolean verify(String password, String text) {
		char[] digestStr = new char[32];
		char[] saltStr = new char[16];

		for (int i = 0; i < 48; i += 3) {
			digestStr[i / 3 * 2] = text.charAt(i);
			digestStr[i / 3 * 2 + 1] = text.charAt(i + 2);

			saltStr[i / 3] = text.charAt(i + 1);
		}

		String salt = new String(saltStr);

		return getMd5Hex(salt + password).equals(new String(digestStr));
		
	}

}