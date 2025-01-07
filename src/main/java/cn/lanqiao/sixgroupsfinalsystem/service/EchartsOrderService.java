package cn.lanqiao.sixgroupsfinalsystem.service;

import java.util.List;
import java.util.Map;

public interface EchartsOrderService {
    List<Map<String, Object>> getOrderStatusStats();
}