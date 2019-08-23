package web.servlet.letter.box.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransmitLetterDAO;
import web.kit.TransmiterLetterServletUtil;

/**
 * 彻底而真正地删去邮件
 * 
 * @author gzh
 *
 */
@WebServlet("/LetterDeleteHandler")
public class LetterDeleteHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");

	TransmitLetterDAO dao = new TransmitLetterDAO();

	LetterDeleteHandler handler = new LetterDeleteHandler();

	TransmiterLetterServletUtil instance = TransmiterLetterServletUtil.getInstance();

	String idsStr = req.getParameter("ids");

	Integer[] ids = instance.conversion(idsStr);

	Integer effects = dao.deleteLetterByIdArray(ids);
	System.out.println(handler.getClass().getName() + "--effects:" + effects);

    }

}