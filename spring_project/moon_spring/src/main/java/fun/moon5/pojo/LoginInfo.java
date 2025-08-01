package fun.moon5.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* 封装登录结果*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {

    private Integer id;
    private String usernmae;
    private String password;
    private String token;


}
