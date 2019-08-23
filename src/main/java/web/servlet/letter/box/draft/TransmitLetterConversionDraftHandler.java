package web.servlet.letter.box.draft;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransmitLetterDAO;

/**
 * Conversion转化<br>
 * 转为草稿
 * 
 * @author gzh
 *
 */
@WebServlet("/transmit_letter_conversion_draft")
public class TransmitLetterConversionDraftHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");

	String tlid = req.getParameter("tlid");
	System.out.println("ConversionDraftHandler=tlid-" + tlid);

	int id = Integer.parseInt(tlid);

	TransmitLetterDAO dao = new TransmitLetterDAO();

	Integer[] ids = { id };

	Integer effect = dao.setStatusByIds(ids, 2);

	if (effect == 1) {
	    req.setAttribute("hint", "存入草稿成功");
	} else {
	    req.setAttribute("hint", "存入草稿失败");
	}

	req.getRequestDispatcher("ManipulateLetterResult.jsp").forward(req, resp);
    }

}