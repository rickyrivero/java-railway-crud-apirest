package com.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.apirest.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
