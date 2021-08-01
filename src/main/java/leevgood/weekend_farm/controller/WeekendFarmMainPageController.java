package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.service.Crops_itemService;
import leevgood.weekend_farm.domain.entity.CropsItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "메인페이지 관련 API")
public class WeekendFarmMainPageController {
    private final Crops_itemService cropsItemService;

    //메인 페이지에 출력될 작물들 리스트
    @GetMapping("/")
    @ApiOperation(value="모든 작물 리스트", notes = "모든 작물들의 정보를 담은 객체의 리스트를 넘겨받습니다.")
    public List<CropsItem> cropsList(){
        return cropsItemService.findAll();
    }
/*

    //로그인 중인 사용자 정보
    @GetMapping("/")
    public Member memberInformation(){
        return null;
    }
*/


}
