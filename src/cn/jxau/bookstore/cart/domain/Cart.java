package cn.jxau.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车类
 * 
 * @author ZQ
 *
 */
public class Cart {
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();

	/*
	 * 添加购物车条目,按物品id查询
	 */
	public void add(CartItem cartitem) {
		if(map.containsKey(cartitem.getBook().getBid())){//判断该购物车是否存在该条目
			CartItem _cartitem = map.get(cartitem.getBook().getBid());//获取原条目
			_cartitem.setCount(_cartitem.getCount() + cartitem.getCount());//设置原来条目的数量
			map.put(cartitem.getBook().getBid(), _cartitem);
		}else{
			map.put(cartitem.getBook().getBid(), cartitem);
		}
	}

	/*
	 * 计算合计
	 */
	public double getTotal() {
		// 合计=所有条目的小计总和
		BigDecimal total = new BigDecimal("0");// 使用bigDecimal来防止二进制运算产生的误差
		for (CartItem cartitem : map.values()) {
			BigDecimal subtotal = new BigDecimal("" + cartitem.getSubtotal());
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}

	/*
	 * 删除指定条目物品
	 */
	public void delect(String bid) {
		map.remove(bid);
	}

	/**
	 * 清空购物车条目
	 */
	public void clear() {
		map.clear();
	}

	/*
	 * 获取所有条目
	 */
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
}
