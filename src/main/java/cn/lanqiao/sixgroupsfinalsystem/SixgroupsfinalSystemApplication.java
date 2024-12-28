package cn.lanqiao.sixgroupsfinalsystem;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.lanqiao.sixgroupsfinalsystem.mapper")
public class SixgroupsfinalSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SixgroupsfinalSystemApplication.class, args);
    }

}
