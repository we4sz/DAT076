/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (To be) controller for the api/admin endpoint(s).
 *
 * This route is filtered via the UserFilter to only be accessible as a user with role admin.
 * @author John
 */
@Controller
@RequestMapping("api/admin")
public class AdminController {
}
