package cn.edu.svtcc.entity.vo;

import java.util.List;

public class DeptTreeVO {
    private Long id;
    private String label;
    private List<DeptTreeVO> children;

    public DeptTreeVO() {
    }

    public DeptTreeVO(Long id, String label, List<DeptTreeVO> children) {
        this.id = id;
        this.label = label;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<DeptTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<DeptTreeVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DeptVO{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", children=" + children +
                '}';
    }
}
