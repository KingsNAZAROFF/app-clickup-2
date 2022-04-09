package uz.pdp.demo3.service;


import uz.pdp.demo3.entity.User;
import uz.pdp.demo3.payload.ApiResponse;
import uz.pdp.demo3.payload.ProjectDTO;
import uz.pdp.demo3.payload.ProjectUserDTO;

public interface ProjectService {

    ApiResponse addProject(ProjectDTO projectDTO, User user);

    ApiResponse editProject(Long projectId, ProjectDTO projectDTO, User user);

    ApiResponse deleteProject(Long projectId, User user);

    ApiResponse addProjectUser(ProjectUserDTO projectUserDTO, User user);

    ApiResponse editProjectUser(Long projectUserId, ProjectUserDTO projectUserDTO, User user);

    ApiResponse deleteProjectUser(Long projectUserId, User user);


}
