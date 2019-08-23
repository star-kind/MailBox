package web.servlet.letter.box.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import entity.Account;
import web.kit.WebUtils;

/**
 * 跃至邮箱主面板页
 * 
 * @author gzh
 *
 */
@WebServlet("/transmitMailPanel")
public class TransmitMinePanelHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String value = WebUtils.getCookieObject(req, "profile").getValue();
	String key = value.substring(0, value.indexOf(":"));
	System.err.println("account-key=== " + key);

	AccountDAO dao = new AccountDAO();
	Account account = dao.selectAccountByAcname(key);

	req.setAttribute("account", account);
	req.getRequestDispatcher("MinePanel.jsp").forward(req, resp);
    }

}