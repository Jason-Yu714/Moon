package fun.moon5.service;

import fun.moon5.pojo.LoginInfo;
import fun.moon5.pojo.Users;

import java.util.List;

public interface UserService {

    //注册
    void regist(Users user);


    List<Users> list();

    LoginInfo login(Users user);


}
