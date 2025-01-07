package cn.lanqiao.sixgroupsfinalsystem.service.impl;


import cn.lanqiao.sixgroupsfinalsystem.mapper.VipNameMapper;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.service.VipNameService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
//import org.springframework.util.StringUtils;

@Service
public class
VipNameServiceImpl implements VipNameService {
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
     * 添加会员
     * @param vipName
     * @return
     */

    @Override
    @Transactional
    public boolean add(VipName vipName) {
        if (vipName == null || StringUtils.isEmpty(vipName.getUsername())) {
            return false;
        }

        try {
            // 设置默认值
            if (vipName.getStatus() == null) {
                vipName.setStatus(0);
            }

            return vipNameMapper.insert(vipName) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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