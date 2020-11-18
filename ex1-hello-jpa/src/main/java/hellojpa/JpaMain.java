package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

//엔티티매니저는 쓰레드간의 공유가되면 안된다. (사용하고 버려야함)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

//JPA 는 SQL 을 추상화한 JPQL 이라는 객체지향 쿼리 언어 제공
//JPQL 은 엔티티 객체를 대상으로 쿼리
//SQL 은 데이터베이스 테이블을 대상으로 쿼리

        try {
//#3 여러개의 값을 가져오고자 할때
//JPQL 은 객체지향 query 이다. table 대신 객체를 이용해서 query 를 작성한다.
//JPQL 을 이용해서 여러개의 Member 를 가져온다.
// limit, index 또할 설정할 수 있다.
            List<Member> members = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for (Member member : members) {
                System.out.println("memeber.name = " + member.getName());
            }
            
//#2 UPDATE 예제
//값을 업데이트하고 추가로 넣어주지 않아도 된다.
//JPA 가 collection 을 다루듯이 다룰수있도록 설계된 프레임워크이기 때문에
//            Member findMember = em.find(Member.class,1L);
//            findMember.setName("HelloJPA");
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

//#1 INSERT 예제
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
