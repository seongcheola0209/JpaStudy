package 제02일차;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// - @Entity 를 사용해서 해당 클래스와 테이블을 연결한다.
// - 아래의 name 속성은 @Entity 의 이름을 지정한다. 이름을 지정하면 레코드명 대신
//   여기에 지정한 이름을 사용하여 테이블을 핸들링(select * from "지정한 이름"."컬럼명") 한다.
// - @Table(name = "..") 을 이용하여 entity 명과 테이블명이 다를 경우 이를 매핑해준다.
//   Entity 명과 table 명이 동일할 경우 굳이 @Table 을 지정할 필요가 없다.
// - getter/setter 는 없어도 컬럼과 테이블이 맵핑된다. 즉, 반드시 필요한 것은 아니다.
//   아래에 특정 컬럼에 대해서는 getter/setter 가 존재하지 않는데도 프로그램은 정상적으로 동작한다.
// - Entity type 와 Value type
//  . Entity 타입은 아래의 id 가 이에 해당한다.
//  . Value 타입은 Entity 타입을 제외한 나머지 변수를 얘기한다.
//  . Composit Value 타입은 일반적인 Value 타입보다는 규모가 큰 Value 타입을 의미한다.
//    (예로 주소는 우편번호, 광역주소 등 여러 항목이 모여서 만들어질 수 있다. 아래의 address 컬럼 및 Address.java 참조)

@Entity
public class Account {
    @Id                // primary 키를 지정한다. (기본타입과 wrapper 타입 모두 지원한다)
    @GeneratedValue    // 내부에서 자동으로 생성해 준다. (DB에 따라 디폴트 생성전략이 다르다)
    private int id;

    // - @Column 이 생략되었다고 생각하면 된다.
    // - 모든 멤버변수에는 @Column 이 정의되어 있다. 
    private String username;
    private String password;


    // ② @OneToMany 설정
    // - 여러개의 study 에 대한 foreign key 를 생성해야 하기 때문에 집합으로 정의해야 함
    @OneToMany                                          // (MANY)
    private Set<Study> studies = new HashSet<>();       // (MANY)

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    private String yes;

    // Address 의 3 컬림이 address Account 테이블에 생성된다.
    // 하지만 지정한 순서대로 컬럼이 생성되지 않는다. 즉 적당한 곳에 컬럼들이 생성된다.
    // 아울러 @Embedded 를 붙일 때와 붙이지 않았을 때 차이가 없다.
    @Embedded
    private Address address;

    // street 을 home_street 으로 매핑한다고 지정
    // 즉, street 이라는 컬럼은 home_street 으로 변경된다.
    // @Embedded
    // @AttributeOverrides({
    //         @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    // })
    // private Address homeaddress;

    @Transient       // 해당컬럼은 DB컬럼과 매핑되지 않는다. 따라서 DBMS 에 해당 컬럼은 생성되지 않는다.
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

    public Set<Study> getStudies() {        // (MANY)
         return studies;
    }

    public void setStudies(Set<Study> studies) {    // (MANY)
        this.studies = studies;
    }
}
