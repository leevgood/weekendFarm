package leevgood.weekend_farm.controller.sample;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.config.StatusEnum;
import leevgood.weekend_farm.domain.entity.Cart;
import leevgood.weekend_farm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = "장바구니 관련 API")
public class CartController {
    static MemberService memberService;

    // 장바구니에 상품 추가
    // 장바구니 조회
    // 장바구니 삭제

    @GetMapping("v1/{memberId}/cartList/")
    @ApiOperation(value="장바구니 정보 조회", notes = "장바구니 정보를 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> cartInfo(@PathVariable("memberId") Long memberId){
        List<Cart> cartList = memberService.findById(memberId).getCartList();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(cartList),headers, HttpStatus.OK);
    }
}
