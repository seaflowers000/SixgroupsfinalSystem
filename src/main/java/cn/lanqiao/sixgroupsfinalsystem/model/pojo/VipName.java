package cn.lanqiao.sixgroupsfinalsystem.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VipName {

  private long id;
  private String username;
  private String gender;
  private String email;
  private String address;
  private long status;



}
