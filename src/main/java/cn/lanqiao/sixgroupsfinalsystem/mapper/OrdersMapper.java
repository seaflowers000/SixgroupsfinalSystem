package cn.lanqiao.sixgroupsfinalsystem.mapper;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Orders;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper

public interface OrdersMapper {
    @Select("select * from orders")
    List<Orders> selectAll();
    @Delete("DELETE FROM orders WHERE order_id = #{orderId}")  // 修改为 order_id
    int deleteById(Integer orderId);
    //批量删除
    @Delete("<script>" +
            "DELETE FROM orders WHERE order_id IN " +
            "<foreach collection='list' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    int batchDelete(List<Integer> ids);
//新增
    @Insert("INSERT INTO orders (recipient_name, total_amount, payable_amount, " +
            "order_status, payment_status, shipping_status, payment_method, " +
            "delivery_method, order_time) " +
            "VALUES (#{recipientName}, #{totalAmount}, #{payableAmount}, " +
            "#{orderStatus}, #{paymentStatus}, #{shippingStatus}, #{paymentMethod}, " +
            "#{deliveryMethod}, #{orderTime})")
    int insert(Orders orders);
    @Update("UPDATE orders SET recipient_name=#{recipientName}, total_amount=#{totalAmount}, " +
        "payable_amount=#{payableAmount}, order_status=#{orderStatus}, " +
        "payment_status=#{paymentStatus}, shipping_status=#{shippingStatus}, " +
        "payment_method=#{paymentMethod}, delivery_method=#{deliveryMethod} " +
        "WHERE order_id=#{orderId}")
int update(Orders orders);
@Select("SELECT * FROM orders WHERE order_id = #{orderId}")
Orders getById(Integer orderId);

}
