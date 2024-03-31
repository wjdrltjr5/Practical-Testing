package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductNumberFactory productNumberFactory;
    //동시성이슈가 발생할 수 있다.
    public List<ProductResponse> getSellingProducts(){
        List<Product> products = productRepository.findAllBySellingStatusIn(forDisplay());
        return products.stream().map(ProductResponse::of).collect(Collectors.toList());
    }

    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        // productNumber
        // 001,002,003,004
        // DB에서 마지막 저장된 Product의 상품 번호를 읽어와서 +1

        String nextProductNumber = productNumberFactory.createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(savedProduct.getId())
                .productNumber(nextProductNumber)
                .type(request.getType())
                .sellingStatus(request.getSellingStatus())
                .name(request.getName())
                .price(request.getPrice())
                .build();
    }


}
