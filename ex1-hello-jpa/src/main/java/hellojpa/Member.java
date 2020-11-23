package hellojpa;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(name = "member_seq_generator",
        sequenceName = "member_seq",
        initialValue = 1, allocationSize = 50)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name="TEAM_ID" , insertable = false, updatable = false)
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private  Locker locker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
