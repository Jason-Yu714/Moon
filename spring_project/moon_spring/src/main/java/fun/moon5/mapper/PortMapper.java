package fun.moon5.mapper;

import fun.moon5.pojo.Port;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PortMapper {

    @Select("select port,serverName,message,attackMethod from port where port = #{port}")
    public List<Port> findAll(@Param("port") Integer port);
}
