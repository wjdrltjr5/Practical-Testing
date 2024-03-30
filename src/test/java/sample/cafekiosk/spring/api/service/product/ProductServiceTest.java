package sample.cafekiosk.spring.api.service.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.product.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품의 +1")
    @Test
    void createProduct(){
        //given
        Product product1 = createProduct("001",HANDMADE,SELLING,"아메리카노",4000);
        productRepository.save(product1);

        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .price(5000)
                .name("카푸치노")
                .build();
        //when

        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        //then

        assertThat(productResponse).extracting("productNumber", "type", "sellingStatus", "price","name")
                .contains("002",HANDMADE,SELLING,5000,"카푸치노");

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
                .extracting("productNumber", "type", "sellingStatus", "price","name")
                .containsExactlyInAnyOrder(
                        tuple("001",HANDMADE,SELLING,4000,"아메리카노"),
                        tuple("002",HANDMADE,SELLING,5000,"카푸치노")
                );
    }

    @DisplayName("신규 상품을 등록한다. 상품이 하나도 없는 경우 상품번호는 001이다.")
    @Test
    void createProductWhenProductsIsEmpty(){
        //given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .price(5000)
                .name("카푸치노")
                .build();
        //when

        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        //then

        assertThat(productResponse).extracting("productNumber", "type", "sellingStatus", "price","name")
                .contains("001",HANDMADE,SELLING,5000,"카푸치노");

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1)
                .extracting("productNumber", "type", "sellingStatus", "price","name")
                .contains(
                        tuple("001",HANDMADE,SELLING,5000,"카푸치노")
                );


    }


    private Product createProduct(String productNumber, ProductType type , ProductSellingStatus status, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(status)
                .name(name)
                .price(price)
                .build();
    }

}