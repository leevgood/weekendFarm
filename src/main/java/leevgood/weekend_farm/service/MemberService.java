package leevgood.weekend_farm.service;

import javassist.NotFoundException;
import leevgood.weekend_farm.domain.dto.MemberDto;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(Member member){
        memberRepository.save(member);
    }

    public Optional<Member> findById(Member member){
        return memberRepository.findById(member.getId());
    }

    public MemberDto findMember(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not exist. id : " + id));

        MemberDto memberDto = new MemberDto();

        // 맵핑 생략

        return memberDto;
    }
}
