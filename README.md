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

### 스프링 & JPA 기반 테스트
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