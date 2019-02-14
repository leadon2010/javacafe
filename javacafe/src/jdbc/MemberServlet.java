package jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/members/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		request.setCharacterEncoding("utf-8");

		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		MembersDO usrdo = new MembersDO();
		MembersDAO usrdao = new MembersDAO();
		try {
			BeanUtils.copyProperties(usrdo, request.getParameterMap());

		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("here");
		} finally {

		}
		String action = request.getParameter("action");

		if (action == null) {
			try {
				throw new Exception("action is null");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("home")) {
			MembersDO newusr = usrdao.selectOne(usrdo.getUser_no());
			request.setAttribute("usrdo", newusr);

			if (newusr.getUser_no() != null) {
				request.getRequestDispatcher("home.jsp").forward(request, response);
			} else {
				response.sendRedirect("home.jsp");
			}

		} else if (action.equals("insert")) {
			System.out.println("insert start in MemberServlet.");
			if (usrdao.insert(usrdo)) {
				response.sendRedirect("MemberServlet?action=home&user_no=" + usrdo.getUser_no());

			} else {
				try {
					throw new Exception("error insert");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} else if (action.equals("login")) {
			/*if (usrdao.login(usrdo.getUser_no(), usrdo.getPassword())) {
				response.sendRedirect("MemberServlet?action=home&user_no=" + usrdo.getUser_no());

			} else {
				response.sendRedirect("login.jsp");

			}*/

		} else if (action.equals("logout")) {
			System.out.println("logout");
			session.invalidate();
			response.sendRedirect("home.jsp");

		} else {
			try {
				throw new Exception("no action.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}// end of doGet

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
