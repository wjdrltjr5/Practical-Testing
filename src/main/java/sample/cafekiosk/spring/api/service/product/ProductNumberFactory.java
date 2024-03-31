package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sample.cafekiosk.spring.domain.product.ProductRepository;
//이제 이걸 테스트
@Component
@RequiredArgsConstructor
public class ProductNumberFactory {

    private final ProductRepository productRepository;
    public String createNextProductNumber(){
        String latestProductNumber = productRepository.findLatestProduct();
        if(latestProductNumber == null){
            return "001";
        }
        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d",nextProductNumberInt);
    }
}
