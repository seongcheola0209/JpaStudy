/*

 1. 앞에서는 단향방 관계를 설정하였다. 이를 양방향 관계로 변경한다.
    (양쪽의 테이블에서 각각 @OneToMany 와 @ManyToOne 지정)
 - 관계설정이 변경되기 때문에 account_studies 를 지우고 지정해야 한다.
 - 관계를 설정하기 위해서는 Account 의  ① 와 같이 mappedBy 를 지정해줘야 한다.
   즉, One-To-Many 에서 @OneToMany 측에 지정한다.
   (mappedBy 로 지정된 상대편이 주인이 된다)
   mappedBy 에는 매핑되는 상대편의 컬럼을 지정해 줘야 한다. 여기서는 Study 의 owner 항목과 매칭함
   Study 의 owner 항목을 정의하고 @ManyToOne 을 지정해줌
 - 이렇게 되면 양방향 관계의 주인이 Study 가 된다. 아울러 양쪽에 서로의 키를 입력하는 작업을 해야 한다.
   만약 만약 ③ 이 없으면  account_studies 이 생성되지 않는다.
   아울러 Study 의 owner_id 에 아무 값도 저장되지 않는 것도 확인할 수 있다.

 2. 1. 의 오류를 수정하기 위해 주인을 지정하기 위한 아래의 작업을 실행한다.
  - ② 는 관계의 주인이 Account 인 것으로 처리되었다.
  - JpaRunner 의 ② 와 더불어 ③ 과 같이 추가해 줘야 한다.
    (즉, 양방향에서 서로 참조할 수 있도록 해줘야 한다)
  - 정상적으로 동작하는 것을 확인할 수 있다.
  - 하지만 account_studies 테이블은 생성되지 않는다.

 */

package 제03일차;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ORMTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ORMTestApplication.class);
    }
}
