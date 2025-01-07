package cn.lanqiao.sixgroupsfinalsystem.service.impl;

import cn.lanqiao.sixgroupsfinalsystem.mapper.EchartsOrderMapper;
import cn.lanqiao.sixgroupsfinalsystem.service.EchartsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class EchartsOrderServiceImpl implements EchartsOrderService {

    @Autowired
    private EchartsOrderMapper echartsOrderMapper;

    @Override
    public List<Map<String, Object>> getOrderStatusStats() {
        return echartsOrderMapper.getOrderStatusStats();
    }
}