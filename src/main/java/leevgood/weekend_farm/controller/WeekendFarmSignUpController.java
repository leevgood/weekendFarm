package leevgood.weekend_farm.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.service.MemberService;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.domain.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "로그인 관련 API")
public class WeekendFarmSignUpController {
    private final MemberService memberService;

    //회원가입 버튼 클릭시 발생
    @PostMapping("/signup")
    @ApiOperation(value="회원가입 시 입력한 회원정보", notes = "회원가입을 위해 입력한 정보들을 담은 객체를 넘겨받습니다.")
    public Member signupMemberInfo(@RequestParam MemberDto memberDto){
            Member member = new Member();
            member.setLogin_id(memberDto.getLogin_id());
            member.setPassword(memberDto.getPassword());
            member.setCreate_date(memberDto.getCreate_date());
            member.setEmail(memberDto.getEmail());
            member.setTel_number(memberDto.getTel_number());
            member.setUpdate_date(memberDto.getUpdate_date());

            memberService.save(member);

            return member;
    }

}
