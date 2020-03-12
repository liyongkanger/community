package lyk.community.mapper;


import lyk.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modify,creator,tag)values(#{title},#{description},#{gmtCreate},#{gmtModify},#{creator},#{tag})")
    public void create(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset")Integer offset,@Param(value = "size") Integer size);
     @Select("select count(1) from question")
    Integer count();
}