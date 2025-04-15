package 제06일차;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {
    @Id @GeneratedValue
    private Long id;
    private String title;

    @OneToMany(mappedBy = "post",  cascade = CascadeType.ALL)
    private Set<Comment> commments = new HashSet<>();;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Comment> getCommments() {
        return commments;
    }

    public void setCommments(Set<Comment> commments) {
        this.commments = commments;
    }

    public void addComment(Comment comment) {
        this.getCommments().add(comment);
        comment.setPost(this);
    }
}
