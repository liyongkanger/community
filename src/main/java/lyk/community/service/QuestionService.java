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
            Integer totalPage;
        if(totalCount%size == 0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }
            if(page<1){
                page = 1;
            }

            if(page > totalPage){
                page= totalPage;
            }


        paginationDTO.setPagination(totalCount,page);
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

        paginationDTO.setPagination(totalCount,page);

        return paginationDTO;
    }

    public User findByToken(String token) {
        User byToken = userMapper.findByToken(token);
        return  byToken;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {

        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);
        if(totalCount%size == 0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }

        Integer offset = size * (page - 1);

        PaginationDTO paginationDTO = new PaginationDTO();


        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalCount, page);

        List<Question> questions = questionMapper.listByUserId(userId,offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }


        paginationDTO.setQuestions(questionDTOList);

        paginationDTO.setPagination(totalCount, page);

        return paginationDTO;
    }
}
