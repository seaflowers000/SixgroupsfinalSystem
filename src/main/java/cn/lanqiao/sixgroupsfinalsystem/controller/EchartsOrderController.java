package cn.lanqiao.sixgroupsfinalsystem.controller;

import cn.lanqiao.sixgroupsfinalsystem.service.impl.EchartsOrderServiceImpl;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class EchartsOrderController {

    @Autowired
    private EchartsOrderServiceImpl echartsOrderService;

    @GetMapping("/stats")
    public ResponseUtils<List<Map<String, Object>>> getOrderStats() {
        List<Map<String, Object>> stats = echartsOrderService.getOrderStatusStats();
        if (stats != null && !stats.isEmpty()) {
            return new ResponseUtils<>(200, "获取成功", stats);
        } else {
            return new ResponseUtils<>(0, "暂无数据", new ArrayList<>());
        }
    }
}