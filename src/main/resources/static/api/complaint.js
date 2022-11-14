/**
 * 查找投诉列表
 * @param params
 * @returns {*}
 */
function getComplaintList(params){
    return $axios({
        url:'/complaint/list',
        method: 'get',
        params,
    })
}

function getComplaintById(complaintId){
    return $axios({
        url:'/complaint/'+complaintId,
        method:'get'
    });
}

/**
 * 添加投诉
 * @param params
 * @returns {*}
 */
function addComplaint(params){
    return $axios({
        url:'/complaint',
        method:'post',
        data:{...params}
    });
}

/**
 * 修改投诉数据
 * @param params
 * @returns {*}
 */
function updateComplaint(params){
    return $axios({
        url:'/complaint',
        method:'put',
        data:{...params}
    });
}

/**
 * 删除投诉数据
 * @param params
 * @returns {*}
 */
function deleteComplaint(complaintId){
    return $axios({
        url:'/complaint/'+complaintId,
        method:'delete'
    });
}

