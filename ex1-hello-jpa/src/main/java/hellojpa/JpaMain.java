package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//            List<Member> result = em.createQuery("select m From Member m where m.username like '%kim%'", Member.class).getResultList();
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            //flush -> commit, query 수행될때 동작함.

            

            String sql = "SELECT MEMBER_ID, city, street, zipcode FROM MEMBER ";
            List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();

            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2 : " + (m1 instanceof Member));
        System.out.println("m1 == m2 : " + (m2 instanceof Member));
    }

}
