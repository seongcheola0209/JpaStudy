/*
1. ORM 이란
- 애플리케이션의 클래스와 SQL 데이터베이스 테이블 사이의 맵핑 정보를 기술한 메타데이터를 제공해 준다.
  (즉, 별도의 데이터 Structure 선언 없이 클래스를 이용하여 직접 테이블을 핸들링할 수 있도록 해주는 기능이다)
- 자바 애플리케이션의 객체를 SQL 데이터베이스의 테이블에 자동으로 (또 깨끗하게) 영속화 (저장할 수 있도록)해주는 기술이다.

2. ORM 으로 해결 가능한 일
- JAVA 클래스는 여러 데이터 형식을 가질 수 있지만 DBMS 는 대개 단순한 데이터 구조를 가지며 DBMS 종류에 따라
  데이터 유형이 다르다. ORM 을 사용하는 이러한 문제점을 해결해 준다.
- 동일한 코드를 여러번 사용할 필요 없이 정의된 클래스를 모두 공동으로 이용 가능하다.
- Interface 를 구성하고 이를 구현하는 클래스를 구성하여 다양성을 제공한다.
- @Id 를 제공하여 클래스내에 어떤 것이 주키인지를 정의할 수 있다.
- 자바클래스를 이용하여 외래키 등 다양한 관계를 설정하고 관리할 수 있다.
- 객체를 원소로 가지는 클래스가 존재한다면 이 원소를 통해서 다른 객체의 변수를 참조하는 식으로 연속적인 레퍼런스 제공

 3. ORM 을 사용하기 위한 resource/application.properties 에 내용 추가

 4. Docker 에서 postgres DBMS 를 UP 하고서 프로그램 실행

 5. 연관관계 (OneToOne, OneToMany (가장 많이 사용), ManyToOne) (단방향 관계)

 6. 단방향 테스트를 다음 순서대로 진행한다. (ManyToOne)
 - Study → Account         : 즉 ManyToOne   (MANY) 이라는 주석을 가진 부분을 주석처리한다. 즉 (ONE) 부분 사용
  . Study 와 Account 간의 관계 설정 학습 (Study 에 @ManyToOne 을 지정함)
  . JpaRunner 를 작성 ①   (굳이 이 문장을 작성하지 않아도 자동으로 처리된다)
  . Study 테이블의 owner 항목에 값이 추가된 것을 알 수 있다.

 7. Account → Study         : 즉 OneToMany   (MANY) 의 주석을 풀고 (ONE) 을 주석처리
 -  @OneToMany 에서 Account Table (Many 에 해당함)에는 studies 라는 항목이 생기지 않고 별도의 account_studies 라는
    테이블이 생성된다.
 -  반대쪽 즉 Account 에서 Study 로 관계를 설정 (단방향 관계)
 - Account 에 @OneToMany 를 설정 (여러개의 Study 를 가리켜야 하기 때문에 Set 즉 집합을 원소를 가진다) ②
 - JpaRunner 를 수정 ③
 - 두 테이블의 관계를 정의하기 위한 새로운 테이블 account_studies 가 생성된다.
   JpaRunner 에서 Account 테이블의 studies 에 set 집합을 입력했음에도 AccountTable 의 studies 는 생성되지 않는다.
   Study 테이블에서 studies 라는 항목은 생성되지 않ㅇ는다.
 - account_studies 테이블은 자동으로 삭제되지 않기 때문에 불필요하면 반드시 수동으로 삭제 해줘야 한다.

 */

package 제02일차;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ORMTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ORMTestApplication.class);
    }
}
