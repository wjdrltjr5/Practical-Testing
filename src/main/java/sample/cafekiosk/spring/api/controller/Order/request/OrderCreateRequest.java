package sample.cafekiosk.spring.api.controller.Order.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.api.service.order.request.OrderCreateServiceRequest;

import java.util.List;
@Builder
@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    public OrderCreateRequest(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }

    @NotEmpty(message = "상품 번호 리스트는 필수입니다.")
    private List<String> productNumbers;

    public OrderCreateServiceRequest toServiceRequest() {

        return OrderCreateServiceRequest.builder()
                .productNumbers(productNumbers)
                .build();
    }
}
