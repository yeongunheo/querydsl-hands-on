### 목차

- 순수 JPA 리포지토리와 Querydsl
- 동적쿼리 Builder 적용
- 동적쿼리 Where 적용
- 조회 API 컨트롤러 개발

# 순수 JPA 리포지토리와 Querydsl

# 동적 쿼리와 성능 최적화 조회 - Builder 사용

- 동적쿼리를 짤 때 주의할 점
    - 만약 조건이 하나도 없다면 `findAll()`을 한다.
    - 따라서 동적쿼리를 짤 때는 페이징과 같은 기본조건이 있는게 좋다.

# 동적 쿼리와 성능 최적화 조회 - Where절 파라미터 사용

- where 절을 이용한 동적쿼리에서 return 타입을 Predicate이 아닌 BooleanExpression으로 하면 나중에 조합이 가능해진다.
- where절 내 조건들을 메서드로 분리하면 재사용할 수 있다.

# 조회 API 컨트롤러 개발