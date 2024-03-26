package sample.cafekiosk.spring.api.controller.Order.request;

import lombok.Builder;

import java.util.List;
@Builder
public class OrderCreateRequest {
    public OrderCreateRequest(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }

    private List<String> productNumbers;
}
