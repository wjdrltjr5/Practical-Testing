package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

//@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest   // Spring Boot Test 보다 가벼움
class ProductRepositoryTest {
   @Autowired
   private ProductRepository productRepository;

    @DisplayName("원하는 판매 상태를 가진 상품들을 조회한다.")
    @Test
    void test(){
        //given
        Product product1 = createProduct("001",HANDMADE,SELLING,"아메리카노",4000);
        Product product2 = createProduct("002",HANDMADE,HOLD,"카페라떼",4500);
        Product product3 = createProduct("003",HANDMADE,STOP_SELLING,"팥빙수",7000);

        productRepository.saveAll(List.of(product1,product2,product3));

        //when

        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        //then
        assertThat(products).hasSize(2)
                .extracting("productNumber" , "name","sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001","아메리카노",SELLING),
                        tuple("002","카페라떼",HOLD)
                );
    }

    @DisplayName("상품번호로 리스트로 상품들을 조회한다.")
    @Test
    void findAllByProductNumberIn(){
        //given
        Product product1 = createProduct("001",HANDMADE,SELLING,"아메리카노",4000);
        Product product2 = createProduct("002",HANDMADE,HOLD,"카페라떼",4500);
        Product product3 = createProduct("003",HANDMADE,STOP_SELLING,"팥빙수",7000);

        productRepository.saveAll(List.of(product1,product2,product3));
        //when
        List<Product> products = productRepository.findAllByProductNumberIn(List.of("001", "002"));
        //then

        assertThat(products).hasSize(2)
                .extracting("productNumber" , "name","sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001","아메리카노",SELLING),
                        tuple("002","카페라떼",HOLD)
                );
    }

    @DisplayName("가장 마지막으로 저장된 상품번호를 가져온다.")
    @Test
    void findLatestProduct(){
        //given
        Product product1 = createProduct("001",HANDMADE,SELLING,"아메리카노",4000);
        Product product2 = createProduct("002",HANDMADE,HOLD,"카페라떼",4500);
        Product product3 = createProduct("003",HANDMADE,STOP_SELLING,"팥빙수",7000);

        productRepository.saveAll(List.of(product1,product2,product3));
        //when
        String latestProductNumber = productRepository.findLatestProduct();
        //then

        assertThat(latestProductNumber).isEqualTo("003");
    }
    @DisplayName("가장 마지막으로 저장된 상품번호를 읽어올때 상품이 하나도 없는 경우 null을 반환한다..")
    @Test
    void findLatestProductNumberWhenProductIsEmpty(){
        //given
        //when
        String latestProductNumber = productRepository.findLatestProduct();
        //then

        assertThat(latestProductNumber).isNull();
    }
    private Product createProduct(String productNumber, ProductType type ,ProductSellingStatus status,String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(status)
                .name(name)
                .price(price)
                .build();
    }
}