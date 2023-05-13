package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFileDownload
 */
@WebServlet("/board/BoardFileDownload")
public class BoardFileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardFileDownload() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		// 응답 헤더 다운로드로 설정
		response.reset();
		int filesize = 0;
		String file = request.getParameter("filename");
		String fileName = new String(file.getBytes("utf-8"), "iso-8859-1");
		String realPath = "d:/upload/" + file;
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength(filesize);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		FileDownloadHelper.copy(realPath, response.getOutputStream());

		response.getOutputStream().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
