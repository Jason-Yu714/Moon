package fun.moon5.controller;



/*
* 登录controller
*
* */

import fun.moon5.pojo.LoginInfo;
import fun.moon5.pojo.Result;
import fun.moon5.pojo.Users;
import fun.moon5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*") // 允许前端服务器域名访问
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result login(@RequestBody Users user) {
        LoginInfo info = userService.login(user);
        if (info != null) {
            return Result.success(info);
        }
        return Result.error("error");
    }
}
