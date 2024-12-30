package cn.lanqiao.sixgroupsfinalsystem.service.impl;


import cn.lanqiao.sixgroupsfinalsystem.mapper.VipNameMapper;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.service.VipNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VipNameServiceImpl implements VipNameService {
    // 依赖注入
    @Autowired
    private VipNameMapper vipNameMapper;
    // 查询所有会员
    @Override
    public List<VipName> selectAll() {
        final List<VipName> vipNameMappers = vipNameMapper.selectAll();
        if(vipNameMappers != null){
            // 有参数返回值
            return vipNameMappers;
        }else{
            return null;
        }
    }
    @Override
    public boolean deleteById(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            int result = vipNameMapper.deleteById(id);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    @Transactional  // 添加事务管理
    public boolean batchDelete(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        try {
            int result = vipNameMapper.batchDelete(ids);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}