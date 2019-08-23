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
 * 勘察信件详情
 * 
 * @author gzh
 *
 */
@WebServlet("/survey_this_letter")
public class TransmitLetterSurveyHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	
	TransmitLetterDAO dao = new TransmitLetterDAO();
	
	Integer tlid = Integer.parseInt(req.getParameter("letter_id"));
	System.out.println("TransmiterLetter-id:" + tlid);
	
	TransmitLetter letter = dao.selectAnTransmitLetterById(tlid);
	System.out.println("letter.toString()-"+letter.toString());
	
	req.setAttribute("transmitedletter", letter);
	req.getRequestDispatcher("TransmiterLetterSurvey.jsp").forward(req, resp);
    }

}