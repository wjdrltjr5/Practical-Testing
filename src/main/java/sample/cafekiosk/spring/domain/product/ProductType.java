package sample.cafekiosk.spring.domain.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductType {
    HANDMADE("제조음료"),
    BOTTLE("병음료"),
    BAKERY("베이커리");

    private final String text;

}
