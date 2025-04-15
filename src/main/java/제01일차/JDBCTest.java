/*
1. IntelliJ Community 버전에서 Spring 프로젝트 생성하는 방법

방법1)
- Web Spring Initializr : https://start.spring.io/
  (아래의 생성옵션을 사용하여 프로젝트생성)
. Gradle-Groovy(또는 Gradle-Kotlin)
. Spring Boot : 3.1.4 (혹은 이후 버전)
. Project Metadata
Group  : HelloSpring
Artifact : SpringStudy
Packing  : jar
java     : 17 버전 이상의 버전 선택
- 이를 통해 생성된 파일의 압축을 풀고 이를 IntelliJ 가 읽어 들인다.

방법2)
- Community 버전에서도 자동 생성 가능
. plugins → Marketplace에 들어가 Spring Initializr 검색하고 설치하고 재 실행
- 오른쪽 하단에 Spring 아이콘이 생기면 이 아이콘을 클릭하고 새로운 프로젝트 생성

2. 새로운 모듈(JAVA 등)을 설치하고자 할 경우 (단축키 : Ctrl + Alt + Shift + S)
※ JAVA 버전 등의 오류가 발생할 경우에도 아래의 내용으로 버전을 조정하여 처리함
- File → New Project Setup → Structure Project → 왼쪽 "Project Settings" 창의 SDKs 선택
  → 선택된 창에서 "+ ADD" 버튼을 클릭 → DownLoad 를 선택 하고 원하는 버전을 선택

3. 만약 외부에서 사용자가 직접 프로젝트를 생성 하였다면 이를 IntelliJ 가 사용할 수 있도록 해야 함
- File → New Project Setup → Structure → 왼쪽 "Project Settings" 창의 SDKs 선택  →
  "+ ADD" 버튼을 클릭 → Add JDK 를 선택하여 설치된 JDK를 선택해야 함

4. Settings (Ctrl + Alt + S)
- Settings 화면에서 → Gradle 입력 → Build Tools 의 Gradle 로 이동 → 맨 아래 부분에 "Grale JVM"
  즉 Gradle 이 사용할 JVM 버전을 알 수 있다. 만약 build.gradle 과 버전이 다른 경우 이것을 맞추어 주어야 한다.
- 해당 화면의 "Build and run using" 및 "Run tests using" 을 "IntelliJ" 로 변경
- 이렇게 하면 Intellij 의 Setting 에 의해서만(바로 위의 setting 화면에서 반영했던 내용들) 영향을 받는다.
  즉 build.gradle 에서는 영향을 받지 않는다.

6. File → Project Structure 의 java 버전과 build.gradle 의 JAVA 버전을 같게 해야 한다.

7. Docker 를 이용하여 Postgrasql 환경 구성
- Intellj 에서 windows 창 띄우기 Alt-F12
> docker run -p 5432:5432 -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=sckim  \
        -e POSTGRES_DB=springdata --name postgres_boot -d postgres
 . --name name           : docker 에 기동되는 container 이름
 . -d 이미지              : docker hub 로 부터 가져올 이미지 이름
 . -POSTGRES_DB=dbma이름  : 기동될 DBMS 이름
 . -p port               : 외부 및 내부 포트 즉 접속 포트
> docker exec -i -t postgres_boot bash
> su - postgres
> psql --username sckim --dbname springdata
> \list                  : 데이터베이스 조회
> \dt                    : 테이블 조회
> SELECT * FROM ACCOUNT; : 특정 테이블로 부터 데이터 조회

8. 아래 코드 즉 JDBC 의 문제점
- DBMS 마다 API 가 다르기 때문에 DBMS가 변경될 때 마다 프로그램을 수정해야 한다.
- 도메인 즉 관심사를 분리하는 코드를 작성하기 힘들다
  (비즈니스 로직에서 DBMS 와 관련된 로직 분리의 어려움)
- 같은 코드가 반복된다. (즉 DBMS connection 등의 API 호출이 반복된다)
*/
package 제01일차;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/springdata";
        String username = "sckim";
        String password = "pass";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection created: " + connection);

//            String sql01 = "CREATE TABLE ACCOUNT (id int, username varchar(255), password varchar(255));";
//            try (PreparedStatement statement = connection.prepareStatement(sql01)) {
//                statement.execute();
//            }

            String sql02 = "INSERT INTO ACCOUNT VALUES(1, 'sckim', 'pass');";
            try (PreparedStatement statement = connection.prepareStatement(sql02)) {
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
