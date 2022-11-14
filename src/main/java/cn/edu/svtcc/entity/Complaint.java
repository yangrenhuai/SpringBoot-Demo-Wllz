package cn.edu.svtcc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class Complaint extends BaseEntity{
    @TableId(type= IdType.AUTO)
    private Long complaintId;

    private String workOrder;
    private String sourceType;
    private String contentType;
    private String complainant;
    private String phonenumber;
    private String details;
    private String remark;
    private String status;
    /**
     * 办理民警编号
     */
    private Long userId;

    private Date complaintTime;

    public Date getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    /**
     * 最迟办结期限
     */
    private Date limitTime;
    /**
     * 转交给民警的办理时间
     */
    private Date forwardTime;

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    private Date processTime;

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getComplainant() {
        return complainant;
    }

    public void setComplainant(String complainant) {
        this.complainant = complainant;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
    }

    public Date getForwardTime() {
        return forwardTime;
    }

    public void setForwardTime(Date forwardTime) {
        this.forwardTime = forwardTime;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId=" + complaintId +
                ", workOrder='" + workOrder + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", contentType='" + contentType + '\'' +
                ", complainant='" + complainant + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", details='" + details + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", complaintTime=" + complaintTime +
                ", limitTime=" + limitTime +
                ", forwardTime=" + forwardTime +
                ", processTime=" + processTime +
                '}';
    }
}
