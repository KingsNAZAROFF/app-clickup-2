package uz.pdp.demo3.service;




import uz.pdp.demo3.entity.User;
import uz.pdp.demo3.payload.ApiResponse;
import uz.pdp.demo3.payload.CategoryDTO;
import uz.pdp.demo3.payload.CategoryUserDTO;

import java.util.UUID;

public interface CategoryService {
    ApiResponse addOrEditCategory(CategoryDTO categoryDTO, User user);

    ApiResponse deleteCategory(Long cId, User user);

    ApiResponse addCategoryUser(Long wId, CategoryUserDTO categoryUserDTO, User user);

    ApiResponse deleteCategoryUser(Long categoryUserId, User user);

    ApiResponse getAllCategoriesByUserId(UUID uId, User user);
}
