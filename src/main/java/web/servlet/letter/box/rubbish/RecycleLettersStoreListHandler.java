package web.servlet.letter.box.rubbish;

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
 * 展示回收站里的信件列表
 * 
 * @author gzh
 *
 */
@WebServlet("/RecycleLettersStoreListHandler")
public class RecycleLettersStoreListHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");

	TransmitLetterDAO dao = new TransmitLetterDAO();

	List<TransmitLetter> list = dao.selectTransmitLettersByStatus(0);

	req.setAttribute("list", list);
	req.getRequestDispatcher("RecycleBox.jsp").forward(req, resp);
    }

}