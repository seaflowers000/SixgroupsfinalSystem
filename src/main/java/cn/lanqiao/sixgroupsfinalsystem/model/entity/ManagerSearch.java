package cn.lanqiao.sixgroupsfinalsystem.model.entity;

import lombok.Data;

/**
 * ClassName: UserSearch
 * Package: com.example.usermanager.entity
 *
 * @Author: QINKUI
 * @createTime: 2024年03月11日 12:12:26
 */
@Data
public class ManagerSearch {
    private String LoginName;//用户名关键字

    private Integer role;//角色

    private Integer pageNo;//当前页

    private Integer pageSize;//每页显示的条数

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
