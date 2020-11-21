package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    //mappedBy - 무엇과 연결되어있는지 나타내는 기능
    //일대다 매핑에서 Member 변수의 team 과 연결되어있다는 것을 나타냄
    //나는 team 으로 매핑이 되어있다.
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList();

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

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
}
