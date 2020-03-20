package lyk.community.controller;

import lyk.community.dto.PaginationDTO;
import lyk.community.dto.QuestionDTO;
import lyk.community.mapper.QuestionMapper;
import lyk.community.mapper.UserMapper;
import lyk.community.model.Question;
import lyk.community.model.User;
import lyk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

     @Autowired
   public QuestionService questionService;

    @GetMapping("/")
   public String index(HttpServletRequest request,
                      Model model,
                       @RequestParam(name="page",defaultValue = "1") Integer page,
                       @RequestParam(name="page",defaultValue = "2") Integer size
    ){
        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);


       return "index";
   }
}
