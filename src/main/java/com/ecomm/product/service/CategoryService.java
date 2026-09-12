package com.ecomm.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.CategoryDto;
import com.ecomm.product.request.AddCategoryRequest;
import com.ecomm.product.request.UpdateCategoryRequest;

public interface CategoryService {

    CategoryDto addCategory(
            AddCategoryRequest request,
            MultipartFile image
    );

    CategoryDto getById(Integer categoryId);

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getSubCategories(String parentCategoryName);

    CategoryDto updateCategory(
            Integer categoryId,
            UpdateCategoryRequest request,
            MultipartFile image
    );

    void deleteCategoryById(Integer categoryId);
}