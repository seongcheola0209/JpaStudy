package 제03일차;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;

    // - mappedBy 를 지정하지 않고 양쪽에서 @OneToMany 와 @ManyToOne 을 지정하면
    //   2개의 단방향 관계가 생성된다.
    // - mappedBy 를 지정하면 지정된 상대편이 주인이 되서 양방향이 설정된다.
    //   즉 study 가 주인이 된다. 
    @OneToMany (mappedBy = "owner")  // ①
    private Set<Study> studies = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    private String yes;

    @Embedded
    private Address address;

    @Transient
    private String no;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String address) {
        this.password = address;
    }

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }
}
