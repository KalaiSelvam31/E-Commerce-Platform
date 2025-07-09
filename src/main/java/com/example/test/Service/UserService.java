package com.example.test.Service;

import com.example.test.Model.Users;
import com.example.test.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo u_repo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder ;

    public String register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        u_repo.save(user);
        return "Added";
    }


    public List<Users> getUser() {
        return u_repo.findAll();
    }

    public Authentication authenticate(Authentication authRequest){
        return authenticationManager.authenticate(authRequest);
    }

    public Users getUserByUsername(String Username){
        return  u_repo.findUsersByUsername(Username).orElseThrow(()->new RuntimeException("User not found"));
    }
}
