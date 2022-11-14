package cn.edu.svtcc.service.impl;

import cn.edu.svtcc.entity.Complaint;
import cn.edu.svtcc.mapper.ComplaintMapper;
import cn.edu.svtcc.service.ComplaintService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements ComplaintService {
    @Override
    public List<Complaint> selectComplaintList(Map<String,Object> queryParams) {
        QueryWrapper<Complaint> wrapper=new QueryWrapper<>();
        // 按投诉时间范围查询
        if(queryParams!=null && queryParams.size()>0){
            if(queryParams.get("complaintTime1")!=null && queryParams.get("complaintTime2")!=null){
                wrapper.between("complaint_time",queryParams.get("complaintTime1"),
                        queryParams.get("complaintTime2"));
            }
        }
        List<Complaint> complaintList=this.baseMapper.selectList(wrapper);

        return complaintList;
    }
}
