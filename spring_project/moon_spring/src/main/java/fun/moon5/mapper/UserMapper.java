package fun.moon5.mapper;

import fun.moon5.pojo.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select id,username,password from users where username = #{username} and password = #{password}")
    Users selectByUserAndPasswd(Users user);

    @Insert("insert into users(email,username,password) values (#{email},#{username},#{password})")
    void insert(Users user);
}


