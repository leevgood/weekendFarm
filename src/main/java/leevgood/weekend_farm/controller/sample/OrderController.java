package leevgood.weekend_farm.controller.sample;

import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.domain.dto.OrderDto;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //작물과 옵션 선택 후 장바구니 담기를 하지않고 바로주문을 선택 할 시 작동
    @GetMapping("/directOrder")
    @ApiOperation(value="구매확정 기능", notes = "구매확정 버튼을 누르면 장바구니에 저장된 것들을 포함하여 모두 구매확정됩니다.")
    public void orderDirect(@RequestParam OrderDto orderDto) {

    }

    @GetMapping("/order/{id}")
    public void getOrderInfo(@PathVariable Long id){

    }
}
