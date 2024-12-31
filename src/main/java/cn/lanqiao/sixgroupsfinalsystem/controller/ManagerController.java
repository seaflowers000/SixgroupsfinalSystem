package cn.lanqiao.sixgroupsfinalsystem.controller;

import ch.qos.logback.core.model.Model;
import cn.lanqiao.sixgroupsfinalsystem.mapper.ManagerMapper;
import cn.lanqiao.sixgroupsfinalsystem.model.entity.ManagerSearch;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;
import cn.lanqiao.sixgroupsfinalsystem.service.ManagerService;
import cn.lanqiao.sixgroupsfinalsystem.service.VipNameService;
import cn.lanqiao.sixgroupsfinalsystem.service.impl.ManagerServiceImpl;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/mang")
public class ManagerController {
    @Autowired
    private ManagerServiceImpl managerService;
    @Resource
    private ManagerMapper managerMapper;
//    @GetMapping("/select")
//    public ResponseUtils select(){
//        try {
//            List<MagVO> magVOS = managerService.selectAll();
//            System.out.println("查询结果：" + magVOS);
//
//            if(magVOS != null && !magVOS.isEmpty()){
//                return new ResponseUtils(200, "查询成功", magVOS);
//            } else {
//                return new ResponseUtils(0, "暂无数据", new ArrayList<>());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseUtils(500, "查询失败: " + e.getMessage());
//        }
//    }
//    Model model
    @RequestMapping("/getAllUserByCon")
    public ResponseUtils getAllUserByCon(@RequestBody ManagerSearch managerSearch) {
        try {
            // 设置默认值
            if (managerSearch.getPageNo() == null) {
                managerSearch.setPageNo(1);
            }
            if (managerSearch.getPageSize() == null) {
                managerSearch.setPageSize(7);
            }
            
            // 分页对象
            Page<Manager> page = new Page<>(managerSearch.getPageNo(), managerSearch.getPageSize());
            
            // 条件构造器
            QueryWrapper<Manager> wrapper = new QueryWrapper<>();
            
            // 添加查询条件
            if(managerSearch.getLoginName() != null && !managerSearch.getLoginName().equals("")) {
                wrapper.like("username", managerSearch.getLoginName());
            }
            if(managerSearch.getRole() != null) {
                wrapper.eq("role_id", managerSearch.getRole());
            }

            // 执行分页查询
            Page<Manager> result = managerMapper.selectPage(page, wrapper);
            
            // 返回分页数据
            Map<String, Object> data = new HashMap<>();
            data.put("records", result.getRecords());
            data.put("total", result.getTotal());
            data.put("current", result.getCurrent());
            data.put("pages", result.getPages());
            data.put("size", result.getSize());
            
            return new ResponseUtils(200, "查询成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "查询失败: " + e.getMessage());
        }
    }
}
