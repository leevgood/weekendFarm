package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.config.StatusEnum;
import leevgood.weekend_farm.domain.entity.Cart;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.domain.entity.Order;
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
@Api(tags = "장바구니 화면관련 API")
//장바구니 화면에 대한 컨트롤러
public class CartPageController {
    private final CartService cartService;
    private final MemberService memberService;

    // 장바구니 정보를 넘겨준다.
    @GetMapping("/cartInfo/{id}")
    @ApiOperation(value="장바구니 정보 조회", notes = "장바구니 정보를 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> cartInfo(@PathVariable("id") Long memberId){
        List<Cart> cartList = memberService.findById(memberId).getCartList();


        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("success");
        message.setData(cartList);

        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }

    //주문 확정기능 주문확정을 누르면 장바구니에 있는 모든 아이템들이 구매확정된다.
    @PostMapping("/comfirmPurchase/{id}")
    @ApiOperation(value="장바구니 구매 확정", notes = "구매확정 버튼을 누르면 구매완료됩니다.")
    public ResponseEntity<Message> confirmPurchase(@PathVariable("id") Long cartId){

        Cart cart = cartService.findById(cartId);
        Order order = cartService.confirmPurchase(cartId);

        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("success");
        message.setData(order);

        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }

}
