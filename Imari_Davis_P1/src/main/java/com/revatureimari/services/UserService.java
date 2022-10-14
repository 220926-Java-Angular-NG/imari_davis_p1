package com.revatureimari.services;

import com.revatureimari.models.User;
import com.revatureimari.repos.UserRepo;

import java.util.List;

public class UserService {
    private UserRepo userRepo;

    public UserService() {
        userRepo = new UserRepo();
    }

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public int createUser(User user) {
        return userRepo.create(user);
    }

    public List<User> getAllUsers() {
        return userRepo.getAll();
    }

    public User getUserById(int userId) {
        return userRepo.getById(userId);
    }

    public User updateUser(User user) {
        return userRepo.update(user);
    }

    public boolean deleteUser(User user) {
        return userRepo.delete(user);
    }

    public boolean deleteUserById(int userId) {
        return userRepo.deleteById(userId);
    }

    public User loginUser(User user) {
        return userRepo.loginUser(user);
    }

    public User loginUserByEmail(User user) {
        return userRepo.loginUserByEmail(user);
    }
}
