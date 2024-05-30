package com.example.usermanagement.service;

import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com";

    public User addUser(User user) {
        if (validateUserId(user.getUserId())) {
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid userId");
        }
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id)
                .or(() -> fetchAndSaveUserFromExternalApi(id));
    }

    public List<User> getUsersByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Integer id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setTitle(updatedUser.getTitle());
                    user.setBody(updatedUser.getBody());
                    return userRepository.save(user);
                }).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private boolean validateUserId(Integer userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = EXTERNAL_API_URL + "/users/" + userId;
        try {
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Optional<User> fetchAndSaveUserFromExternalApi(Integer userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = EXTERNAL_API_URL + "/users/" + userId;
        try {
            User user = restTemplate.getForObject(url, User.class);
            if (user != null) {
                return Optional.of(userRepository.save(user));
            }
        } catch (Exception e) {
            //  exception
        }
        return Optional.empty();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}