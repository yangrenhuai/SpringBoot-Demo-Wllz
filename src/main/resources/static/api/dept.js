
/**
 * 给树形选择框获取数据
 * @param params
 * @returns {*}
 */
function getDeptTree(params){
    return $axios({
        url:'/dept/tree',
        method: 'get',
        params,
    })
}

/**
 * 给树形表格获取数据
 * @param params
 * @returns {*}
 */
function getDeptList(params){
    return $axios({
        url:'/dept/list',
        method: 'get',
        params,
    })
}

function getDeptById(deptId){
    return $axios({
        url:'/dept/'+deptId,
        method:'get'
    });
}

/**
 * 添加部门
 * @param params
 * @returns {*}
 */
function addDept(params){
    return $axios({
        url:'/dept',
        method:'post',
        data:{...params}
    });
}

/**
 * 修改部门数据
 * @param params
 * @returns {*}
 */
function updateDept(params){
    return $axios({
        url:'/dept',
        method:'put',
        data:{...params}
    });
}

/**
 * 删除部门数据
 * @param params
 * @returns {*}
 */
function deleteDept(deptId){
    return $axios({
        url:'/dept/'+deptId,
        method:'delete'
    });
}

