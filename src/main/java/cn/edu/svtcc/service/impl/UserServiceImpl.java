package cn.edu.svtcc.service.impl;

import cn.edu.svtcc.entity.User;
import cn.edu.svtcc.mapper.UserMapper;
import cn.edu.svtcc.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
