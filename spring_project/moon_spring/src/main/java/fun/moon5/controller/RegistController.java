package fun.moon5.controller;

import fun.moon5.pojo.Result;
import fun.moon5.pojo.Users;
import fun.moon5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class RegistController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/regist", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result regist(@RequestBody Users user) {
        if(isNull(user.getUsername()) || isNull(user.getPassword())) {
            return Result.error("username or password is null");
        }

        if(isNull(user.getEmail())) {
            return Result.error("You email us null");
        }

        userService.regist(user);

        return Result.success();

    }

    //判断前端传回的数据是否为null
    public boolean isNull(String str) {
        if (str != null && !str.trim().isEmpty()) {
            return false;
        }
        return true;
    }


}
