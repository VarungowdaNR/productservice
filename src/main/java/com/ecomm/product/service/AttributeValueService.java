package com.ecomm.product.service;

import java.util.List;

import com.ecomm.product.dto.AttributeValueDto;
import com.ecomm.product.request.AddAttributeValueRequest;
import com.ecomm.product.request.UpdateAttributeValueRequest;


public interface AttributeValueService {
	
	AttributeValueDto addAttributeValue(AddAttributeValueRequest request);
	
	List<AttributeValueDto> getValuesByAttributeId(Integer attributeId);
	
	AttributeValueDto getValueById(Integer valueId);
	
	AttributeValueDto updateAttributeValue(Integer valueId,UpdateAttributeValueRequest request);
	
	void deleteAttributeValue(Integer valueId);

}
