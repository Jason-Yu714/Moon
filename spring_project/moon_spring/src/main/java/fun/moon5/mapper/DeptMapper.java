package fun.moon5.mapper;

import fun.moon5.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from test")
    public List<Dept> findAll();
}
