package 제05일차;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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

        session.save(post);

        Post post01 = session.get(Post.class, 1L);
        session.delete(post01);

    }
}
