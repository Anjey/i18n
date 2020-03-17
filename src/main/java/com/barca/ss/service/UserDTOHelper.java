package com.barca.ss.service;

import com.barca.ss.domain.User;
import com.barca.ss.domain.UserRole;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class UserDTOHelper {

    public static User createEntity(MultipartFile file, User user) throws IOException {
        user.setEncodedImage(Base64.getEncoder().encodeToString(file.getBytes()));

        return user;
    }
}
