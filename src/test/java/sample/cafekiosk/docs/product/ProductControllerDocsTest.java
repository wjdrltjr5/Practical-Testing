package sample.cafekiosk.docs.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import sample.cafekiosk.docs.RestDocsSupport;
import sample.cafekiosk.spring.api.controller.product.ProductController;
import sample.cafekiosk.spring.api.controller.product.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.ProductService;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.BAKERY;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

public class ProductControllerDocsTest extends RestDocsSupport {

    private final ProductService productService = mock(ProductService.class);
    @Override
    protected Object initController() {
        return new ProductController(productService);
    }
    @DisplayName("신규 상품을 등록한는 API")
    @Test
    void createProduct() throws Exception {
        //given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .price(5000)
                .name("카푸치노")
                .build();

        given(productService.createProduct(any(ProductCreateServiceRequest.class)))
                        .willReturn(ProductResponse.builder()
                                .id(1L)
                                .price(4000)
                                .productNumber("001")
                                .type(BAKERY)
                                .sellingStatus(SELLING)
                                .name("크루아상")
                                .build());

        //when
        mockMvc.perform(
                post("/api/v1/products/new")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
                .andDo(document("product-create",
                    requestFields(
                            fieldWithPath("type").type(JsonFieldType.STRING)
                                .description("상품 타입"),
                            fieldWithPath("sellingStatus").type(JsonFieldType.STRING)
                                    .optional()
                                    .description("상품 판매 상태"),
                            fieldWithPath("name").type(JsonFieldType.STRING)
                                    .description("상품 이름"),
                            fieldWithPath("price").type(JsonFieldType.NUMBER)
                                    .description("상품 가격")
                    ),
                    responseFields(
                            fieldWithPath("code").type(JsonFieldType.NUMBER)
                                    .description("코드"),
                            fieldWithPath("status").type(JsonFieldType.STRING)
                                    .description("상태"),
                            fieldWithPath("message").type(JsonFieldType.STRING)
                                    .description("메시지"),
                            fieldWithPath("data").type(JsonFieldType.OBJECT)
                                    .description("응답데이터"),
                            fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                    .description("상품 ID"),
                            fieldWithPath("data.productNumber").type(JsonFieldType.STRING)
                                    .description("상품 번호"),
                            fieldWithPath("data.type").type(JsonFieldType.STRING)
                                    .description("상품 타입"),
                            fieldWithPath("data.sellingStatus").type(JsonFieldType.STRING)
                                    .description("상품 판매 상태"),
                            fieldWithPath("data.name").type(JsonFieldType.STRING)
                                    .description("상품 이름"),
                            fieldWithPath("data.price").type(JsonFieldType.NUMBER)
                                    .description("상품 가격")
                    )
                ));
    }

}
