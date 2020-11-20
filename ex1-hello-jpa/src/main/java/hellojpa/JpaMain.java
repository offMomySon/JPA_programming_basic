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
//#6 기본키 맵핑
// 직접할당은 @id '만' 를 이용하면 된다.
// DB 에 위임하기 위해서는 @GeneratedValue 설정하면 된다.
// Identity - DB 에게 id 설정 위임
// SEQUENCE - DB 의 sequence object 를 가져와서 값을 셋팅함
//
// @SequenceGenerator 의 allocationSize 를 50 으로 셋팅하면,
// 미리 DB 의 sequence object 에서 50 을 증가시키고 web app 의 메모리에서 해당 크기만큼 미리할당하여
// DB 에게 다음 key 값을 요청하지 않아도, Entity 의 key 할당이 가능하다.
//
            Member mem1 = new Member();
            mem1.setUsername("A");
            Member mem2 = new Member();
            mem2.setUsername("B");
            Member mem3 = new Member();
            mem3.setUsername("C");

            System.out.println("==========");

            em.persist(mem1);
            em.persist(mem2);
            em.persist(mem3);

            System.out.println("mem1.getId() = " + mem1.getId());
            System.out.println("mem2.getId() = " + mem2.getId());
            System.out.println("mem3.getId() = " + mem3.getId());

            System.out.println("==========");
//
//
//            Member member = new Member();
//            member.setId("ID_A");
//            member.setUsername("C");
//
//            em.persist(member);
//영속성 컨택스트의 이점
// 1차 캐시
// 동일성 보장 - repeatable read
// 트랜잭션을 지원하는 쓰기 지연
// 변경감지
// 지연 로딩

//#5 flush 란 영속성 context 의 변경사항을 query 를 통해 DB 에 반영하는것.
// 영속성 context 를 flush 하는 방법
            // 순서
            // 1. persist context 의 변경내용감지
            // 2. 수정된 entity 의 변경내용을 지연 sql 저장소에 등록
            // 3. 지원 sql 저장소의 query 를 DB 에 전달

// 1. em.flush - 직접호출
// 2. 트랜잭션 커밋 - 자동호출
// 3. JPQL 쿼리 실행 - 플러시 자동 호출
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//
//            em.flush();
//
//            System.out.println("===========");


//#4 영속과 비영속에 대해
            //비영속
//            Member member = new Member();
//            member.setId(10L);
//            member.setName("HelloJAVA");
//
//            //영속
//            //현재 DB 에 저장되지않고 영속성 컨텍스트에만 저장된다.
//            //트랜잭션을 commit 하는 순간 영속성 context 에 있는 것이 DB에 query 로 날라간다.
//            em.persist(member);

//#3 여러개의 값을 가져오고자 할때
//JPQL 은 객체지향 query 이다. table 대신 객체를 이용해서 query 를 작성한다.
//JPQL 을 이용해서 여러개의 Member 를 가져온다.
// limit, index 또할 설정할 수 있다.
//            List<Member> members = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//            for (Member member : members) {
//                System.out.println("memeber.name = " + member.getName());
//            }
            
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
