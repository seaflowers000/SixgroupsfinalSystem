package cn.lanqiao.sixgroupsfinalsystem.service.impl;

import cn.lanqiao.sixgroupsfinalsystem.mapper.OrdersMapper;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Orders;
import cn.lanqiao.sixgroupsfinalsystem.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> selectAll() {
        List<Orders> orders = ordersMapper.selectAll();

        if(orders != null){
            return orders;
        }else {
            return null;
        }
//        return orders;
//        System.out.println("OrdersServiceImpl.selectAll()");

    }

    @Override
public int deleteById(Integer id) {
    System.out.println("Service层接收到的ID: " + id);  // 添加日志
    
    if (id != null){
        int result = ordersMapper.deleteById(id);
        System.out.println("数据库删除操作结果: " + result);  // 添加日志
        return result;
    } else {
        return 0;
    }
}

    @Override
    public int batchDelete(List<Integer> ids) {
        if(ids == null || ids.isEmpty()){
            return 0;
        }else{
            int result = ordersMapper.batchDelete(ids);
            return result;
        }

    }

    @Override
    public int insert(Orders orders) {
        return  ordersMapper.insert(orders);
    }

    @Override
    public int update(Orders orders) {
        return ordersMapper.update(orders);
    }

    @Override
    public Orders selectById(Integer orderId) {
        return ordersMapper.getById(orderId);
    }
}