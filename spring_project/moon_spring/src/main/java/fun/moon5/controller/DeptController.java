package fun.moon5.controller;


import fun.moon5.pojo.Dept;
import fun.moon5.pojo.Result;
import fun.moon5.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // 允许前端服务器域名访问
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //produces  支持更多的请求头
    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result list(@RequestParam(value = "id",defaultValue = "0") Integer id){  //接收前端返回的id,设置默认值为0
        List<Dept> deptList = deptService.findAll(id);
        return Result.success(deptList);
    }
}
