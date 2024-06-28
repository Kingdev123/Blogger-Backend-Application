package com.blogapplication.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.Payload.ApiResponse;
import com.blogapplication.Payload.CategoryDTO;
import com.blogapplication.Services.CategoryService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    //  create category
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto){
        CategoryDTO createCategoryDto = this .categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }
    
    //  update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDto, @PathVariable Integer categoryId){
        CategoryDTO updateCategoryDTO =  this.categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok(updateCategoryDTO);
    }

    //  delete category
    @DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully !!", true, LocalDateTime.now()),
				HttpStatus.OK);
	}

    //  get category
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<CategoryDTO>(this.categoryService.getCategory(categoryId), HttpStatus.FOUND);
    }

    //  get all category
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        return new ResponseEntity<List<CategoryDTO>>(this.categoryService.getAllCategory(), HttpStatus.OK);
    }
    
}
