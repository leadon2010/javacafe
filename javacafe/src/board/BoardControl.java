package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import hrModel2.Dept;
//import hrModel2.DeptDAO;

/**
 * Servlet implementation class BoardControl
 */
@WebServlet("/fileUpload/BoardControl.do")
public class BoardControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		BoardDAO dao = new BoardDAO();
		Board board = new Board();

		String action = request.getParameter("action");
		if (action == null || action.isEmpty()) {
			System.out.print("잘못된 명령입니다.");
		} else if (action.equals("insForm")) { // 등록폼
			response.sendRedirect("deptIns.tile");
		}
		// 단건조회
		else if (action.equals("sel")) {
			// 단건조회
			Board result = dao.getDB(Integer.parseInt(board.getNo()));
			// 상세보기폼으로
			request.setAttribute("board", result);
			request.getRequestDispatcher("boardSel.jsp").forward(request, response);
		} else if (action.equals("list")) { // 전체처리
			ArrayList<Board> datas = dao.getDBList();
			request.setAttribute("datas", datas);
			request.getRequestDispatcher("boardList.jsp").forward(request, response);
		} else if (action.equals("download")) { // 전체처리
			Board result = dao.getDB(Integer.parseInt(board.getNo()));
			// 응답 헤더 다운로드로 설정
			response.reset();
			int filesize = 0;
			String fileName = new String(board.getOriginalFileName().getBytes("utf-8"), "iso-8859-1");
			String realPath = "d:/upload/" + board.getUploadedFileName();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setContentLength(filesize);
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");

			FileDownloadHelper.copy(realPath, response.getOutputStream());

			response.getOutputStream().close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
