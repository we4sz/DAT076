/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.filter;

import edu.chalmers.dat076.moviefinder.model.User;
import edu.chalmers.dat076.moviefinder.model.UserRole;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A filter for authentication.
 * Limits access to /api/ if the user is not logged in.
 * Limits access to .../admin if the user is not logged in as admin.
 * @author John
 */
@WebFilter("/*")
public class UserFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String path = req.getRequestURI().substring(req.getContextPath().length());

        Object o = session.getAttribute("user");

        if (o == null) {
            if (path.toLowerCase().startsWith("/api/login/login")) {
                chain.doFilter(req, res);
                return;
            } else if (path.toLowerCase().startsWith("/api/")) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            } else {
                chain.doFilter(req, res);
                return;
            }
        }

        User u = (User) o;
        if (path.toLowerCase().startsWith("/api/admin") && u.getRole() != UserRole.ADMIN) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(req, res);
    }


}
