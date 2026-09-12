package com.ecomm.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.product.dto.BrandDto;
import com.ecomm.product.request.AddBrandRequest;
import com.ecomm.product.request.UpdateBrandRequest;


public interface BrandService {
	
	BrandDto addBrand(AddBrandRequest request,MultipartFile image);
	
	BrandDto getById(Integer brandId);
	
	List<BrandDto> getAllBrands();
	
	void deleteBrandById(Integer brandId);
	
	BrandDto updateBrand(Integer brandId,UpdateBrandRequest request,MultipartFile image);

}
