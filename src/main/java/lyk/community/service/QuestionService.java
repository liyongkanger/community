package lyk.community.service;

import lyk.community.dto.PaginationDTO;
import lyk.community.dto.QuestionDTO;
import lyk.community.mapper.QuestionMapper;
import lyk.community.mapper.UserMapper;
import lyk.community.model.Question;
import lyk.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

     @Autowired
     private QuestionMapper questionMapper;

     @Autowired
     private UserMapper userMapper;



    public PaginationDTO list(Integer page, Integer size) {

            Integer offset = size * (page - 1 );

            PaginationDTO paginationDTO = new PaginationDTO();
            Integer totalCount = questionMapper.count();
            paginationDTO.setPagination(totalCount,page,size);

            if(page<1){
                page = 1;
            }
            if(page > paginationDTO.getTotalPage()){
                page= paginationDTO.getTotalPage();
            }



        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
           User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

  

        paginationDTO.setQuestions(questionDTOList);

        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
    }

    public User findByToken(String token) {
        User byToken = userMapper.findByToken(token);
        return  byToken;
    }
}
