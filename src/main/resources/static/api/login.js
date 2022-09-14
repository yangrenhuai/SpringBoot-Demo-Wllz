function login(data) {
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
    'url': '/captchaImage',
    'method': 'get',
  })
}

function logout(){
  return $axios({
    'url': '/user/logout',
    'method': 'post',
  })
}
