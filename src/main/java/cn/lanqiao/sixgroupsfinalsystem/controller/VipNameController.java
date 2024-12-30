package cn.lanqiao.sixgroupsfinalsystem.controller;


import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.service.VipNameService;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vipName")

public class VipNameController {
    @Autowired
    private VipNameService vipNameService;

    /**
     * 会员列表查询所有
     * @return
     */
    @GetMapping("/select")
    public ResponseUtils select(){
        try {
            List<VipName> vipNameMappers = vipNameService.selectAll();
            System.out.println("查询结果：" + vipNameMappers);
            
            if(vipNameMappers != null && !vipNameMappers.isEmpty()){
                return new ResponseUtils(200, "查询成功", vipNameMappers);
            } else {
                return new ResponseUtils(0, "暂无数据", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "查询失败: " + e.getMessage());
        }
    }
}

