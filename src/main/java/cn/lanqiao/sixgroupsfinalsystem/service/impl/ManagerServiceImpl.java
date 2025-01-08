package cn.lanqiao.sixgroupsfinalsystem.service.impl;

import cn.lanqiao.sixgroupsfinalsystem.mapper.ManagerMapper;
import cn.lanqiao.sixgroupsfinalsystem.mapper.VipNameMapper;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;
import cn.lanqiao.sixgroupsfinalsystem.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public List<MagVO> selectAll() {
        final List<MagVO> magMapper = managerMapper.selectAll();
        if(magMapper != null){
            // 有参数返回值
            return magMapper;
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
            int result = managerMapper.deleteById(id);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean batchDelete(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        for (Integer id : ids) {
            managerMapper.deleteById(id);
        }
        return true;
    }

    /*** 模糊查询*/
    @Override
    public List<Manager> likeselect(String loginname) {
        try {
            return managerMapper.likeselect(loginname);
        } catch (Exception e) {e.printStackTrace();return new ArrayList<>();}
    }

    @Override
    public int insertSelective(Manager record) {
        int i = managerMapper.insertSelective(record);
        return i;
    }
}
