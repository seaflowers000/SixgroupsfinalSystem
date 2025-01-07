package cn.lanqiao.sixgroupsfinalsystem.controller;

import cn.lanqiao.sixgroupsfinalsystem.service.OrdersService;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Orders;
import cn.lanqiao.sixgroupsfinalsystem.service.impl.OrdersServiceImpl;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin
@RequestMapping("/orders") // 使用斜杠开头表示根路径
public class OrdersController {
    //查询订单列表

    @Autowired
    private OrdersServiceImpl ordersService;

    @GetMapping ("/se")// 默认为 GET 请求
    public ResponseUtils se() {
        List<Orders> orders = ordersService.selectAll();
        System.out.println("查询结果"+orders);
        if (orders != null && !orders.isEmpty()) {
            return new ResponseUtils(200, "success", orders);
        } else {
            return new ResponseUtils(0, "暂无数据", new ArrayList<>());
        }



    }
    //删除订单列表
    @PostMapping("/delete")
    public ResponseUtils<String> delete(@RequestBody Map<String, Integer> params) {
        System.out.println("接收到的请求参数: " + params);  // 添加日志
        
        if (params == null || params.get("id") == null){
            return new ResponseUtils<>(400, "参数错误", null);
        }
        
        Integer id = params.get("id");
        System.out.println("准备删除的订单ID: " + id);  // 添加日志
        
        int result = ordersService.deleteById(id);
        System.out.println("删除操作结果: " + result);  // 添加日志
        
        if (result > 0) {
            return new ResponseUtils<>(200, "删除成功", null);
        } else {
            return new ResponseUtils<>(500, "删除失败", null);
        }
    }
// 批量删除订单列表
@PostMapping("/batchDelete")
public ResponseUtils<String> batchDelete(@RequestBody Map<String, List<Integer>> params) {
    System.out.println("接收到的批量删除请求参数: " + params);  // 添加日志
    
    List<Integer> ids = params.get("ids");
    if (ids == null || ids.isEmpty()) {
        return new ResponseUtils<>(400, "参数错误", null);
    }
    
    System.out.println("准备批量删除的订单IDs: " + ids);  // 添加日志
    
    int result = ordersService.batchDelete(ids);
    System.out.println("批量删除操作结果: " + result);  // 添加日志
    
    if (result > 0) {
        return new ResponseUtils<>(200, "批量删除成功", null);
    } else {
        return new ResponseUtils<>(500, "批量删除失败", null);
    }
}
// 新增订单列表

@PostMapping("/add")
public ResponseUtils<String> add(@RequestBody Orders order) {
   System.out.println("接收到的新增订单请求参数: " + order);  // 添加日志

   if (order == null) {
       return new ResponseUtils<>(400, "参数错误", null);
   }

   // 设置默认值
   if (order.getOrderTime() == null) {
       order.setOrderTime(new java.sql.Timestamp(System.currentTimeMillis()));

   }
   if (order.getOrderStatus() == null) {
       order.setOrderStatus("待支付");
   }

   int result = ordersService.insert(order);
   System.out.println("新增操作结果: " + result);  // 添加日志

   if (result > 0) {
       return new ResponseUtils<>(200, "新增成功", null);
   } else {
       return new ResponseUtils<>(500, "新增失败", null);
   }
}
//修改订单列表
    @GetMapping("/getById/{id}")
    public ResponseUtils<Orders> getById(@PathVariable Integer id) {
        Orders order = ordersService.selectById(id);
        if (order != null) {
            return new ResponseUtils<>(200, "success", order);
        } else {
            return new ResponseUtils<>(0, "未找到数据", null);
        }
    }
@PostMapping("/update")
public ResponseUtils<String> update(@RequestBody Orders order) {
    System.out.println("接收到的更新订单请求参数: " + order);
    
    if (order == null || order.getOrderId() == null) {
        return new ResponseUtils<>(400, "参数错误", null);
    }
    
    int result = ordersService.update(order);
    System.out.println("更新操作结果: " + result);
    
    if (result > 0) {
        return new ResponseUtils<>(200, "更新成功", null);
    } else {
        return new ResponseUtils<>(500, "更新失败", null);
    }}
}