package leevgood.weekend_farm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class TestController {

    @GetMapping
    @ApiOperation(value="상세조회", notes = "게시물 번호에 해당하는 상세 정보를 조회 할 수 있습니다.")
    @ApiImplicitParam(name="parameter", value = "parameter_example")
    public List<String> getList(String parameter){
        ArrayList<String> list = new ArrayList<>();

        return list;
    }

}
