package cn.lanqiao.sixgroupsfinalsystem.service;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Orders;

import java.util.List;

public interface OrdersService  {
    List<Orders> selectAll();
    int deleteById(Integer orderId);
    int batchDelete(List<Integer> ids);
    int insert(Orders orders);
    int update(Orders orders);
    Orders selectById(Integer orderId);
}
