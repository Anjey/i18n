package com.barca.ss.service;

import com.barca.ss.domain.User;
import com.barca.ss.domain.UserRole;
import com.barca.ss.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.ROLE_USER);
        logger.info("save user " + user);
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        logger.info("find user by email " + email);
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException("No user present with email " + email);
    }

    public User update(User user) {
        logger.info("update user " + user);
        return userRepository.save(user);
    }

    public List<User> getUsersWithoutAccepting(Boolean acceptFlag) {
        logger.info("get all users without accepting certificate ");
        return userRepository.findAllByIsAcceptedAndEncodedImageNotNull(acceptFlag);
    }
}
