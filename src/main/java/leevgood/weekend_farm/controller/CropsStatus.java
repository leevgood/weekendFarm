package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.config.StatusEnum;
import leevgood.weekend_farm.domain.dto.CropsStatusDto;
import leevgood.weekend_farm.domain.dto.ProductsDto;
import leevgood.weekend_farm.domain.entity.Order;
import leevgood.weekend_farm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
//현재 재배 중인 작물현황 화면에 대한 컨트롤러
@Api(tags = "재배 중인 작물 현황관련 API")
public class CropsStatus {
    private final OrderService orderService;

    //현재 로그인 중인 회원의 재배 중인 작물들의 정보를 넘겨준다... Products에 저장된 데이터들이 해당 -> Crops들의 리스트
    @GetMapping("/cropsStatus")
    @ApiOperation(value="재배중인 작물 정보", notes = "해당 회원의 재배 중인 작물 정보를 담은 객체를 넘겨받습니다..")
    public ResponseEntity<Message> cropsStatus(@RequestParam Long memberId) {

        List<CropsStatusDto> cropsStatusDtoLists = orderService.getCropsStatus(memberId);

        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("success");
        message.setData(cropsStatusDtoLists);

        return new ResponseEntity<>(message,headers, HttpStatus.OK);

    }
}
