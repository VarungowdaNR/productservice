package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.ProductVariantDto;
import com.ecomm.product.request.AddProductVariantRequest;
import com.ecomm.product.request.UpdateProductVariantRequest;

public interface ProductVariantService {
	
	ProductVariantDto addProductVariant(Integer productId,AddProductVariantRequest request);
	
	ProductVariantDto updateProductVariant(UpdateProductVariantRequest request);  //instead of id -- sku
    
	List<ProductVariantDto> getVariantsByProductId(Integer productId);
	
	ProductVariantDto getVariantBySku(String sku);
	
	void deleteProductVariant(Integer productVariantId);
}
