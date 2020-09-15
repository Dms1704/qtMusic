package com.xupt.controller;

import com.xupt.domain.User;
import com.xupt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"user"})
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/testFindAll")
    public String testFindAll(){
        return "success";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute User user){
        if ((userService.register(user) == 1)) {
            return "success";
        }
        else
            return "fail";
    }

    @RequestMapping("/login")
    public void login(String username, String password, HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        if (userService.login(username, password) == 1){
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
            response.sendRedirect(request.getContextPath()+"/song/listSong?currentPage=1");
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }
}
