package leevgood.weekend_farm.test;

import com.google.gson.Gson;
import leevgood.weekend_farm.controller.CropsStatus;
import leevgood.weekend_farm.controller.sample.OrderController;
import leevgood.weekend_farm.domain.dto.CropsStatusDto;
import leevgood.weekend_farm.domain.dto.ForOrderItemDto;
import leevgood.weekend_farm.domain.entity.CropsProgress;
import leevgood.weekend_farm.domain.entity.Order;
import leevgood.weekend_farm.domain.entity.OrderItem;
import leevgood.weekend_farm.service.CartService;
import leevgood.weekend_farm.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Mock
    private CartService cartService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @DisplayName("구매 확정 기능")
    @Test
    void orderDirect() throws Exception {
        //given
        final ForOrderItemDto forOrderItemDto = new ForOrderItemDto();
        doReturn(12L).when(orderService).makeOrderItem(any(ForOrderItemDto.class));

        //when
        final ResultActions resultActions = mockMvc.perform(
                post("/api/v1/order/directOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(forOrderItemDto))
        );

        //then
        resultActions.andExpect(status().isOk()).andReturn();
    }

    @DisplayName("장바구니 확정 기능")
    @Test
    void confirmPurchase() throws Exception {
        //given
        Order order = new Order();
        doReturn(order).when(cartService).confirmPurchase(any());
        Long cartId = 1L;
        //when
        final ResultActions resultActions = mockMvc.perform(
                post("/api/v1/1L/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(cartId))
        );
        //then
        resultActions.andExpect(status().isOk()).andReturn();
    }

    @DisplayName("주문 상세 조회")
    @Test
    void cropsStatus() throws Exception{
        //given
        Long memberId = 1L;
        List<CropsStatusDto> cropsStatusDtoList = cropsStatusDtos();
        doReturn(cropsStatusDtoList).when(orderService).getCropsStatus(memberId);

        //when
        final ResultActions resultActions = mockMvc.perform(
                post("/api/v1/order/cropsStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(memberId))
        );
        //then
        resultActions.andExpect(status().isOk()).andReturn();
    }

    private List<CropsStatusDto> cropsStatusDtos(){
        List<CropsStatusDto> list = new ArrayList<>();

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem());

        CropsProgress cropsProgress = new CropsProgress();

        CropsStatusDto cropsStatus = new CropsStatusDto(orderItems,cropsProgress);

        list.add(cropsStatus);
        return list;
    }


    private ForOrderItemDto orderItemDto() {
        final ForOrderItemDto forOrderItemDto = new ForOrderItemDto();
        forOrderItemDto.setMemberId((long) 1);

        ArrayList<Long> array = new ArrayList<>();
        array.add(2L);
        array.add(3L);
        array.add(4L);

        forOrderItemDto.setProductList(array);
        return forOrderItemDto;
    }
}