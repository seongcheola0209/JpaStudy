package 제02일차;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
/*
 - ApplicationRunner는 스프링 부트 애플리케이션(서버)이 완전히 초기화된 후 실행되는 콜백 인터페이스다.
   이를 구현하면 애플리케이션 시작 시점에 필요한 작업을 수행할 수 있다.
 - 즉, SpringBoot 의 ApplicationRunner를 사용하면 애플리케이션이 시작될 때 특정 로직을 실행할 수 있다.
 - ApplicationArguments를 통해 명령줄 인자를 좀 더 편리하게 처리할 수 있다.
 - 특히 @Configuration 클래스 내에서 @Bean으로 등록하면 필요한 빈들을 주입받아 유연하게 사용할 수 있다.
 */


public class JpaRunner implements ApplicationRunner {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("SJKim");
        account.setPassword("pass");

        Study study = new Study();
        study.setName("Spring Data JPA");
        // study.setOwner(account);      //①  주인의 레코드를 가지고 관계를 설정해야 한다. (ONE)

        // @OneToMany 는 join table 로 구성한다.
        // - account.getStudies() 를 호출하면 set 이 반환되고 이 set 의 add 를 사용하여
        //   study 의 foreign key 를 주입한다.
        // - 새로운 account_study 라는 테이블을 생성하여 관계를 설정해준다.
        //   (studies 라는 컬럼이 Study 테이블에 생성되지 않는다 @OneToOne 과의 차이점)
        // - account_study 라는 테이블에는 account_id 와 study_id 를 항목으로 가지고 있다.
        account.getStudies().add(study);  // ③  (MANY)

        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);
    }
}
