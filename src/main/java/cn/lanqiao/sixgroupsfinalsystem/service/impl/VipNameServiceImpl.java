package cn.lanqiao.sixgroupsfinalsystem.service.impl;


import cn.lanqiao.sixgroupsfinalsystem.mapper.VipNameMapper;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.service.VipNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class VipNameServiceImpl implements VipNameService {
    // 依赖注入
    @Autowired
    private VipNameMapper vipNameMapper;

    /**
     * 查询所有会员
     * @return
     */
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

    /**
     * 删除单个会员
     * @param id
     * @return
     */
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

    /**
     * 批量逻辑删除会员
     */
    @Override
    public boolean batchDelete(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        for (Integer id : ids) {
            vipNameMapper.deleteById(id);
        }
        return true;
    }

    /**
     * 模糊查询
     * @param username
     * @return
     */
    @Override
    public List<VipName> search(String username) {
        try {
            return vipNameMapper.search(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}