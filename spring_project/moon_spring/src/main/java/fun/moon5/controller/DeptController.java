package fun.moon5.controller;


import fun.moon5.pojo.Dept;
import fun.moon5.pojo.Result;
import fun.moon5.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //produces  支持更多的请求头
    @CrossOrigin(origins = "http://127.0.0.1:9873") // 允许前端服务器域名访问
    @GetMapping(value = "/test.html", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result list(){
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
}
