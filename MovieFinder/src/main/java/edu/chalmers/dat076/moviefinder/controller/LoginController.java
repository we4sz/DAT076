/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.controller;

import edu.chalmers.dat076.moviefinder.model.User;
import edu.chalmers.dat076.moviefinder.model.UserLoginModel;
import edu.chalmers.dat076.moviefinder.model.UserRole;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for the api/login endpoint. This endpoint is used to login and logout
 * users.
 * @author John
 */
@Controller
@RequestMapping("api/login")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    User login(HttpSession s, @RequestBody UserLoginModel login) {
        User u;

        if (login.getUsername().equals("admin")) {
            u = new User(login.getUsername(), UserRole.ADMIN);
        } else {
            u = new User(login.getUsername(), UserRole.VIEWER);
        }

        s.setAttribute("user", u);
        return u;
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public @ResponseBody
    User getMe(HttpSession s) {
        User u = (User) s.getAttribute("user");
        return u;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void logout(HttpSession s) {
        s.removeAttribute("user");
    }

}
