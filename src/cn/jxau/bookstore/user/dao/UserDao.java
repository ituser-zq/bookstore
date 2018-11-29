package cn.jxau.bookstore.user.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.jxau.bookstore.user.domain.User;

/**
 * user持久层
 * @author ZQ
 *
 */
public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	/*
	 * 通过用户名查询
	 */
	public User findByUsername(String username){
		try {
			String sql = "select * from tb_user where username = ?";
			return qr.query(sql, new BeanHandler<User>(User.class),username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过email查询
	 */
	public User finaByEmail(String email){
		try {
			String sql ="select * from tb_user where email = ?";
			return qr.query(sql, new BeanHandler<User>(User.class),email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
	/*
	 * 插入一个user
	 */
	public void add(User user){
		try {
			String sql = "insert into tb_user values(?,?,?,?,?,?)";
			Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),
					user.getEmail(),user.getCode(),user.isState()};
			qr.update(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过code查询数据库
	 */
	public User findByCode(String code){
		try {
			String sql = "select * from tb_user where code = ?";
			return qr.query(sql, new BeanHandler<User>(User.class),code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 修改state状态码
	 */
	public void updateState(String uid,boolean state){
		try {
			String sql = "update tb_user set state=? where uid=?";
			qr.update(sql, state, uid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
