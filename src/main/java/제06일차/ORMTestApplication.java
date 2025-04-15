/*

 JPA 에서 Query 사용하기
 지금까지는 JPA SQL Query 가 아닌 API를 사용하여 DML 작업을 하였다.
 이번에는 API 가 아닌 JPA 에서 쿼리를 사용하는 방법을 배운다.

 1. JPQL (HQL)
 - Java Persistence Query Language / Hibernate Query Language
 - 데이터베이스 테이블이 아닌, 엔티티 객체 모델 기반으로 쿼리 작성
 - JPA 또는 하이버네이트가 해당 쿼리를 SQL로 변환해서 실행함

 2. Native Query
  ......
  ........
  .......



*/

package 제06일차;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ORMTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ORMTestApplication.class);
    }
}
