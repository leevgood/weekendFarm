package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.domain.entity.*;
import leevgood.weekend_farm.service.*;
import leevgood.weekend_farm.domain.dto.CropsAndOptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Api(tags = "작물선택 관련 api API")
public class WeekendFarmSelectCropsController {

    private final Cart_itemService cart_itemService;
    private final Order_itemService order_itemService;

    private final Crops_itemService cropsItemService;
    private final FertilizerService fertilizerService;
    private final PesticideService pesticideService;
    private final NutrientsService nutrientsService;
    private final AreaService areaService;

    //작물 상세 선택화면 요청 url
    @GetMapping("/selectCropsScreen")
    @ApiOperation(value="작물과 옵션 정보", notes = "작물의 정보와 작물에 대한 옵션 정보를 담은 객체를 넘겨받습니다.")
    public CropsAndOptionDto selectCropsScreen(@RequestParam Long crops_item_id) {
        Optional<CropsItem> crops_item = cropsItemService.findById(crops_item_id);
        List<Fertilizer> fertilizers = fertilizerService.findAll();
        List<Pesticide> pesticides = pesticideService.findAll();
        List<Nutrients> nutrientsList = nutrientsService.findAll();
        List<Area> areas = areaService.findAll();

        CropsAndOptionDto cropsAndOptionDto = new CropsAndOptionDto();
        cropsAndOptionDto.setCrops_item(crops_item);
        cropsAndOptionDto.setFertilizers(fertilizers);
        cropsAndOptionDto.setPesticides(pesticides);
        cropsAndOptionDto.setNutrientsList(nutrientsList);
        cropsAndOptionDto.setAreas(areas);

        return cropsAndOptionDto;
    }
    //작물과 옵션 선택 후 장바구니에 담기 클릭시 해당 url로 매핑
    @GetMapping("/putCropsInCart")
    @ApiOperation(value="장바구니 담기", notes = "장바구니 담기 버튼을 클릭하면 해당 핸들러로 매핑되어 적용됩니다.")
    public void putCropsInCart(@RequestParam CropsItem crops_item,
                               @RequestParam Area area,
                               @RequestParam Pesticide pesticide,
                               @RequestParam Fertilizer fertilizer,
                               @RequestParam Nutrients nutrients,
                               @RequestParam Member member
    ) {
        Cart_item cart_item = new Cart_item();
        cart_item.setArea(area);
        cart_item.setCrops_item(crops_item);
        cart_item.setFertilizer(fertilizer);
        cart_item.setNutrients(nutrients);
        cart_item.setPesticide(pesticide);
        cart_item.setPrice(crops_item.getPrice()+area.getPrice()+pesticide.getPesticide_price()
                +fertilizer.getFertilizer_price()+nutrients.getNutrients_price());

        Cart cart = member.getCart();
        cart.addPrice(cart_item.getPrice());
        cart_item.setCart(cart);

        cart_itemService.save(cart_item);

    }


    //작물과 옵션 선택 후 장바구니 담기를 하지않고 바로주문을 선택 할 시 작동
    @GetMapping("/directOrder")
    @ApiOperation(value="구매확정 기능", notes = "구매확정 버튼을 누르면 장바구니에 저장된 것들을 포함하여 모두 구매확정됩니다.")
    public void orderDirect(@RequestParam Member member) {


        //카트 안에 들어있는 것들을 확정시킨다.
        List<Cart_item> cart_items = cart_itemService.findAll();

        //카트 아이템을 오더 아이템에 복사 후 카트 아이템 삭제
        for(Cart_item cart_item : cart_items){
            Order_item order_item = new Order_item();
            order_item.setArea(order_item.getArea());
            order_item.setCrops_item(order_item.getCrops_item());
            order_item.setFertilizer(order_item.getFertilizer());
            order_item.setNutrients(order_item.getNutrients());
            order_item.setPesticide(order_item.getPesticide());
            order_item.setPrice(order_item.getPrice());
            order_item.setOrders(member.getOrders());


            Crops_progress crops_progress = new Crops_progress();
            crops_progress.setOrder_item(order_item);
            crops_progress.setCrop_condition("clear");

            //오늘로부터 7일 후 재배 시작
            LocalDateTime startDate = LocalDateTime.now().plusDays(7);
            crops_progress.setCultivation_start(startDate.toString());

            //시작일로부터 예상 경작 소요일 계산
            LocalDateTime tillage_date = startDate.plusDays(Integer.parseInt(cart_item.getCrops_item().getCultivation_period()));
            crops_progress.setExpected_date(tillage_date.toString());

            //주문에 추가된 아이템 가격 더 해주는 과정
            Orders orders =  member.getOrders();
            //orders.addPrice(order_item.getPrice());

            order_item.setOrders(orders);

            //오더아이템 저장 후 카트아이템 삭제
            order_itemService.save(order_item);
            cart_itemService.delete(cart_item);

        }
    }
}
