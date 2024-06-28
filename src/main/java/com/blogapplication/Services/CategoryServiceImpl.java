package com.blogapplication.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.Entity.Category;
import com.blogapplication.Exception.ResourceNotFound;
import com.blogapplication.Payload.CategoryDTO;
import com.blogapplication.Repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {
        
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category savedCategory = this.categoryRepository.save(category);
        
        return this.modelMapper.map(savedCategory, CategoryDTO.class); 
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO CategoryDto, Integer categoryId) {
        
        Category category = this.categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new ResourceNotFound("category", "Id", categoryId));
        
        category.setCategoryTitle(CategoryDto.getCategoryTitle());
        category.setCategoryDescription(CategoryDto.getCategoryDescription());

        Category updatedCategory = this.categoryRepository.save(category);
        
        return this.modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    @Override
	public void deleteCategory(Integer categoryId) {

		Category cat = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFound("Category ", "category id", categoryId));
		this.categoryRepository.delete(cat);
	}

    @Override
    public CategoryDTO getCategory(Integer categoryId) {

        Category category = this.categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFound("category", "Id", categoryId));

        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {

        List<Category> categoryList = this.categoryRepository.findAll();
        List<CategoryDTO> CategoryDtoList = categoryList.stream().map((category)-> this.modelMapper
                                            .map(category, CategoryDTO.class))
                                            .collect(Collectors.toList());

        return CategoryDtoList;
    }
    
}
