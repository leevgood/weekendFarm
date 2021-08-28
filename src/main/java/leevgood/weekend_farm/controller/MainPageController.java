package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.config.StatusEnum;
import leevgood.weekend_farm.domain.dto.CropsListDto;
import leevgood.weekend_farm.domain.entity.product.Crops;
import leevgood.weekend_farm.service.CropsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "메인페이지 관련 API")
public class MainPageController {
    private final CropsService cropsService;


    //메인 페이지에 출력될 작물들 리스트
    @GetMapping("/")
    @ApiOperation(value="모든 작물 리스트", notes = "모든 작물들의 정보를 담은 객체의 리스트를 넘겨받습니다.")
    public ResponseEntity<Message> cropsList(){

        CropsListDto cropsListDto = new CropsListDto();
        cropsListDto.setCropsList(cropsService.getAllCrops());

        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        //제대로 조회에 성공시, 실패시 다른 메시지와 status 넣어준다.
        if(cropsListDto.getCropsList().isEmpty()){
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("failed");
        }
        else{
            message.setStatus(StatusEnum.OK);
            message.setMessage("success");
            message.setData(cropsListDto);
        }


        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }



}
