package com.blogapplication.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogapplication.Payload.CategoryDTO;
@Service
public interface CategoryService {
    
    // create category
    CategoryDTO createCategory(CategoryDTO CategoryDto); 

    // update category
    CategoryDTO updateCategory(CategoryDTO CategoryDto, Integer categoryId); 

    // delete category
    void deleteCategory(Integer categoryId); 

    // get category
    CategoryDTO getCategory(Integer categoryId); 

    // get all category 
    List<CategoryDTO> getAllCategory(); 

}
