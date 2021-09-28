package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.dto.MemberDto;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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


    @Transactional
    public MemberDto signupMember(MemberDto memberDto){
       Member member = new Member();
       memberDto.setCreate_date(LocalDateTime.now());
       memberDto.setUpdate_date(LocalDateTime.now());
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

    @Transactional
    //수정된 회원정보 담은 memberDto를 기존 회원에 적용 후 저장
    public MemberDto editMemberInfo(Long memberId, MemberDto memberDto) {
        Member existingMember = memberRepository.findById(memberId).orElseThrow(()->new EntityNotFoundException(
                "user not existing. id :"+memberId
        ));

        Member modifiedMember = modelMapper.map(memberDto,Member.class);

        existingMember.setEmail(modifiedMember.getEmail());
        existingMember.setPassword(modifiedMember.getPassword());
        existingMember.setTelNumber(modifiedMember.getTelNumber());
        existingMember.setUpdateDate(LocalDateTime.now());

        memberRepository.save(existingMember);

        MemberDto modifiedMemberDto = modelMapper.map(existingMember,MemberDto.class);

        return modifiedMemberDto;
    }

    @Transactional
    public String deleteMember(Long memberId) {
        String message;

        Member deleteMember = memberRepository.findById(memberId)
                .orElseThrow(()->new EntityNotFoundException("user not exist. id : " + memberId));
        memberRepository.delete(deleteMember);

        return message = "member delete complete";
    }
}
