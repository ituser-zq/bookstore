package cn.jxau.bookstore.user.service;

import cn.jxau.bookstore.user.dao.UserDao;
import cn.jxau.bookstore.user.domain.User;

/**
 * user业务层
 * @author ZQ
 *
 */
public class UserService {
	private UserDao userDao = new UserDao();
	
	/*
	 * 注册功能
	 */
	public void regist(User form) throws UserException{
		//校验用户名
		User user = userDao.findByUsername(form.getUsername());
		if(user != null)throw new UserException("用户名已注册");
		//校验Email
		user = userDao.finaByEmail(form.getEmail());
		if(user != null)throw new UserException("Email已被注册");
		//插入用户到数据库
		userDao.add(form);
	}
	/**
	 * 激活功能
	 * @param uid
	 * @param state
	 * @throws UserException 
	 */
	public void active(String code) throws UserException{
		//使用code查询数据库得到user
		User user = userDao.findByCode(code);
		//如果user不存在，抛异常
		if(user == null)throw new UserException("该激活码无效！");
		//如果user的状态码存在，抛异常
		if(user.isState())throw new UserException("你以注册，无需再次激活！");
		
		//若都没抛异常，将该状态码修改
		userDao.updateState(user.getUid(), true);
	}
	/**
	 * 登录功能
	 * @param form
	 * @return
	 * @throws UserException
	 */
	public User load(User form) throws UserException{
		/*
		 * 1. 使用username查询，得到User
		 * 2. 如果user为null，抛出异常（用户名不存在）
		 * 3. 比较form和user的密码，若不同，抛出异常（密码错误）
		 * 4. 查看用户的状态，若为false，抛出异常（尚未激活）
		 * 5. 返回user
		 */
		User user = userDao.findByUsername(form.getUsername());
		if(user == null)throw new UserException("用户名不存在");
		if(!user.getPassword().equals(form.getPassword()))
			throw new UserException("密码错误");
		if(!user.isState())throw new UserException("该用户未激活！");
		
		return user;
	}
	
}
