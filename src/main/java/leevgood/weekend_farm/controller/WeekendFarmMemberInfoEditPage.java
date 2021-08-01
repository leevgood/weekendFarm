package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.service.MemberService;
import leevgood.weekend_farm.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Api(tags = "회원정보 수정관련 API")
//회원 정보 수정 화면
public class WeekendFarmMemberInfoEditPage {

    private final MemberService memberService;

    //회원정보를 넘겨준다.
    @GetMapping("/MemberInfoEdit")
    @ApiOperation(value="현재 로그인 중인 회원정보", notes = "현재 로그인 중인 회원정보를 담은 객체를 넘겨받습니다.")
    public Member MemberInfoEdit(@RequestParam Member member) {
        return member;
    }
    
    //회원정보 수정 후 확인 버튼 클릭시 매핑되는 메서드... 수정된 회원정보 저장
    @GetMapping("/MemberInfoChange")
    @ApiOperation(value="수정된 멤버."
            , notes = "수정된 정보들이 반영된 멤버 정보를 담은 회원객체를 넘겨받습니다.")
    public void MemberInfoChange(@RequestParam Member member,
                                 @RequestParam Member changedMember
    ){
        try{
            Long memberId = member.getId();
            Optional<Member> optionalExistingMember = memberService.findById(member);
            Member existingMember = optionalExistingMember.get();

            existingMember.setLogin_id(changedMember.getLogin_id());
            existingMember.setUpdate_date(changedMember.getUpdate_date());
            existingMember.setPassword(changedMember.getPassword());
            existingMember.setEmail(changedMember.getEmail());
            existingMember.setMember_name(changedMember.getMember_name());
            existingMember.setTel_number(changedMember.getTel_number());
            memberService.save(existingMember);
        }
        catch (NullPointerException e){
            System.out.println(e.toString());
        }
    }

}
