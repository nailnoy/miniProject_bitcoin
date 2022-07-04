package model;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import model.dto.MemberDTO;
import model.entity.Member;
import model.util.PublicCommon;

public class MemberDAO {

	private static MemberDAO instance = new MemberDAO();

	private MemberDAO() {}

	public static MemberDAO getInstance() {
		return instance;
	}

	public boolean addMember(MemberDTO Member) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;

		try {
			em.persist(Member.toEntity());
			em.getTransaction().commit();

			result = true;

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

	// 수정
//	public boolean updateMember(String MemberId, String major) throws SQLException {
//		EntityManager em = PublicCommon.getEntityManager();
//		em.getTransaction().begin();
//		boolean result = false;
//
//		try {
//			em.find(Member.class, MemberId).setMajor(major);
//
//			em.getTransaction().commit();
//
//			result = true;
//		} catch (Exception e) {
//			em.getTransaction().rollback();
//		} finally {
//			em.close();
//		}
//		return result;
//	}

	// 삭제
	// sql - delete from Member where Member_id=?
	public boolean deleteMember(String memberId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		boolean result = false;

		try {
			em.remove(em.find(Member.class, memberId));

			em.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
		return result;
	}

	// id로 해당 멤의 모든 정보 반환
	public MemberDTO getMember(String MemberId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();
		MemberDTO Member = null;

		try {
			Member a = em.find(Member.class, MemberId);
			Member = new MemberDTO(a.getId(), a.getName(), a.getPassword());
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return Member;
	}

}

