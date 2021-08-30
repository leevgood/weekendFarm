package leevgood.weekend_farm.controller.sample;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.domain.dto.CropsStatusDto;
import leevgood.weekend_farm.domain.dto.OrderDto;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.domain.entity.Order;
import leevgood.weekend_farm.service.CartService;
import leevgood.weekend_farm.service.OrderService;
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
@Api(tags = "주문 관련 API")
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;
    // 주문하기
    // 주문 목록 조회
    // 주문 상회 조회

    //특정 카트의 주문확정 버튼을 누르면 해당 카트의 아이템들이 구매확정된다.
    @PostMapping("v1/{cartId}/order")
    @ApiOperation(value="장바구니 구매 확정", notes = "구매확정 버튼을 누르면 구매완료됩니다.")
    public ResponseEntity<Message> confirmPurchase(@PathVariable("cartId") Long cartId){

        Order order = cartService.confirmPurchase(cartId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(order),headers, HttpStatus.OK);
    }

    //현재 로그인 중인 회원의 재배 중인 작물들의 정보를 넘겨준다... Products에 저장된 데이터들이 해당 -> Crops들의 리스트
    @GetMapping("/cropsStatus")
    @ApiOperation(value="재배중인 작물 정보", notes = "해당 회원의 재배 중인 작물 정보를 담은 객체를 넘겨받습니다..")
    public ResponseEntity<Message> cropsStatus(@RequestParam Long memberId) {

        List<CropsStatusDto> cropsStatusDtoLists = orderService.getCropsStatus(memberId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(cropsStatusDtoLists),headers, HttpStatus.OK);

    }


    //작물과 옵션 선택 후 장바구니 담기를 하지않고 바로주문을 선택 할 시 작동
    @GetMapping("/directOrder")
    @ApiOperation(value="구매확정 기능", notes = "구매확정 버튼을 누르면 장바구니에 저장된 것들을 포함하여 모두 구매확정됩니다.")
    public void orderDirect(@RequestParam OrderDto orderDto) {

    }

    @GetMapping("/order/{id}")
    public void getOrderInfo(@PathVariable Long id){

    }
}
