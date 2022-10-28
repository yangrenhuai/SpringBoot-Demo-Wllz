package cn.edu.svtcc.service.impl;

import cn.edu.svtcc.entity.Dept;
import cn.edu.svtcc.mapper.DeptMapper;
import cn.edu.svtcc.service.DeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Override
    public List<Dept> selectDeptList(Dept dept) {
        QueryWrapper<Dept> wrapper=new QueryWrapper<>();
        if(dept!=null){
            if(dept.getDeptName()!=null){
                wrapper.like("dept_name",dept.getDeptName());
            }
            if(dept.getDeptId()!=null && dept.getDeptId()!=0){
                wrapper.eq("dept_id",dept.getDeptId());
            }
            if(dept.getStatus()!=null && dept.getStatus()!=""){
                wrapper.eq("status",dept.getStatus());
            }
        }
        List<Dept> deptList=this.baseMapper.selectList(wrapper);

        return deptList;
    }

    public List<Dept> selectAllDeptList(){
        return this.baseMapper.selectList(new QueryWrapper<>());
    }
}
