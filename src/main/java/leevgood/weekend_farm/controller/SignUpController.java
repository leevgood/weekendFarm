package leevgood.weekend_farm.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.config.StatusEnum;
import leevgood.weekend_farm.service.MemberService;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.domain.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@Api(tags = "로그인 관련 API")
public class SignUpController {
    private final MemberService memberService;

    //회원가입 버튼 클릭시 발생 적절한 정보값인지 확인위해 입력정보 넘겨준다.
    //이동완료
    @GetMapping("/signupBefore")
    @ApiOperation(value="회원가입 시 입력한 회원정보", notes = "회원가입을 위해 입력한 정보들을 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> signupMemberInfo(@RequestBody MemberDto memberDto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(memberDto),headers, HttpStatus.OK);
    }

    //입력정보 확인 후 적절한 입력정보이면 Member를 등록한다.
    //이동완료
    @PostMapping("/signupAfter")
    @ApiOperation(value="회원가입 시 입력한 회원정보", notes = "회원가입을 위해 입력한 정보들을 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> signupMemberSave(@RequestBody MemberDto memberDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(memberDto),headers, HttpStatus.OK);
    }

}
