package web.servlet.letter.box.send;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransmitLetterDAO;
import entity.TransmitLetter;

/**
 * 将即将要修改编辑发信箱里的邮件数据转发至操作页
 * 
 * @author gzh
 *
 */
@WebServlet("/transmiterLetter_dispatch_revamp")
public class TransmitLetterDispatchRevampHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");

	Integer tlid = Integer.valueOf(req.getParameter("tlid"));
	System.out.println("tlid=" + tlid);

	TransmitLetterDAO dao = new TransmitLetterDAO();
	TransmitLetter transmitLetter = dao.selectAnTransmitLetterById(tlid);
	System.out.println(transmitLetter.toString());

	req.setAttribute("transmitLetter", transmitLetter);
	req.getRequestDispatcher("ShowRevampTransmiterLetter.jsp").forward(req, resp);
    }

}