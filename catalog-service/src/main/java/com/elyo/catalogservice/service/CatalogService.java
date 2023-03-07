package com.elyo.catalogservice.service;

import com.elyo.catalogservice.entity.CatalogEntity;


public interface CatalogService {

    Iterable<CatalogEntity> getAllCatalogs();
}
