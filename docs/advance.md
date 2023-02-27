# 프로젝션과 결과 반환 - 기본

### 프로젝션이란?
- SELECT 절에 어떤 것을 가져올 지 대상을 지정하는 것

## 프로젝션 대상이 하나

- 프로젝션 대상이 하나면 타입을 명확하게 지정할 수 있음
- 프로젝션 대상이 둘 이상이면 튜플이나 DTO로 조회

### Tuple

- tuple은 `com.querydsl.core` 내 클래스이기에 Repository 바깥인 Service나 Controller에 쓰는 것을 피하는게 좋다.

# 프로젝션과 결과 반환 - DTO 조회

### 순수 JPA에서 DTO 조회 코드

- 순수 JPA에서 DTO를 조회할 때는 new 명령어를 사용해야 함
- DTO의 package 이름을 다 적어줘야 해서 지저분함
- 생성자 방식만 지원함

```java
List<MemberDto> result = em.createQuery("SELECT new study.querydsl.dto.MemberDto(m.username, m.age) FROM Member m", MemberDto.class)
                .getResultList();
```

### Querydsl 빈 생성(Bean population)

결과를 DTO 반환할 때 사용

다음 3가지 방법 지원

- 프로퍼티 접근
- 필드 직접 접근
- 생성자 사용

> 세터 접근이나 필드 접근 방식은 필드 이름과 같게 만들어 주는 것이 중요하다.  
그러나 생성자 방식은 필드 이름이 달라도 타입만 맞으면 잘 매핑된다.
>

**프로퍼티, 필드 접근 생성 방식에서 이름이 다를 때 해결 방안**

- `ExpressionsUtils.as(source, alias)` : 필드나 서브 쿼리에 별칭 적용
- `username.as(”memberName”)` : 필드에 별칭 적용

# 프로젝션과 결과 반환 - @QueryProjection

### 생성자 + @QueryProjection

**장점**

- 컴파일 시점에 타입이 안맞으면 오류를 잡아준다.
- 생성자 방식은 컴파일 오류를 잡아주지 못한다. 런타임 시점에 에러가 발생한다.

**단점**

- DTO가 Querydsl에 대한 의존성을 갖게 된다.
- DTO는 Controller, Service, Repository 모든 곳에서 사용할텐데, 그런 DTO가 프로젝트 전반적으로 흘러다니는 것이 부담스럽다.

⇒ 따라서 생성자 방식을 추천한다.

# 동적 쿼리 - BooleanBuilder 사용

### 동적 쿼리를 해결하는 두가지 방식

- BooleanBuilder
- Where 다중 파라미터 사용

# 동적 쿼리 - Where 다중 파라미터 사용

- where 조건에 null 값은 무시된다.
- 메서드를 다른 쿼리에서도 재활용할 수 있다.
- BooleanBuilder 방식보다 쿼리 자체의 가독성이 높아진다.
- 조합이 가능하다.
    - 조합한 메서드는 재사용이 가능해진다.

  > 광고 상태 isValid, 날짜가 IN일 경우:  
  조건들을 조합한 최종메서드: isServicable

# 수정, 삭제 벌크 연산

- 쿼리 한번으로 대량 데이터 수정

**대신, Bulk 연산은 조심해서 사용해야 한다.**

- 쿼리가 그즉시 바로 날라가기 때문에 DB 상태와 영속성 컨텍스트의 상태가 달라진다.

# SQL function 호출하기

SQL function은 JPA와 같이 Dialect에 등록된 내용만 호출할 수 있다.