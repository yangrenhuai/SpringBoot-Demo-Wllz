package cn.edu.svtcc.entity.vo;

import java.util.List;

public class DeptTableVO {
    private Long deptId;
    /**
     * 上级部门Id
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 排序字段
     */
    private Long orderNum;
    /**
     * 部门领导
     */
    private String leader;
    /**
     * 部门领导电话
     */
    private String phone;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 状态，默认值为0，0-正常，1-停用
     */
    private String status;
    private List<DeptTableVO> children;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DeptTableVO> getChildren() {
        return children;
    }

    public void setChildren(List<DeptTableVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DeptTableVO{" +
                "deptId=" + deptId +
                ", parentId=" + parentId +
                ", deptName='" + deptName + '\'' +
                ", orderNum=" + orderNum +
                ", leader='" + leader + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", children=" + children +
                '}';
    }
}
