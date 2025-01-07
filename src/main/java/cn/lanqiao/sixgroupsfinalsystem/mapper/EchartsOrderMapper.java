package cn.lanqiao.sixgroupsfinalsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface EchartsOrderMapper {
    @Select("SELECT order_status, COUNT(*) as count FROM orders GROUP BY order_status")
    List<Map<String, Object>> getOrderStatusStats();
}