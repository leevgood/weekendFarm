package leevgood.weekend_farm.controller.sample;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.config.StatusEnum;
import leevgood.weekend_farm.domain.dto.ForCartItemDto;
import leevgood.weekend_farm.domain.entity.Cart;
import leevgood.weekend_farm.service.CartService;
import leevgood.weekend_farm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = "장바구니 관련 API")
public class CartController {
    private final MemberService memberService;
    private final CartService cartService;

    // 장바구니에 상품 추가
    //작물과 옵션 선택 후 장바구니에 담기 클릭시 해당 url로 매핑
    @PostMapping("v1/newCart/{id}")
    @ApiOperation(value="장바구니 담기", notes = "장바구니 담기 버튼을 클릭하면 카트에 저장되고 해당 카트 아이디를 반환합니다.")
    public ResponseEntity<Message> putProductsInCart(@RequestBody ForCartItemDto forCartItemDto) {

        Long cartItemId = cartService.makeCartItem(forCartItemDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity(Message.okMessage(cartItemId),headers,HttpStatus.OK);
    }

    // 장바구니 조회
    @GetMapping("v1/{memberId}/cartList/")
    @ApiOperation(value="장바구니 정보 조회", notes = "장바구니 정보를 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> cartInfo(@PathVariable("memberId") Long memberId){
        List<Cart> cartList = memberService.findById(memberId).getCartList();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(cartList),headers, HttpStatus.OK);
    }

    // 장바구니 삭제
    @PostMapping("v1/{memberId}/{cartId}/deleteCart/")
    @ApiOperation(value="장바구니 정보 조회", notes = "장바구니 정보를 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> deleteCart(@PathVariable("memberId") Long memberId, @PathVariable("cartId") Long cartId){

        cartService.deleteCart(cartId);
        List<Cart> cartList = memberService.findById(memberId).getCartList();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(cartList),headers, HttpStatus.OK);
    }

}
