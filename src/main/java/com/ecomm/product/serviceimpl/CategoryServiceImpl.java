package com.ecomm.product.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.CategoryDto;
import com.ecomm.product.entity.Category;
import com.ecomm.product.exception.AppException;
import com.ecomm.product.repo.CategoryRepo;
import com.ecomm.product.request.AddCategoryRequest;
import com.ecomm.product.request.UpdateCategoryRequest;
import com.ecomm.product.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo crepo;

    private final ModelMapper mapper;

    // =========================================================
    // ADD CATEGORY
    // =========================================================

    @Override
    @Transactional
    public CategoryDto addCategory( AddCategoryRequest request,MultipartFile image) {

        // -----------------------------------------------------
        // Check duplicate category
        // -----------------------------------------------------

        Category existingCategory =crepo.findByCategoryName(request.getCategoryName().trim()).orElse(null);

        if (existingCategory != null) {

            throw new AppException("Category already exists!",HttpStatus.CONFLICT);
        }

        // -----------------------------------------------------
        // Convert Request -> Entity
        // -----------------------------------------------------

        Category category =mapper.map(request, Category.class);

        // -----------------------------------------------------
        // Parent Category
        // -----------------------------------------------------

        if (request.getParentCategoryName() != null&& !request.getParentCategoryName().trim().isEmpty()) {

            Category parent = crepo.findByCategoryName( request.getParentCategoryName().trim()).orElseThrow(() -> new AppException(
                                    "No parent category found!",
                                    HttpStatus.BAD_REQUEST
                            )
                    );

            category.setParentCategory(parent);
        }

        // -----------------------------------------------------
        // Save Category
        // -----------------------------------------------------

        Category savedCategory = crepo.save(category);

        // -----------------------------------------------------
        // Convert Entity -> DTO
        // -----------------------------------------------------

        return mapper.map(savedCategory, CategoryDto.class);
    }

    // =========================================================
    // GET CATEGORY BY ID
    // =========================================================

    @Override
    public CategoryDto getById(Integer categoryId) {

        Category category =crepo.findById(categoryId).orElseThrow(
                                () -> new AppException("Category not found!", HttpStatus.NOT_FOUND));

        return mapper.map(category, CategoryDto.class);
    }

    // =========================================================
    // GET ALL CATEGORIES
    // =========================================================

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = crepo.findAll();

        return categories.stream().map(category ->mapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    // =========================================================
    // GET SUB CATEGORIES
    // =========================================================

    @Override
    public List<CategoryDto> getSubCategories(
            String parentCategoryName) {

        Category parent =
                crepo.findByCategoryName(
                        parentCategoryName.trim()
                ).orElseThrow(() -> new AppException( "Parent category not found!",HttpStatus.NOT_FOUND));

        return parent.getSubCategories()
                .stream()
                .map(category ->
                        mapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    // =========================================================
    // UPDATE CATEGORY
    // =========================================================

    @Override
    @Transactional
    public CategoryDto updateCategory(
            Integer categoryId,
            UpdateCategoryRequest request,
            MultipartFile image) {

        Category category =
                crepo.findById(categoryId)
                        .orElseThrow(() -> new AppException("Category not found!", HttpStatus.NOT_FOUND));

        // -----------------------------------------------------
        // Update category name
        // -----------------------------------------------------

        if (request.getCategoryName() != null
                && !request.getCategoryName().trim().isEmpty()) {

            category.setCategoryName(
                    request.getCategoryName().trim()
            );
        }

        // -----------------------------------------------------
        // Update description
        // -----------------------------------------------------

        if (request.getDescription() != null) {

            category.setDescription(
                    request.getDescription()
            );
        }

        // -----------------------------------------------------
        // Update parent category
        // -----------------------------------------------------

        if (request.getParentCategoryName() != null
                && !request.getParentCategoryName()
                        .trim()
                        .isEmpty()) {

            Category parent =
                    crepo.findByCategoryName(
                            request.getParentCategoryName().trim()
                    ).orElseThrow(
                            () -> new AppException("Parent category not found!", HttpStatus.BAD_REQUEST));

            category.setParentCategory(parent);
        }

        Category updatedCategory =
                crepo.save(category);

        return mapper.map(updatedCategory,CategoryDto.class
        );
    }

    // =========================================================
    // DELETE CATEGORY
    // =========================================================

    @Override
    @Transactional
    public void deleteCategoryById(Integer categoryId) {

        Category category =
                crepo.findById(categoryId)
                        .orElseThrow(() -> new AppException("Category not found!",HttpStatus.NOT_FOUND));
        crepo.delete(category);
    }
}