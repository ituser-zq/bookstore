package cn.jxau.bookstore.category.service;

import java.util.List;

import cn.jxau.bookstore.book.dao.BookDao;
import cn.jxau.bookstore.category.dao.CategoryDao;
import cn.jxau.bookstore.category.domain.Category;
import cn.jxau.bookstore.category.web.servlet.admin.CategoryException;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();

	/**
	 * 查询所有分类
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/**
	 * 添加分类
	 * 
	 * @param category
	 */
	public void add(Category category) {
		categoryDao.add(category);

	}

	/**
	 * 删除指定分类
	 * 
	 * @param cid
	 * @throws CategoryException
	 */
	public void delete(String cid) throws CategoryException {
		// 获取分类的图书本书
		int count = bookDao.getCountByCid(cid);
		// 判断图书本书是否为0，不为0不予删除
		if (count > 0)
			throw new CategoryException("该分类存在图书，不允许删除！");
		// 删除该分类
		categoryDao.delete(cid);
	}

	/**
	 * 加载分类
	 * 
	 * @param cid
	 * @return
	 */
	public Category load(String cid) {
		return categoryDao.load(cid);
	}
/**
 * 修改分类
 * @param category
 */
	public void edit(Category category) {
		categoryDao.edit(category);

	}
}
