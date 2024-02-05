package ru.sakaevrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakaevrs.exception.ResourceNotFoundException;
import ru.sakaevrs.model.Project;
import ru.sakaevrs.model.User;
import ru.sakaevrs.model.UsersProject;
import ru.sakaevrs.repository.ProjectRepository;
import ru.sakaevrs.repository.UserRepository;
import ru.sakaevrs.repository.UsersProjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserProjectService {

    @Autowired
    private UsersProjectRepository usersProjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<User> getUsersByProjectId(Long projectId) {
        Optional<List<UsersProject>> optionalUsersProjects = usersProjectRepository.findByProjectId(projectId);

        List<UsersProject> usersProjects = optionalUsersProjects.orElseThrow(() -> new ResourceNotFoundException("No UsersProject found for the given projectId"));

        return usersProjects.stream()
                .map(usersProject -> userRepository.findById(usersProject.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found")))
                .collect(Collectors.toList());
    }

    public List<Project> getProjectsByUserId(Long userId) {
        Optional<List<UsersProject>> optionalUsersProjects = usersProjectRepository.findByUserId(userId);

        List<UsersProject> usersProjects = optionalUsersProjects.orElseThrow(() -> new ResourceNotFoundException("No UsersProject found for the given userId"));

        return usersProjects.stream()
                .map(usersProject -> projectRepository.findById(usersProject.getProjectId())
                        .orElseThrow(() -> new ResourceNotFoundException("Project not found")))
                .collect(Collectors.toList());
    }


    public void addUserToProject(Long userId, Long projectId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project not found");
        }

        UsersProject usersProject = new UsersProject();
        usersProject.setUserId(userId);
        usersProject.setProjectId(projectId);
        usersProjectRepository.save(usersProject);
    }

    public void removeUserFromProject(Long userId, Long projectId) {
        UsersProject usersProject = usersProjectRepository.findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() -> new ResourceNotFoundException("User-Project relation not found"));

        usersProjectRepository.delete(usersProject);
    }




}