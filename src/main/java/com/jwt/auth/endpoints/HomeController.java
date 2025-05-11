package com.jwt.auth.endpoints;

import com.jwt.auth.domain.User;
import com.jwt.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        System.out.println("Getting users");
        return this.userService.getUsers();
    }

    @GetMapping("/load-profile")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }

}
