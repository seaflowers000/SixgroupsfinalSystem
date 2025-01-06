package cn.lanqiao.sixgroupsfinalsystem.service.impl;


import cn.lanqiao.sixgroupsfinalsystem.mapper.SixmagMapper;
import cn.lanqiao.sixgroupsfinalsystem.model.dto.SixmagLogin;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;
import cn.lanqiao.sixgroupsfinalsystem.service.SixmagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SixmagServiceimpl implements SixmagService {
    @Autowired
    private SixmagMapper sixmagMapper;
    @Override
    public  Manager login(SixmagLogin manager){
        Manager result = sixmagMapper.login(manager);
        if (result!=null){
            return result;
        }else   {
            return null;
        }
    }


}
