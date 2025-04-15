package 제02일차;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Study {

    @Id @GeneratedValue
    private Long id;
    private String name;

    // - Account 와의 관계 설정
    // - 아래와 같이 설정하면 owner 에는 Account 의 Primary Key를 참조하는 Foreign key를 생성해서 이를 저장함
    // - 아래의 컬럼명을 owner 로 지정하였지만 owner_id 라는 컬럼이 생성된다.
    // - 애노테이션을 가지고 있는쪽 즉 FORIEGN KEY 를 가지고 있는 쪽이 주인이 된다.
    //   따라서 Study 가 주인이 된다.

    // @ManyToOne                // 여러개의 Study를 하나의 Account 가 생성한다고 가정 (ONE)
    // private Account owner;    // study 를 맡은 사람을 의미함 (ONE)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Account getOwner() {       // (ONE)
//      return owner;
//    }
//
//    public void setOwner(Account owner) { // (ONE)
//        this.owner = owner;
//    }
}
