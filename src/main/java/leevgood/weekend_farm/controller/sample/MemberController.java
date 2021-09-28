package leevgood.weekend_farm.controller.sample;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.domain.dto.MemberDto;
import leevgood.weekend_farm.domain.dto.MemberLoginDto;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = "회원 관련 API")
public class MemberController {
    private final MemberService memberService;
    private final ModelMapper modelMapper;
    // 회원가입
    //회원가입 버튼 클릭시 발생 적절한 정보값인지 확인위해 입력정보 넘겨준다.
    @GetMapping("/v1/member/signupInfo")
    @ApiOperation(value="회원가입 시 입력한 회원정보", notes = "회원가입을 위해 입력한 정보들을 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> signupMemberInfo(@RequestBody MemberDto memberDto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(memberDto),headers, HttpStatus.OK);
    }

    //입력정보 확인 후 적절한 입력정보이면 Member를 등록한다.
    @PostMapping("/v1/member/signup")
    @ApiOperation(value="회원가입 시 입력한 회원정보", notes = "회원가입을 위해 입력한 정보들을 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> signupMemberSave(@RequestBody MemberDto memberDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        memberService.signupMember(memberDto);

        return new ResponseEntity<>(Message.okMessage(memberDto),headers, HttpStatus.OK);
    }



    // 회원 정보 조회
    //회원정보를 넘겨준다.
    @GetMapping("/v1/member/lookup")
    @ApiOperation(value="현재 로그인 중인 회원정보", notes = "현재 로그인 중인 회원정보를 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> MemberInfoEdit(@RequestParam Long memberId) {
        Member member = memberService.findById(memberId);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(modelMapper.map(member, MemberDto.class)),headers, HttpStatus.OK);
    }

    //폼 입력 후 로그인 버튼 클릭 시 발생
    @GetMapping("/v1/member/login")
    @ApiOperation(value="로그인 정보", notes = "입력한 아이디, 패스워드를 넘겨받습니다.")
    public ResponseEntity<Message> memberLogin(@RequestParam MemberLoginDto memberLoginDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(memberLoginDto),headers, HttpStatus.OK);
    }

    // 회원 정보 수정

    //회원정보 수정 후 확인 버튼 클릭시 매핑되는 메서드... 수정된 회원정보 저장
    @GetMapping("/v1/member/modified")
    @ApiOperation(value="수정 정보를 멤버에 적용."
            , notes = "수정된 정보들을 기존 회원정보에 적용합니다.")
    public ResponseEntity<Message> MemberInfoChange(@RequestBody Long memberId,
                                                    @RequestBody MemberDto memberDto
    ){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(memberService.editMemberInfo(memberId,memberDto)),headers,HttpStatus.OK);
    }


    // 회원 탈퇴
    @PostMapping("/v1/member/modified")
    @ApiOperation(value="수정 정보를 멤버에 적용."
            , notes = "수정된 정보들을 기존 회원정보에 적용합니다.")
    public ResponseEntity<Message> MemberDelete(@RequestBody Long memberId
    ){
        String result = memberService.deleteMember(memberId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(result),headers,HttpStatus.OK);
    }


}
