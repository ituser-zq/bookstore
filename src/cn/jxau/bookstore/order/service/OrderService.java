package cn.jxau.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;
import cn.jxau.bookstore.order.dao.OrderDao;
import cn.jxau.bookstore.order.domain.Order;

/**
 * 添加订单 处理事务，解决数据异常问题
 * 
 * @author ZQ
 *
 */
public class OrderService {
	private OrderDao orderDao = new OrderDao();

	public void add(Order order) {
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			orderDao.addOrder(order);// 添加订单
			orderDao.addOrderItemList(order.getOrderItemList());
			// 提交事务
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				// 回滚事务
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 我的订单
	 * 
	 * @param uid
	 * @return
	 */
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}

	/**
	 * 加载订单
	 * 
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		return orderDao.load(oid);
	}

	/**
	 * 确认收货
	 * 
	 * @param oid
	 * @throws OrderException
	 */
	public void confirm(String oid) throws OrderException {
		// 获取订单状态，判断订单状态
		int state = orderDao.getStateByOid(oid);
		if (state != 2)
			throw new OrderException("确认收货失败！");
		// 修改订单状态为3，确认收货

		orderDao.updateState(oid, 3);
	}

	/*
	 * 支付方法
	 */
	public void zhiFu(String r6_Order) {
		/*
		 * 1. 获取订单的状态 * 如果状态为0，那么执行下面代码 * 如果状态不为0，那么本方法什么都不做
		 */
		int state = orderDao.getStateByOid(r6_Order);
		if (state == 0) {
			// 将状态修改为1
			orderDao.updateState(r6_Order, 1);
		}
	}
}
