package web.servlet.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
import web.kit.CookieUtils;

/**
 * 
 * @author gzh
 *
 */
@WebServlet(name = "AccountLoginHandlerServlet", value = "/login")
public class AccountLoginHandlerServlet extends HttpServlet {
    private static final long serialVersionUID = -3492037543200763582L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException, ServiceException {
	resp.setCharacterEncoding("UTF-8");
	req.setCharacterEncoding("UTF-8");

	AccountServletKitUtil instance = AccountServletKitUtil.getInstance();

	String acname = req.getParameter("acname");
	String password = req.getParameter("password");
	System.out.println(acname + "," + password);

	AccountDAO dao = new AccountDAO();
	Account account = dao.selectAccountByAcname(acname);
	System.err.println("account: " + account);

	String descExcept = null;

	try {

	    if (!(account == null)) {

		String originText = account.getPassword();
		boolean verify = instance.verify(password, originText);
		System.err.println("verify: " + verify);
		if (verify) {
		    String profile = account.getAcname() + ":" + account.getPassword();
		    CookieUtils.setCookie(req, resp, "profile", profile, 60 * 60 * 24 * 7);

		    req.getSession().setAttribute("acname", acname);
		    req.getSession().setAttribute("acid", account.getAcid());
		    req.getSession().setAttribute("Κωδικός", password);
		    
		    req.setAttribute("account", account);
		    req.getRequestDispatcher("MinePanel.jsp").forward(req, resp);

		} else {
		    descExcept = ErrorCodeEnum.KEYWORD_ERR.getDesc();

		    System.err.println(descExcept);

		    req.setAttribute("tip", descExcept);
		    req.getRequestDispatcher("login.jsp").forward(req, resp);
		}

	    } else {

		descExcept = ErrorCodeEnum.NOT_EXISTS_ACCOUNT.getDesc();

		System.err.println(descExcept);

		req.setAttribute("tip", descExcept);
		req.getRequestDispatcher("login.jsp").forward(req, resp);

	    }

	} catch (Exception e) {

	    e.printStackTrace();
	    System.err.println(ErrorCodeEnum.SYS_ERROR.getDesc());
	    req.setAttribute("errMsg", ErrorCodeEnum.SYS_ERROR.getDesc());
	    req.getRequestDispatcher("ErrorMessage.jsp").forward(req, resp);

	}

    }

}