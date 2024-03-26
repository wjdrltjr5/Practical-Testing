package sample.cafekiosk.spring.api.controller.Order.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Getter
@NoArgsConstructor
public class OrderCreateRequest {
    public OrderCreateRequest(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }

    private List<String> productNumbers;
}
