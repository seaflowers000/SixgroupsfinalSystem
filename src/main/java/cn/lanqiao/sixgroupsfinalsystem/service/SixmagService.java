package cn.lanqiao.sixgroupsfinalsystem.service;


import cn.lanqiao.sixgroupsfinalsystem.model.dto.SixmagLogin;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;

public interface SixmagService {
    Manager login(SixmagLogin manager);
    int register(Manager manager);

}
