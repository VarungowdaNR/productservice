package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.ProductAttributeDto;
import com.ecomm.product.request.AddProductAttributeRequest;
import com.ecomm.product.request.UpdateProductAttributeRequest;


public interface ProductAttributeService {
	
	
	public ProductAttributeDto addAttribute(AddProductAttributeRequest request);
	
	List<ProductAttributeDto> getAllAttributes();
	
	ProductAttributeDto getAttributeById(Integer attributeId);
	
	ProductAttributeDto updateAttribute(Integer attributeId,UpdateProductAttributeRequest request);
	
	void deleteAttribute(Integer attributeId);

}
