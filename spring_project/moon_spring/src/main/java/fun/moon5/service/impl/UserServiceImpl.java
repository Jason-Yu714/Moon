package fun.moon5.service.impl;

import fun.moon5.mapper.UserMapper;
import fun.moon5.pojo.LoginInfo;
import fun.moon5.pojo.Users;
import fun.moon5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    //注册用户信息
    @Override
    public void regist(Users user) {
        userMapper.insert(user);
    }

    @Override
    public List<Users> list() {
        return List.of();
    }

    @Override
    public LoginInfo login(Users user) {


        //查询用户信息
        Users u = userMapper.selectByUserAndPasswd(user);

        if (u != null) {
            return new LoginInfo(u.getId(),u.getUsername(),u.getPassword(),"");
        }

        //不存在
        return null;
    }
}
