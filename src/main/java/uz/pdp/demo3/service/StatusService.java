package uz.pdp.demo3.service;


import uz.pdp.demo3.entity.User;
import uz.pdp.demo3.payload.ApiResponse;
import uz.pdp.demo3.payload.StatusDTO;

public interface StatusService {
    ApiResponse addOrEditStatus(StatusDTO statusDTO, User user);
    ApiResponse deleteStatus(Long statusId, User user);

}
