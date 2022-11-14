package cn.edu.svtcc.service;

import cn.edu.svtcc.entity.Complaint;
import cn.edu.svtcc.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ComplaintService extends IService<Complaint> {
    public List<Complaint> selectComplaintList(Map<String,Object> queryParams) ;
}
