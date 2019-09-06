package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class EmpServlet DB에 저장되어있는것 보기
 */
@WebServlet("/goods/GoodsServlet")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 인코딩
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		// 내장 객체
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		GoodsDAO goodsDAO = new GoodsDAO();
		GoodsDO goodsDO = new GoodsDO();

		try {
			BeanUtils.copyProperties(goodsDO, request.getParameterMap()); // 앞단 화면의 뷰에서 여기에 담는다
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// action에서 따라서 처리
		String action = request.getParameter("action");
		System.out.println("action===" + action);
		
		if (action == null) {
			out.println("action이 null입니다.");

		} else if (action.equals("adminGoodsForm")) { // 상세보기 페이지
			GoodsDO gds = goodsDAO.selectOne(goodsDO.getProd_no());
			request.setAttribute("goods", gds);
			request.getRequestDispatcher("../goods/adminGoodsForm.jsp").forward(request, response);

		} else if (action.equals("goodsForm")) {
			GoodsDO gds = goodsDAO.selectOne(goodsDO.getProd_no());
			request.setAttribute("goods", gds);
			request.getRequestDispatcher("../goods/goodsForm.jsp").forward(request, response);

		} else if (action.equals("adminCategory")) {
			request.setAttribute("datas", goodsDAO.selectCategory());

		} else if (action.equals("adminGoodsList")) {
			if (goodsDO.getProd_category() == null) {
				goodsDO.setProd_category("");
			}
			request.setAttribute("datas", goodsDAO.selectAll(goodsDO.getProd_category()));
			request.getRequestDispatcher("adminGoodsList.jsp").forward(request, response);

		} else if (action.equals("goodsList")) {
			if (goodsDO.getProd_category() == null) {
				goodsDO.setProd_category("");
			}
			request.setAttribute("datas", goodsDAO.selectAll(goodsDO.getProd_category()));
			request.getRequestDispatcher("goodsList.jsp").forward(request, response);

		} else if (action.equals("goodsConfirm")) {
			request.getRequestDispatcher("goodsConfirm.jsp").forward(request, response);

		} else if (action.equals("goodsOrder")) {

		} else if (action.equals("cartCheck")) {
			request.getRequestDispatcher("cart.jsp").forward(request, response);

		} else if (action.equals("cartOrder")) {

		} else if (action.equals("adminGoodsRegisterForm")) {
			CategoryDAO categoryDAO = new CategoryDAO();
			ArrayList<CategoryDO> cate = categoryDAO.selectAll();
			request.setAttribute("cate", cate);
			request.getRequestDispatcher("adminGoodsRegister.jsp").forward(request, response);

		} else if (action.equals("adminGoodsRegister")) {
			goodsDAO.insert(goodsDO);
			response.sendRedirect("GoodsServlet?action=goodsList");

		} else if (action.equals("adminGoodsCorrectForm")) {
			GoodsDO gds = goodsDAO.selectOne(goodsDO.getProd_no());
			request.setAttribute("goods", gds);
			request.getRequestDispatcher("adminGoodsCorrect.jsp").forward(request, response);

		} else if (action.equals("adminGoodsCorrect")) {
			goodsDAO.update(goodsDO);
			response.sendRedirect("GoodsServlet?action=goodsList");

		} else if (action.equals("adminGoodsDelete")) {

		} else {
			out.println("없는 action 입니다.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
