package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.config.StatusEnum;
import leevgood.weekend_farm.domain.dto.*;
import leevgood.weekend_farm.domain.entity.*;
import leevgood.weekend_farm.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = "작물선택 관련 api API")
public class SelectCropsController {

    private final CropsService cropsService;
    private final CropsOptionService cropsOptionService;
    private final AreaService areaService;
    private final CartService cartService;
    private final OrderService orderService;

    //작물 상세 선택화면 요청 url
    //이동 완료
    @GetMapping("/v1/selectCropsScreen/{id}")
    @ApiOperation(value="작물과 옵션 정보", notes = "작물의 정보와 작물에 대한 옵션 정보를 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> selectCropsScreen(@PathVariable("id") Long cropsId) {

        ProductsForCropDto productsForCropDto = new ProductsForCropDto(cropsService.findById(cropsId),
                areaService.getAllArea(),cropsOptionService.getAllCropsOption());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(productsForCropDto),headers, HttpStatus.OK);
    }

    //작물과 옵션 선택 후 장바구니에 담기 클릭시 해당 url로 매핑
    //이동 완료
    @PostMapping("putProductsInCart/{id}")
    @ApiOperation(value="장바구니 담기", notes = "장바구니 담기 버튼을 클릭하면 카트에 저장되고 해당 카트 아이디를 반환합니다.")
    public ResponseEntity<Message> putProductsInCart(@RequestBody ForCartItemDto forCartItemDto) {

        Long cartItemId = cartService.makeCartItem(forCartItemDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity(Message.okMessage(cartItemId),headers,HttpStatus.OK);
    }

    /*
        {
          memberId : ...
          product : [
            id : ....
            price : ...
          ]

        }


     */

    //작물과 옵션 선택 후 장바구니 담기를 하지않고 바로주문을 선택 할 시 작동
    //이동 완료
    @PostMapping("/directOrder")
    @ApiOperation(value="구매확정 기능", notes = "구매확정 버튼을 누르면 해당 상품이 구매확정되고 그 주문의 아이디가 반환됩니다.")
    public ResponseEntity<Message> orderDirect(@RequestBody ForOrderItemDto forOrderItemDto
    ){
        Long orderItemId = orderService.makeOrderItem(forOrderItemDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity(Message.okMessage(orderItemId), headers, HttpStatus.OK);
    }

}
