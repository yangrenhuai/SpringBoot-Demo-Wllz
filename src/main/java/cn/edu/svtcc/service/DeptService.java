package cn.edu.svtcc.service;

import cn.edu.svtcc.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DeptService extends IService<Dept> {
    public List<Dept> selectDeptList(Dept dept);

    public List<Dept> selectAllDeptList();
}
