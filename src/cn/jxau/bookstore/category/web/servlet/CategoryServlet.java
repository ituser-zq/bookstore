package cn.jxau.bookstore.category.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.jxau.bookstore.category.service.CategoryService;

public class CategoryServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryService();
	/**
	 * 查询所有分类
	 * @param req
	 * @param res
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("categoryList", categoryService.findAll());
		return "f:/jsps/left.jsp";
	}
}
