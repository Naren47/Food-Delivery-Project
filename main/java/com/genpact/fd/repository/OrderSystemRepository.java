package com.genpact.fd.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.genpact.fd.entity.OrderSystem;

public interface OrderSystemRepository extends JpaRepository<OrderSystem,Long>{
	
	List<OrderSystem> findByNumber(Long Number);
	
//	@Query("update o ordersystem o SET o.status=:accept where o.id=id ")
//	List<OrderSystem> findById(@Param("id")Long id,@Param("status") String accept);

//	UPDATE Customers
//	SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
//	WHERE CustomerID = 1;
}
