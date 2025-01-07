package cn.lanqiao.sixgroupsfinalsystem.controller;

//import ch.qos.logback.core.model.Model;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
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
    @PostMapping("/addMag")
    public ResponseUtils addMag(@RequestBody Manager manager) {
        try {
            // 添加日志
            System.out.println("接收到的数据：" + manager);
            
            // 验证必填字段
            if (manager.getLoginName() == null || manager.getLoginName().trim().isEmpty()) {
                return new ResponseUtils(500, "登录名不能为空", null);
            }
            if (manager.getPassword() == null || manager.getPassword().trim().isEmpty()) {
                return new ResponseUtils(500, "密码不能为空", null);
            }
            
            // 设置默认值
            manager.setJoinTime(new Timestamp(System.currentTimeMillis()));
            manager.setStatus(1);
            
            // 执行插入操作
            int result = managerMapper.insert(manager);
            System.out.println("插入结果：" + result); // 添加日志
            
            if (result > 0) {
                return new ResponseUtils(200, "添加成功", null);
            } else {
                return new ResponseUtils(500, "添加失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "添加失败: " + e.getMessage());
        }
    }
    @PostMapping("/updateUser")
    public ResponseUtils updateUser(@RequestBody Manager manager){
        try {
            int i = managerMapper.updateById(manager);
            if(i == 1){
                return new ResponseUtils(200, "更新成功", null);
            } else {
                return new ResponseUtils(500, "更新失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "更新失败: " + e.getMessage(), null);
        }
    }
}
