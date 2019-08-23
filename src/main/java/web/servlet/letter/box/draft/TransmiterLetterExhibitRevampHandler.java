package web.servlet.letter.box.draft;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransmitLetterDAO;
import entity.TransmitLetter;

/**
 * 转发展示即将要被改动的草稿邮件
 * 
 * @author gzh
 *
 */
@WebServlet("/TransmiterLetterExhibitRevampHandler")
public class TransmiterLetterExhibitRevampHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");

	TransmiterLetterExhibitRevampHandler handler = new TransmiterLetterExhibitRevampHandler();

	TransmitLetterDAO dao = new TransmitLetterDAO();

	Integer letterId = Integer.parseInt(req.getParameter("letter_id"));
	System.out.println(handler.getClass() + "=letterId--" + letterId);

	TransmitLetter transmitLetter = dao.selectAnTransmitLetterById(letterId);

	req.setAttribute("transmitLetter", transmitLetter);
	req.getRequestDispatcher("ShowRevampTransmiterLetter.jsp").forward(req, resp);
    }

}