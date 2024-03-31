## [Practical Testing](https://www.inflearn.com/course/practical-testing-%EC%8B%A4%EC%9A%A9%EC%A0%81%EC%9D%B8-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EA%B0%80%EC%9D%B4%EB%93%9C/dashboard)

### 단위 테스트 #1, #2
- 단위테스트
- 수동테스트, 자동화 테스트
- Junit5,AssertJ
- 해피케이스, 예외케이스
- 경계값 테스트
- 테스트하기 쉬운/어려운 영역(순수함수)
-----
- lombok(사용가이드) @Data, @Setter, @AllArgsConstructor지양 JPA 양방향 연관관계 시 @ToString 순환참조 문제 알아보기

### TDD  #3
- TDD
- 레드-그린-리팩토링
---
- 애자일 vs 폭포수
- 익스트림 프로그래밍
- 스크럼,칸반

### 테스트는 []다 #4
- @DisplayName - 도메인 정책, 용어를 사용한 명확한 문장
- Given/When/Then - 주어진 환경,행동,상태 변화
- TDD vs BDD
---
- Junit vs Spock(BDD 프레임워크)

### 스프링 & JPA 기반 테스트 #5 #6 #7 #8 #9
- Layered Architecture
- 단위 테스트 vs 통합 테스트
- Ioc, DI, AOP
- ORM, 패러다임의 불일치, Hibernate
- String Data JPA
- @SpringBootTest vs @DataJpaTest
- @SpringBootTest vs @WebMvcTest
- @Transactional(readOnly = true)
- @RestController, @ExceptionHandle
- Spring bean validation notnull, not empty, not blank
- @WebMvcTest
- ObjectMapper
- Mock, Mockito, @MockBean
- 
---
- Hexagonal Architecture
- Query DSL : 타입체크, 동적처리
- Optimisic Lock, Pessimisitc Lock
- CQRS

### Mock을 마주하는 자세 #10
- Test Double, Stubbing : dummy, fake, stub, spy, mock
- @Mock, @MockBean, @Spy,@SpyBean,@InjectMocks bean들은 bean등록이 필요할때사용
- BDDMockito
- Classist vs Mockist

### 더 나은 테스트를 작성하기 위한 조언 #11 #12 #13
- 한 문단에 한 주제 : 한가지 테스트에서는 한가지 목적만 검증
- 완벅하게 제어하기 : ex. 시간과 관련
- 테스트 환경의 독립성을 보장하자 : 실패하는 부분은 when then / 생성자(빌더) 기반으로 환경 구성
  (프로덕션 환경에서는 팩토리 메서드 o 뭐 추가 정책./ 테스트 환경에서는 그냥 생성자(빌더))
- 테스트 간 독립성을 보장하자
- 한 눈에 들어오는 Test Fixture 구성하기
- Test Fixture 클렌징
- @ParameterizedTest 
- @DynamicTest
- 테스트 수행도 비용이다 환경통합하기
- private 메서드의 테스트 : 이런 느낌이 든다면 분리하고 public으로 만들어야 할 수도
- 테스트에서만 필요한 메서드가 있다면 : 결론은 상관없다. 하지만 보수적으로