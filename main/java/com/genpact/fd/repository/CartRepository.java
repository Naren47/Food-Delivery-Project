package com.genpact.fd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genpact.fd.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

}
