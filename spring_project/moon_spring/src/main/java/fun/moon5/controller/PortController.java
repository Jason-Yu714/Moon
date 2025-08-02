package fun.moon5.controller;


import fun.moon5.pojo.Port;
import fun.moon5.pojo.Result;
import fun.moon5.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // 允许前端服务器域名访问
@RestController
public class PortController {

    @Autowired
    private PortService deptService;

    //produces  支持更多的请求头
    @GetMapping(value = "port", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result list(@RequestParam(value = "port",defaultValue = "0") Integer port){  //接收前端返回的port,设置默认值为0
        List<Port> deptList = deptService.findAll(port);
        return Result.success(deptList);
    }
}
