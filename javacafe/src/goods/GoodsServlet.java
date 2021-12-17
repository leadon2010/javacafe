package goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import jdbc.CategoryDAO;
import jdbc.CategoryDO;

/**
 * Servlet implementation class EmpServlet DB에 저장되어있는것 보기
 */
@WebServlet("/goods/GoodsServlet")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
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

		} else if (action.equals("updateCategory")) {
			String $id = request.getParameter("category_id");
			String $name = request.getParameter("category_name");
			String $desc = request.getParameter("category_desc");
			System.out.println($id + " " + $name + " " + $desc);
			CategoryDAO catDao = new CategoryDAO();
			CategoryDO cat = new CategoryDO();
			cat.setCategory_id($id);
			cat.setCategory_name($name);
			cat.setCategory_desc($desc);
			catDao.updateCategory(cat);

			response.sendRedirect("GoodsServlet?action=adminCategory");

		} else if (action.equals("insertCategory")) {
			String $id = request.getParameter("category_id");
			String $name = request.getParameter("category_name");
			String $desc = request.getParameter("category_desc");
			System.out.println($id + " " + $name + " " + $desc);
			CategoryDAO catDao = new CategoryDAO();
			CategoryDO cat = new CategoryDO();
			cat.setCategory_id($id);
			cat.setCategory_name($name);
			cat.setCategory_desc($desc);
			catDao.insertCategory(cat);

			response.sendRedirect("GoodsServlet?action=adminCategory");
//			CategoryDAO dao = new CategoryDAO();
//			List<CategoryDO> list = dao.selectAll();
//			request.setAttribute("datas", list);
//			request.getRequestDispatcher("../goods/goodsCategory.jsp").forward(request, response);

		} else if (action.equals("adminCategory")) {
			CategoryDAO dao = new CategoryDAO();
			List<CategoryDO> list = dao.selectAll();
			request.setAttribute("datas", list);
			request.getRequestDispatcher("goodsCategory.jsp").forward(request, response);

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
			ArrayList<CategoryDO> category = categoryDAO.selectAll();
			request.setAttribute("category", category);
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

}
