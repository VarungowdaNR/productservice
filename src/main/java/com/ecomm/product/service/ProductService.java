package com.ecomm.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.ProductDto;
import com.ecomm.product.request.AddProductRequest;
import com.ecomm.product.request.UpdateProductRequest;


public interface ProductService {
	
	ProductDto addProduct(AddProductRequest request,List<MultipartFile> images);
	
	ProductDto getProductById(Integer productId);
	
	List<ProductDto> getAllProducts();
	
	List<ProductDto> getProductsByCategory(String categoryName);
	
	List<ProductDto> getProductsByBrand(String brandName);
	
	ProductDto updateProduct(Integer productId , UpdateProductRequest request,List<MultipartFile> images);
	
	void deleteProductById(Integer productId);
	
	


}
