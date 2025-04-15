package 제04일차;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
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

        Post post = new Post();
        post.setTitle("KSC POST");

        Comment comment01 = new Comment();
        comment01.setComment("비방글을 자제해야 함");
        post.addComment(comment01);

        Comment comment02 = new Comment();
        comment02.setComment("비방글을 삭제할 예정임");
        post.addComment(comment02);

        Session session = entityManager.unwrap(Session.class);

        // post 만을 저장할 경우
        // 관계테이블도 comment 테이블도 생성되지 않고 post 만 저장된다. ①
        // 03일차 @OneToMany 의 경우에는 session.save(comments)도 호출했어야 했다.
        // 둘을 비교해 보기 바람
        session.save(post);

        // - post 테이블의 테이터를 삭제하면 comment 테이블의 데이터도 같이 삭제된다.
        // - 즉, post 테이블이 부모이기 때문에 부모가 삭제되면 부모와 같은 키를 가지는 자식도
        //   같이 삭제되는 것이다.
        Post post01 = session.get(Post.class, 1L);
        session.delete(post01);

    }
}
