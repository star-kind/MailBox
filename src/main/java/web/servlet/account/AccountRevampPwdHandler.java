package web.servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import entity.Account;
import web.ex.ErrorCodeEnum;
import web.ex.ServiceException;
import web.kit.AccountServletKitUtil;

@WebServlet(name = "AccountRevampPwdHandler", value = "/revamp")
public class AccountRevampPwdHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	res.setCharacterEncoding("UTF-8");

	AccountDAO dao = new AccountDAO();

	AccountServletKitUtil asku = AccountServletKitUtil.getInstance();

	// 从前页获提参
	String oldPassword = req.getParameter("oldPassword");
	String reNewPassword = req.getParameter("reNewPassword");
	System.out.println(oldPassword + " -- " + reNewPassword);

	// 从会话提参
	String acidStr = req.getSession().getAttribute("acid").toString();
	Integer acid = Integer.parseInt(acidStr);
	System.out.println("acid - " + acid);

	Account account = dao.selectAccountByAcid(acid);

	// 对比原密码
	boolean verify = asku.verify(oldPassword, account.getPassword());

	if (!verify) {
	    ServiceException exception = new ServiceException("原密码错误");
	    req.setAttribute("fatalTip", exception.getErrorCode().getDesc());
	    req.getRequestDispatcher("RevampKeyword.jsp").forward(req, res);

	} else {
	    String salt = asku.extractSalt();
	    String keywordText = asku.generateKeywordText(reNewPassword, salt);

	    Integer affect = dao.updatePasswordWhereAcid(acid, keywordText);
	    System.out.println("affect - " + affect);
	    res.sendRedirect("login.jsp");
	}

    }

}