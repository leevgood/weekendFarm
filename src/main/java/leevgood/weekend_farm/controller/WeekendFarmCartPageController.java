package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.domain.entity.Cart_item;
import leevgood.weekend_farm.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "장바구니 화면관련 API")
//장바구니 화면에 대한 컨트롤러
public class WeekendFarmCartPageController {

    // 장바구니 정보를 넘겨준다.
    @GetMapping("/cartInfo")
    @ApiOperation(value="장바구니 정보 조회", notes = "장바구니 정보를 담은 객체를 넘겨받습니다.")
    public List<Cart_item> cartInfo(@RequestParam Member member){
         List<Cart_item> cart_items =  member.getCart().getCart_items();

         return cart_items;
    }

    //주문 확정에 대한 url은 WeekendFarmSelectCropsController에 있다.
}
