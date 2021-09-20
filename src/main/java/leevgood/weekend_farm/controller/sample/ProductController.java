package leevgood.weekend_farm.controller.sample;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import leevgood.weekend_farm.config.Message;
import leevgood.weekend_farm.domain.dto.CropsListDto;
import leevgood.weekend_farm.domain.dto.ProductsForCropDto;
import leevgood.weekend_farm.service.AreaService;
import leevgood.weekend_farm.service.CropsOptionService;
import leevgood.weekend_farm.service.CropsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = "제품 관련 API")
public class ProductController {
    private final CropsService cropsService;
    private final AreaService areaService;
    private final CropsOptionService cropsOptionService;

    // 작물 상품 전체 조회
    @GetMapping("v1/crops")
    @ApiOperation(value="모든 작물 리스트", notes = "모든 작물들의 정보를 담은 객체의 리스트를 넘겨받습니다.")
    public ResponseEntity<Message> cropsList(){

        CropsListDto cropsListDto = new CropsListDto();
        cropsListDto.setCropsList(cropsService.getAllCrops());


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(cropsListDto),headers, HttpStatus.OK);
    }

    // 작물 상품 상세 조회
    @GetMapping("/v1/selectCrops/{id}")
    @ApiOperation(value="작물과 옵션 정보", notes = "작물의 정보와 작물에 대한 옵션 정보를 담은 객체를 넘겨받습니다.")
    public ResponseEntity<Message> selectCrops(@PathVariable("id") Long cropsId) {

        ProductsForCropDto productsForCropDto = new ProductsForCropDto(cropsService.findById(cropsId),
                areaService.getAllArea(),cropsOptionService.getAllCropsOption());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(Message.okMessage(productsForCropDto),headers, HttpStatus.OK);
    }

}
