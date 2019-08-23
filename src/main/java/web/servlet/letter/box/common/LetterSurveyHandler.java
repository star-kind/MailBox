package web.servlet.letter.box.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.kit.TransmiterLetterServletUtil;

/**
 * 信件阅览公用转发器
 * 
 * @author gzh
 *
 */
@WebServlet("/LetterSurveyHandler")
public class LetterSurveyHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	resp.setCharacterEncoding("utf-8");

	MoveLettersToBoxHandler handler = new MoveLettersToBoxHandler();

	TransmiterLetterServletUtil instance = TransmiterLetterServletUtil.getInstance();

	Integer letterId = Integer.parseInt(req.getParameter("letter_id"));
	String sourceBox = req.getParameter("sourceBox");
	System.out.println(handler.getClass() + ":letterId:" + letterId + ",sourceBox:" + sourceBox);

	String jsp = ".jsp";

	HashMap<String, Object> map = instance.selectJspWhereSrcBox(sourceBox, letterId);

	req.setAttribute("letter", map.get("letter"));

	req.getRequestDispatcher(map.get("pageName") + jsp).forward(req, resp);
    }

}