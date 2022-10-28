function getDeptList (params) {
    return $axios({
        url: '/dept/list',
        method: 'post',
        ...params
    })
}

function getDeptTree(params){
    return $axios({
        url:'/dept/tree',
        method: 'get',
        params,
    })
}

function getDeptList(params){
    return $axios({
        url:'/dept/list',
        method: 'get',
        params,
    })
}

function addDept(params){
    return $axios({
        url:'/dept',
        method:'post',
        data:{...params}
    });
}
