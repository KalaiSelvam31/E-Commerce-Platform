package com.example.test.Service;

import com.example.test.Configuration.UserPrincple;
import com.example.test.Model.Users;
import com.example.test.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepo u_repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> op =u_repo.findUsersByUsername(username);
        if(op.isEmpty()) throw new UsernameNotFoundException("User not Found");
        Users user =op.get();
        return new UserPrincple(user);

    }
}
