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
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length!=0)
        for (Cookie cookie : cookies) {
             if(cookie.getName().equals("token")){
                 String token = cookie.getValue();
                 User user = questionService.findByToken(token);
                 if(user!=null){
                     request.getSession().setAttribute("user",user);
                 }
                 break;
             }
        }
        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);


       return "index";
   }
}
