function loginApi(data) {
  return $axios({
    'url': '/user/login',
    'method': 'post',
    data
  })
}

/**
 * 获取验证码
 */
function getCodeImg(){
  return $axios({
    'url': '/wllz/captchaImage',
    'method': 'get',
  })
}

function logoutApi(){
  return $axios({
    'url': '/user/logout',
    'method': 'post',
  })
}
