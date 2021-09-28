package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.config.StatusEnum;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.domain.dto.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@Api(tags = "로그인 페이지 관련 API")
public class LoginPageController {

    //폼 입력 후 로그인 버튼 클릭 시 발생
    //완료
    @GetMapping("/memberLogin")
    @ApiOperation(value="로그인 정보", notes = "입력한 아이디, 패스워드를 넘겨받습니다.")
    public ResponseEntity<Message> memberLogin(@RequestParam MemberLoginDto memberLoginDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(memberLoginDto),headers, HttpStatus.OK);
    }
}
