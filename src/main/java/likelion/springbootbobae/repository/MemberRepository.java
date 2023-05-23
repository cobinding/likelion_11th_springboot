package likelion.springbootbobae.repository;

import likelion.springbootbobae.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
