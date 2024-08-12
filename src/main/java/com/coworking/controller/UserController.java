package com.coworking.controller;

import com.coworking.application.dto.UserDTO;
import com.coworking.application.service.UserService;
import com.coworking.domain.model.User;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController {
    private final UserService userService;
    private final Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void handleGetUser(int userId, HttpServletResponse response) throws IOException {
        try {
            Optional<User> optionalUser = userService.getUserById(userId);
            response.setContentType("application/json");
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(), null);
                response.getWriter().write(gson.toJson(userDTO));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(gson.toJson("User not found"));
            }
        } catch (Exception e) {
            logger.error("Error retrieving user with ID {}: {}", userId, e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Internal server error"));
        }
    }

    public void handleGetAllUsers(HttpServletResponse response) throws IOException {
        try {
            List<User> users = userService.getAllUsers();
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(users));
        } catch (Exception e) {
            logger.error("Error retrieving all users: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Internal server error"));
        }
    }

    public void handleCreateUser(UserDTO userDTO, HttpServletResponse response) throws IOException {
        try {
            User user = new User(0, userDTO.getName(), userDTO.getEmail());
            User createdUser = userService.createUser(user);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(gson.toJson(createdUser));
        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Internal server error"));
        }
    }

    public void handleDeleteUser(int userId, HttpServletResponse response) throws IOException {
        try {
            boolean isDeleted = userService.deleteUserById(userId);
            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(gson.toJson("User not found"));
            }
        } catch (Exception e) {
            logger.error("Error deleting user with ID {}: {}", userId, e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Internal server error"));
        }
    }
}