package web.servlet.letter.box.draft;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransmitLetterDAO;
import entity.TransmitLetter;

/**
 * 展示草稿信件列表
 * 
 * @author gzh
 *
 */
@WebServlet("/transmit_letter_show_draft_list")
public class TransmitLetterShowDraftListHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");

	TransmitLetterDAO dao = new TransmitLetterDAO();

	List<TransmitLetter> list = dao.selectTransmitLettersByStatus(2);

	req.setAttribute("list", list);
	req.getRequestDispatcher("DraftBox.jsp").forward(req, resp);
    }

}