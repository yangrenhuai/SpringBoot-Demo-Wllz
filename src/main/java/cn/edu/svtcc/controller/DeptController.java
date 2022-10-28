package cn.edu.svtcc.controller;

import cn.edu.svtcc.common.domain.AjaxResult;
import cn.edu.svtcc.entity.Dept;
import cn.edu.svtcc.entity.User;
import cn.edu.svtcc.entity.vo.DeptTableVO;
import cn.edu.svtcc.entity.vo.DeptTreeVO;
import cn.edu.svtcc.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    DeptService deptService;

    @PostMapping
    public AjaxResult add(@RequestBody Dept dept, HttpServletRequest request){
        AjaxResult ajax=AjaxResult.success();

        dept.setStatus("0");
        dept.setCreateBy(((User)request.getSession()).getUserName());
        dept.setCreateTime(new Date());
        dept.setUpdateBy(((User)request.getSession()).getUserName());
        dept.setUpdateTime(new Date());

        deptService.save(dept);

        return ajax;
    }

    @GetMapping("/list")
    public AjaxResult list(String deptName,String status){
        AjaxResult ajax=AjaxResult.success();

        Dept dept=new Dept();
        dept.setDeptName(deptName);
        dept.setStatus(status);

        List<Dept> deptList=deptService.selectDeptList(dept);
        List<DeptTableVO> treeList=toTableTree(deptList,0L);

        log.info(treeList.toString());

        ajax.put("data",treeList);
        return ajax;
    }

    @RequestMapping("/tree")
    public AjaxResult tree(Long deptId){
        Dept dept=new Dept();
        dept.setDeptId(deptId);
        List<Dept> deptList= deptService.selectDeptList(dept);

        List<DeptTreeVO> deptVOList=new ArrayList<>();
        deptVOList=toSelectTree(deptList,0L);
        deptVOList.add(0,new DeptTreeVO(0L,"无",null));

        AjaxResult ajax=AjaxResult.success();
        ajax.put("data",deptVOList);
        return ajax;
    }

    public List<DeptTreeVO> toSelectTree(List<Dept> deptList, Long parentId){
        List<Dept> list1=new ArrayList<>();

        for(Dept dept:deptList){
            if(dept.getParentId()==parentId) list1.add(dept);
        }

        if(list1.size()<0) return null;

        List<DeptTreeVO> treeList =new ArrayList<>();
        for(Dept dept:list1){
            DeptTreeVO deptTreeVO =new DeptTreeVO();
            deptTreeVO.setId(dept.getDeptId());
            deptTreeVO.setLabel(dept.getDeptName());
            List<DeptTreeVO> children=toSelectTree(deptList,dept.getDeptId());
            if(children==null || children.size()==0)
                deptTreeVO.setChildren(null);
            else
                deptTreeVO.setChildren(children);
            treeList.add(deptTreeVO);
        }
        return treeList;
    }

    public List<DeptTableVO> toTableTree(List<Dept> deptList, Long parentId){
        List<Dept> list1=new ArrayList<>();

        for(Dept dept:deptList){
            if(dept.getParentId()==parentId) list1.add(dept);
        }

        if(list1.size()<0) return null;

        List<DeptTableVO> treeList =new ArrayList<>();
        for(Dept dept:list1){
            DeptTableVO vo =new DeptTableVO();
            vo.setDeptId(dept.getDeptId());
            vo.setDeptName(dept.getDeptName());
            vo.setEmail(dept.getEmail());
            vo.setLeader(dept.getLeader());
            vo.setOrderNum(dept.getOrderNum());
            vo.setPhone(dept.getPhone());
            vo.setParentId(dept.getParentId());

            List<DeptTableVO> children=toTableTree(deptList,dept.getDeptId());
            if(children==null || children.size()==0)
                vo.setChildren(null);
            else
                vo.setChildren(children);
            treeList.add(vo);
        }
        return treeList;
    }
}
