/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.filter;

import edu.chalmers.dat076.moviefinder.model.User;
import edu.chalmers.dat076.moviefinder.model.UserRole;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author John
 */
@Configuration
@WebFilter("/*")
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = request.getSession(true);
        String path = request.getRequestURI();

        System.out.println(path);

        Object o = session.getAttribute("user");

        if (o == null) {
            if (path.toLowerCase().startsWith("/moviefinder/login/login")) {
                chain.doFilter(req, response);
                return;
            } else {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        User u = (User) o;
        if (path.contains("/admin") && u.getRole() != UserRole.ADMIN) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(req, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
