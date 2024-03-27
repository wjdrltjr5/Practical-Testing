package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTypeTest {

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType(){
        //given
        ProductType givenType = ProductType.HANDMADE;
        ProductType bakery = ProductType.BAKERY;
        //when
        boolean b = ProductType.containsStockType(givenType);
        boolean c = ProductType.containsStockType(bakery);
        //then
        assertThat(b).isFalse();
        assertThat(c).isTrue();
    }
}