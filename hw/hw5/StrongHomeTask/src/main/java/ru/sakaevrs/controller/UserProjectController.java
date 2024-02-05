package ru.sakaevrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
import ru.sakaevrs.dto.UserDTO;
import ru.sakaevrs.dto.ProjectDTO;
import ru.sakaevrs.model.User;
import ru.sakaevrs.model.Project;
import ru.sakaevrs.service.UserProjectService;

@Controller
@RequestMapping("/user-project")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;

    @GetMapping("/get-users/{projectId}")
    public ResponseEntity<List<UserDTO>> getUsersByProjectId(@PathVariable Long projectId) {
        List<User> users = userProjectService.getUsersByProjectId(projectId);
        List<UserDTO> userDTOs = users.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUsername(user.getUsername());
                    userDTO.setEmail(user.getEmail());
                    userDTO.setRole(user.getRole());
                    return userDTO;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("/get-projects/{userId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByUserId(@PathVariable Long userId) {
        List<Project> projects = userProjectService.getProjectsByUserId(userId);
        List<ProjectDTO> projectDTOs = projects.stream()
                .map(project -> {
                    ProjectDTO projectDTO = new ProjectDTO();
                    projectDTO.setName(project.getName());
                    projectDTO.setDescription(project.getDescription());
                    projectDTO.setCreatedDate(project.getCreatedDate());
                    return projectDTO;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(projectDTOs, HttpStatus.OK);
    }

    @PostMapping("/add-user/{userId}/{projectId}")
    public ResponseEntity<String> addUserToProject(@PathVariable Long userId, @PathVariable Long projectId) {
        userProjectService.addUserToProject(userId, projectId);
        return new ResponseEntity<>("User added to project successfully", HttpStatus.OK);
    }

    @PostMapping("/remove-user/{userId}/{projectId}")
    public ResponseEntity<String> removeUserFromProject(@PathVariable Long userId, @PathVariable Long projectId) {
        userProjectService.removeUserFromProject(userId, projectId);
        return new ResponseEntity<>("User removed from project successfully", HttpStatus.OK);
    }
}
