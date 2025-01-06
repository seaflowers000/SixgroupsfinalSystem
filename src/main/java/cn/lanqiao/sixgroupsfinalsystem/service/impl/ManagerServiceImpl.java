package cn.lanqiao.sixgroupsfinalsystem.service.impl;

import cn.lanqiao.sixgroupsfinalsystem.mapper.ManagerMapper;
import cn.lanqiao.sixgroupsfinalsystem.mapper.VipNameMapper;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;
import cn.lanqiao.sixgroupsfinalsystem.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
