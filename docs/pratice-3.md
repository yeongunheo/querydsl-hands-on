여기서 소개하는 기능은 제약이 커서 복잡한 실무 환경에서 사용하기에는 많이 부족하다.  
그래도 스프링 데이터에서 제공하는 기능이므로 간단히 소개하고, 왜 부족한지 설명하겠다.

# 인터페이스 지원 - QuerydslPredicateExecuter

### 한계점

- 조인X (묵시적 조인은 가능하지만 left join이 불가능하다.)
- 클라이언트가 Querydsl에 의존해야 한다. 서비스 클래스가 Querydsl이라는 구현 기술에 의존해야 한다.
- 복잡한 실무환경에서 사용하기에는 한계가 명확하다.

> 참고 : QuerydslPredicateExecutor는 Pageable, Sort를 모두 지원하고 정상 동작한다.

# Querydsl Web 지원

### 한계점

- 단순한 조건만 가능
- 조건을 커스텀하는 기능이 복잡하고 명시적이지 않음
- 컨트롤러가 Querydsl에 의존
- 복잡한 실무 환경에서 사용하기에는 한계가 명확

# 리포지토리 지원 - QuerydslRepositorySupport

### 장점

- `getQuerydsl().applyPagination` 스프링 데이터가 제공하는 페이징을 Querydsl로 편리하게 변환 가능(단! Sort는 오류 발생)
  - offset, limit를 메서드 체인에서 뺄 수 있다!
- `from()` 으로 시작 가능(최근에는 QueryFactory를 사용해서 `select()`로 시작하는 것이 더 명시적)
- EntityManager 제공

### 한계점

- Querydsl 3.x 버전을 대상으로 만듦
- Querydsl 4.x에 나온 JPAQueryFactory로 시작할 수 없음
  - select로 시작할 수 없음 (from으로 시작해야함)
- `QueryFactory`를 제공하지 않음
- 스프링 데이터 Sort 기능이 정상 동작하지 않음

# Querydsl 지원 클래스 직접 만들기

스프링 데이터가 제공하는 QuerydslRepositorySupport가 지닌 한계를 극복하기 위해 지닌 Querydsl 지원 클래스를 만들어보자.

### 장점

- 스프링 데이터가 제공하는 페이징을 편리하게 변환
- 페이징과 카운트 쿼리 분리 가능
- 스프링 데이터 Sort 지원
- `select()`, `selectFrom()`으로 시작 가능
- `EntityManager`, `QueryFactory` 제공