package web.servlet.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import entity.Account;
import web.kit.AccountServletKitUtil;
import web.kit.FormDataStrToAccountUtil;
import web.kit.WebUtils;

/**
 * 新增帐号,可为注册
 * 
 * @author gzh
 *
 */
@WebServlet(name = "AccountHandlerSaveServlet", value = "/reg")
public class AccountHandlerSaveServlet extends HttpServlet {
    private static final long serialVersionUID = 4180860418047663595L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	AccountServletKitUtil instance = AccountServletKitUtil.getInstance();
	FormDataStrToAccountUtil statInst = FormDataStrToAccountUtil.getInstance();

	AccountDAO dao = new AccountDAO();
	PrintWriter writer = resp.getWriter();

	// 从request请求内取出数据
	String acc = URLDecoder.decode(req.getParameter("account"), "UTF-8");
	System.out.println("received: " + acc);

	Account bean = new Account();
	
	try {
	    HashMap<String, String> map = statInst.generateMap(acc);
	    bean = statInst.settsIn(map);
	} catch (Exception e) {
	    e.printStackTrace();
	    writer.print(instance.paraNotEnough);
	    return;
	}

	// 检查应提交的参数是否齐全
	Integer integer = instance.checkCommittedAccounts(bean);
	if (integer < 4) {
	    writer.print(instance.paraNotEnough);
	    return;
	}

	// 检查账户名是否重复
	Account account = dao.selectAccountByAcname(bean.getAcname());
	if (!(account == null)) {
	    writer.print(instance.duplicateAccountName);
	    return;
	}

	// 生成注册时间并注入bean
	bean.setRegTime(new Date());

	// 获取盐值
	String salt = instance.extractSalt();
	bean.setSalt(salt);

	// 密码加密
	String keywordText = instance.generateKeywordText(bean.getPassword(), salt);
	bean.setPassword(keywordText);

	Integer rows = dao.insertNewAccount(bean);
	System.out.println(rows);

	if (rows == 1) {
	    writer.print(instance.successCode);
	}

	writer.close();
    }

}