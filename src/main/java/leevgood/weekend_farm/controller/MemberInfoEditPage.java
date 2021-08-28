package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.config.StatusEnum;
import leevgood.weekend_farm.domain.dto.MemberDto;
import leevgood.weekend_farm.service.MemberService;
import leevgood.weekend_farm.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Api(tags = "회원정보 수정관련 API")
//회원 정보 수정 화면
public class MemberInfoEditPage {

    private final MemberService memberService;
    private final ModelMapper modelMapper;

    //회원정보를 넘겨준다.
    @GetMapping("/MemberInfoEdit")
    @ApiOperation(value="현재 로그인 중인 회원정보", notes = "현재 로그인 중인 회원정보를 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> MemberInfoEdit(@RequestParam Long memberId) {
        Member member = memberService.findById(memberId);

        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("success");
        message.setData(modelMapper.map(member,MemberDto.class));

        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }
    
    //회원정보 수정 후 확인 버튼 클릭시 매핑되는 메서드... 수정된 회원정보 저장
    @GetMapping("/MemberInfoChange")
    @ApiOperation(value="수정 정보를 멤버에 적용."
            , notes = "수정된 정보들을 기존 회원정보에 적용합니다.")
    public ResponseEntity<Message> MemberInfoChange(@RequestBody Long memberId,
                                      @RequestBody MemberDto memberDto
    ){

        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("success");
        message.setData(memberService.editMemberInfo(memberId,memberDto));

        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

}
