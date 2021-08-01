package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.service.Order_itemService;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.domain.entity.Order_item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
//현재 재배 중인 작물현황 화면에 대한 컨트롤러
@Api(tags = "재배 중인 작물 현황관련 API")
public class WeekendFarmCropsStatus {
    private final Order_itemService order_itemService;

    //현재 로그인 중인 회원의 재배 중인 작물들의 정보를 넘겨준다... Orders에 저장된 데이터들이 해당 -> order_item들의 리스트
    @GetMapping("/cropsStatus")
    @ApiOperation(value="재배중인 작물 정보", notes = "해당 회원의 재배 중인 작물 정보를 담은 객체를 넘겨받습니다..")
    public List<Order_item> cropsStatus(@RequestParam Member member)
    {
        return order_itemService.findOrder_itemsFetchJoin(member.getId());
    }



}
