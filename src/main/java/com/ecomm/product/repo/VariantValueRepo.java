package com.ecomm.product.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.product.entity.VariantValue;

public interface VariantValueRepo extends JpaRepository<VariantValue, Integer> {
	
	List<VariantValue> findByProductVariantProductVariantId(Integer productVariantId);

}
