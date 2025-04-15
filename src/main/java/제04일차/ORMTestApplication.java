/*
 양방향 관련성을 구성하고 부모 테이블에 cacade 를 설정하면 보모-자식이 성립되는 테이블의 경우
 부모에 해당하는 테이블을 저장하면 자식도 자동으로 저장된다. 단방향과의 차이를 학습한다.

 1. 엔티티 상태 변화를 전파 시키는 옵션
 - 엔티티 상태란
  . Transient   : JPA 가 모르는 상태 (DB에 데이터를 UPDATE 하기 전 상태)
  . Persistent  : JPA 가 관리중인 상태 (1차 캐시, Dirty Checking 등)
                  (.save() 또는 .persist() 를 호출한 상태. 하지만 아직 디스크에 반영되지는 않았다.
                   아직은 캐쉬에 데이터가 존재한다. 이후 일정시점 이후에 디스크에 반영됨)
                  데이터가 캐쉬에 있는 동안에는 업데이트 문장 및 이 데이터를 읽는 쿼리는 모두 캐쉬상에서
                  이루어진다.
  . Datached    : JPA 가 더 이상 관리하지 않는 상태
                  (트랜잭션이 종료되어서 세션 밖으로 나왔을 때)
  . Removed     : JPA 가 관리하긴 하지만 삭제하기로 한 상태

  ※ 상태전이와 관련한 내용은 참고 문서 참조

- 관계매핑 애노테이션에 옵션(cascade)이 존재
 . @OneToMany, @ManyToOne 등 에 cascade 옵션이 존재
 . 즉 한쪽 테이블에 발생한 상태를 다른쪽 테이블에도 반영할지 여부를 결정

- 관계매핑은 @OneToMany, @ManyToOne 관계를 가지면서 Parent-Child 의 관계가 성립할 때 가능하다.
 . Parent-Child 의 관계는 Parent 가 모두 삭제되면 Child 도 삭제되어야 하는 경우임
   (Account-Study 는 이런 관계가 아님 Study 에 Owner 가 반드시 필요한 것은 아님)

2. Parent-Child 구조를 만들기 위해 Post 클래스 생성
- Comment 에는 개별 Comment 를 관리하고 Post 는 이러한 comment 를 Post 형태로 관리한다.
- 즉 모든 Comment 가 삭제되면 Post 도 필요 없어진다.

3. JPARunner 수정
- ① 의 상황을 주의할 것 (아직은 cascade 옵션을 주지 않음)
- 이 상태에서 Post 테이블에서 @OneToMany 에 cascade 옵션을 추가하고 프로그램 실행
- post 만을 저장해도 comment 테이블도 같이 저장됨을 알 수 있다.
  03일차에서는 @OneToMany 일지라도 두개의 테이블에 대해 각각 .save() 를 호출했어야 했다.

*/

package 제04일차;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ORMTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ORMTestApplication.class);
    }
}
