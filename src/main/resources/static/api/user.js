function getUserList (params) {
  return $axios({
    url: '/user/page',
    method: 'get',
    params
  })
}

// 修改---启用禁用接口
function enableOrDisableUser (params) {
  return $axios({
    url: '/user',
    method: 'put',
    data: { ...params }
  })
}

// 新增---添加用户
function addUser (params) {
  return $axios({
    url: '/user',
    method: 'post',
    data: { ...params }
  })
}

// 修改---修改用户
function editUser (params) {
  return $axios({
    url: '/user',
    method: 'put',
    data: { ...params }
  })
}

// 修改页面反查详情接口
function queryEmployeeById (id) {
  return $axios({
    url: `/employee/${id}`,
    method: 'get'
  })
}