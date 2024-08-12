package com.coworking.controller;

import com.coworking.application.dto.RoleDTO;
import com.coworking.application.service.RoleService;
import com.coworking.domain.model.Role;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleController {
    private final RoleService roleService;
    private final Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    public void handleGetRole(int roleId, HttpServletResponse response) throws IOException {
        try {
            Optional<Role> optionalRole = roleService.getRoleById(roleId);
            response.setContentType("application/json");
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                response.getWriter().write(gson.toJson(role));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(gson.toJson("Role not found"));
            }
        } catch (Exception e) {
            logger.error("Error retrieving role with ID {}: {}", roleId, e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Internal server error"));
        }
    }

    public void handleGetAllRoles(HttpServletResponse response) throws IOException {
        try {
            List<Role> roles = roleService.getAllRoles();
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(roles));
        } catch (Exception e) {
            logger.error("Error retrieving all roles: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Internal server error"));
        }
    }

    public void handleCreateRole(RoleDTO roleDTO, HttpServletResponse response) throws IOException {
        try {
            Role role = new Role(0, roleDTO.getRoleName());
            Role createdRole = roleService.createRole(role);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(gson.toJson(createdRole));
        } catch (Exception e) {
            logger.error("Error creating role: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Internal server error"));
        }
    }

    public void handleDeleteRole(int roleId, HttpServletResponse response) throws IOException {
        try {
            boolean isDeleted = roleService.deleteRoleById(roleId);
            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(gson.toJson("Role not found"));
            }
        } catch (Exception e) {
            logger.error("Error deleting role with ID {}: {}", roleId, e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Internal server error"));
        }
    }
}
