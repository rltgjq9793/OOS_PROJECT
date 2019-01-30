package org.oos.persistence;



import org.oos.domain.SellerVO;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<SellerVO, String> {

	
}
