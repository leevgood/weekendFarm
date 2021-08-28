package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.dto.MemberDto;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public void save(Member member){
        memberRepository.save(member);
    }

    //id로 멤버를 조회,,,, MemberDto로 매핑후 반환
    public Member findById(Long memberId){

        Member member = memberRepository.findById(memberId).orElseThrow(()->new EntityNotFoundException(
                "user not existing. id :"+memberId
        ));

        return member;
    }


    public MemberDto signupMember(MemberDto memberDto){
       Member member = new Member();
       member = modelMapper.map(memberDto,Member.class);

       memberRepository.save(member);

       return memberDto;
    }

    //현재 사용안함 예시...
    public MemberDto findMember(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not exist. id : " + id));

        MemberDto memberDto = new MemberDto();

        // 맵핑 생략

        return memberDto;
    }

    //수정된 회원정보 담은 memberDto를 기존 회원에 적용 후 저장
    public MemberDto editMemberInfo(Long memberId, MemberDto memberDto) {
        Member existingMember = memberRepository.findById(memberId).orElseThrow(()->new EntityNotFoundException(
                "user not existing. id :"+memberId
        ));

        Member modifiedMember = modelMapper.map(memberDto,Member.class);

        //Dto에 없는 부분 추가 매핑
        modifiedMember.builder()
                .cartList(existingMember.getCartList())
                .orderList(existingMember.getOrderList());

        memberRepository.delete(existingMember);
        memberRepository.save(modifiedMember);

        return memberDto;
    }
}
