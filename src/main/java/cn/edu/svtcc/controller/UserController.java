package cn.edu.svtcc.controller;

import cn.edu.svtcc.common.constant.Constants;
import cn.edu.svtcc.common.domain.AjaxResult;
import cn.edu.svtcc.common.utils.Md5Utils;
import cn.edu.svtcc.entity.User;
import cn.edu.svtcc.entity.vo.UserVO;
import cn.edu.svtcc.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AjaxResult login(HttpServletRequest request, @RequestBody UserVO userVO) {
        /*
         * 1.根据userName查询用户信息
         * 2.将密码用MD5加密后比对
         * 3.判断用户状态
         * 4.判断验证码
         * */
        //1.根据用户名查询用户信息
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name", userVO.getUserName());
        User user = userService.getOne(queryWrapper);

        if(user==null){
            return AjaxResult.error("用户名不存在");
        }
        AjaxResult ajax = AjaxResult.success();

        //2.将客户端发送的密码加密后比对
        String encryptPassword=Md5Utils.hash(userVO.getPassword());
        if(!encryptPassword.equals(user.getPassword())){
            return AjaxResult.error("密码错误");
        }

        //3.判断用户状态

        //4.判断验证码
        if(request.getSession().getAttribute(Constants.CAPTCHA_CODE_SESSION_KEY)==null){
            return AjaxResult.error("验证码超时，请重新获取验证码");
        }
        String captchaCode=(String)request.getSession().getAttribute(Constants.CAPTCHA_CODE_SESSION_KEY);
        if(!captchaCode.equals(userVO.getCode())){
            return AjaxResult.error("验证码错误");
        }
        ajax.put("data",user);
        return ajax;
    }

    @PostMapping("/logout")
    public AjaxResult logout(HttpServletRequest request){
        AjaxResult ajax=AjaxResult.success();
        request.getSession().removeAttribute(Constants.CAPTCHA_CODE_SESSION_KEY);
        return ajax;
    }
}
