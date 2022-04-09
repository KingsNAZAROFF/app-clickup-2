package uz.pdp.demo3.service;


import uz.pdp.demo3.entity.User;
import uz.pdp.demo3.payload.ApiResponse;
import uz.pdp.demo3.payload.SpaceDTO;

public interface SpaceService {

    ApiResponse addSpace(SpaceDTO spaceDTO, User user);

    ApiResponse deleteSpace(Long spaceId, User user);

    ApiResponse getViewsBySpaceId(Long sId, User user);


}
