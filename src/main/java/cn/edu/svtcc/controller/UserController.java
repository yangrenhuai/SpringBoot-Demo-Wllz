package cn.edu.svtcc.controller;

import cn.edu.svtcc.common.constant.Constants;
import cn.edu.svtcc.common.domain.AjaxResult;
import cn.edu.svtcc.common.utils.Md5Utils;
import cn.edu.svtcc.common.utils.StringUtils;
import cn.edu.svtcc.entity.User;
import cn.edu.svtcc.entity.vo.UserVO;
import cn.edu.svtcc.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
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
        request.getSession().setAttribute("User",user);
        return ajax;
    }

    /**
     * 退出系统
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public AjaxResult logout(HttpServletRequest request){
        AjaxResult ajax=AjaxResult.success();
        request.getSession().removeAttribute(Constants.CAPTCHA_CODE_SESSION_KEY);
        request.getSession().removeAttribute("User");
        return ajax;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping
    public AjaxResult add(@RequestBody User user, HttpServletRequest request){
        AjaxResult ajax=AjaxResult.success();

        User loginUser=(User)(request.getAttribute("User"));
        user.setPassword(Md5Utils.hash(user.getPassword()));
        user.setCreateBy(loginUser.getUserName());
        user.setCreateTime(new Date());
        user.setUpdateBy(loginUser.getUserName());
        user.setUpdateTime(new Date());
        userService.save(user);

        return ajax;
    }

    /**
     * 修改用户信息
     * @param user
     * @param request
     * @return
     */
    @PutMapping
    public AjaxResult edit(@RequestBody User user,HttpServletRequest request){
        AjaxResult ajax=AjaxResult.success();
        User loginUser=(User)(request.getAttribute("User"));
        user.setPassword(Md5Utils.hash(user.getPassword()));
        user.setUpdateBy(loginUser.getUserName());
        user.setUpdateTime(new Date());

        return ajax;
    }

    @PutMapping("/status")
    public AjaxResult enableOrDisable(@RequestBody User user,HttpServletRequest request){
        AjaxResult ajax=AjaxResult.success();


        return ajax;
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @GetMapping("/page")
    public AjaxResult page(int page,int pageSize,String userName,String status){
        AjaxResult ajax=AjaxResult.success();
        log.info("page={},pageSize={},name={},status={}",page,pageSize,userName,status);

        //1.构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //2.构造条件
        LambdaQueryWrapper<User> wapper=new LambdaQueryWrapper();
        wapper.like(StringUtils.isNotEmpty(userName),User::getUserName, userName);
        wapper.eq(StringUtils.isNotEmpty(status),User::getStatus,status);
        //添加排序条件
        wapper.orderByDesc(User::getUpdateTime);

        //3.执行查询
        userService.page(pageInfo,wapper);

        ajax.put("data",pageInfo);

        return ajax;
    }
}
