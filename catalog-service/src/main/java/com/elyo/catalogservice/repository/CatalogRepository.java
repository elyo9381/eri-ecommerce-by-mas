package com.elyo.catalogservice.repository;

import com.elyo.catalogservice.entity.CatalogEntity;
import org.springframework.data.repository.CrudRepository;


public interface CatalogRepository extends CrudRepository<CatalogEntity, Long> {

    CatalogEntity findByProductId(String productId);
}
