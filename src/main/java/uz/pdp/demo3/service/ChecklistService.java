package uz.pdp.demo3.service;


import uz.pdp.demo3.entity.User;
import uz.pdp.demo3.payload.ApiResponse;
import uz.pdp.demo3.payload.CheckListDTO;
import uz.pdp.demo3.payload.CheckListItemDTO;

public interface ChecklistService {
    ApiResponse addOrEditChecklist(CheckListDTO checkListDTO, Long wId, User user);
    ApiResponse deleteChecklist(Long chId, User user);
    ApiResponse addOrEditChecklistItem(CheckListItemDTO checkListItemDTO, Long wId, User user);
    ApiResponse deleteChecklistItem(Long chId, User user);


}
