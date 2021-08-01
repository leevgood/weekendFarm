package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.domain.dto.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "로그인 페이지 관련 API")
public class WeekendFarmLoginPageController {

    //폼 입력 후 로그인 버튼 클릭 시 발생
    @GetMapping("/memberLogin")
    @ApiOperation(value="로그인 정보", notes = "입력한 아이디, 패스워드를 넘겨받습니다.")
    public Member memberLogin(@RequestParam MemberLoginDto memberLoginDto){
        Member member = new Member();
        member.setLogin_id(memberLoginDto.getLogin_id());
        member.setPassword(memberLoginDto.getPassword());

        return member;
    }
}
