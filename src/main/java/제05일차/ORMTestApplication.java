/*
 1. Fetch 시 연관관계에 있는 엔티티를 어떻게 가져올 것인가?
 - @ManyToOne 의 기본값 Eager
  . 즉 Comment 를 가져오는 시점에 Post 도 같이 가져온다.
 - @OneToMany 의 기본값 Lazy (Lazy 는 실제 사용될 시점에 가져온다)
  . 즉 앞에서 Post 데이터를 가져올 때 Comment 는 가져오지 않는다.
 2. @OneToMany 또는 @ManyToOne 의 설정에 "fetch=fetch = FetchType.Eager
   또는 Lazy 의 지정이 가능하다.
*/

package 제05일차;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ORMTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ORMTestApplication.class);
    }
}
