package uz.pdp.demo3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.demo3.entity.User;
import uz.pdp.demo3.payload.ApiResponse;
import uz.pdp.demo3.payload.CheckListDTO;
import uz.pdp.demo3.payload.CheckListItemDTO;
import uz.pdp.demo3.security.CurrentUser;
import uz.pdp.demo3.service.ChecklistService;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/checklist")
public class CheckListController {
    @Autowired
    ChecklistService checklistService;


    @PostMapping
    public HttpEntity<?> addOrEditChecklist(@Valid @RequestBody CheckListDTO checkListDTO, @RequestParam Long wId, @CurrentUser User user) {
        ApiResponse apiResponse = checklistService.addOrEditChecklist(checkListDTO, wId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{chId}")
    public HttpEntity<?> deleteChecklist(@PathVariable Long chId, @CurrentUser User user) {
        ApiResponse apiResponse = checklistService.deleteChecklist(chId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }


    @PostMapping
    public HttpEntity<?> addOrEditChecklistItem(@Valid @RequestBody CheckListItemDTO checkListItemDTO, @RequestParam Long wId, @CurrentUser User user) {
        ApiResponse apiResponse = checklistService.addOrEditChecklistItem(checkListItemDTO, wId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @DeleteMapping("/checklistItem/{chId}")
    public HttpEntity<?> deleteChecklistItem(@PathVariable Long chId, @CurrentUser User user) {
        ApiResponse apiResponse = checklistService.deleteChecklistItem(chId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
