package org.oos.persistence;



import org.oos.domain.MemberVO;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberVO, String> {

	
}
