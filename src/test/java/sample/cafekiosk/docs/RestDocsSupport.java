package sample.cafekiosk.docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsSupport {
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach //WebApplicationContext webApplicationContext,
    void setUp(RestDocumentationContextProvider provider){
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController()) //테스트 하고자 하는 컨트롤러 삽입
                .apply(documentationConfiguration(provider))
                .build();
    }

    protected abstract Object initController();
}
