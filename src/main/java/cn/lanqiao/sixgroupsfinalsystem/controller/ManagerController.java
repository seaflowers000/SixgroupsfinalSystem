package cn.lanqiao.sixgroupsfinalsystem.controller;

import cn.lanqiao.sixgroupsfinalsystem.mapper.ManagerMapper;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;
import cn.lanqiao.sixgroupsfinalsystem.service.ManagerService;
import cn.lanqiao.sixgroupsfinalsystem.service.VipNameService;
import cn.lanqiao.sixgroupsfinalsystem.service.impl.ManagerServiceImpl;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/mang")
public class ManagerController {
    @Autowired
    private ManagerServiceImpl managerService;
    @GetMapping("/select")
    public ResponseUtils select(){
        try {
            List<MagVO> magVOS = managerService.selectAll();
            System.out.println("查询结果：" + magVOS);

            if(magVOS != null && !magVOS.isEmpty()){
                return new ResponseUtils(200, "查询成功", magVOS);
            } else {
                return new ResponseUtils(0, "暂无数据", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "查询失败: " + e.getMessage());
        }
    }
}
