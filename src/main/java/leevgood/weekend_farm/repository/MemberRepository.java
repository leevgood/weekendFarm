package leevgood.weekend_farm.repository;

import leevgood.weekend_farm.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
