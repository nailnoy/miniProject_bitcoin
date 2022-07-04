package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import model.dto.BookmarkDTO;
import model.entity.Bookmark;
import model.util.PublicCommon;


public class BookmarkDAO {

	private static BookmarkDAO instance = new BookmarkDAO();

	private BookmarkDAO() {
	}

	public static BookmarkDAO getInstance() {
		return instance;
	}

	// 북마크 저장
	public boolean addBookmark(BookmarkDTO Bookmark) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;

		try {
			manager.persist(Bookmark);
			manager.getTransaction().commit();

			result = true;

		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}



	// 삭제
	public boolean deleteBookmark(int BookmarkId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		boolean result = false;

		try {
			manager.remove(manager.find(Bookmark.class, BookmarkId));

			manager.getTransaction().commit();

			result = true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return result;
	}

	// id로 해당 북마크 검색
	public BookmarkDTO getBookmark(int BookmarkId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		manager.getTransaction().begin();
		BookmarkDTO Bookmark = null;

		try {
			Bookmark p = manager.find(Bookmark.class, BookmarkId);
			Bookmark = new BookmarkDTO(p.getBookmarkId(), p.getCoinId(), p.getMemberId());
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return Bookmark;
	}

	// 회원의 모든북마크 검색
	public ArrayList<BookmarkDTO> getAllBookmarks(String memberId) throws SQLException {
		EntityManager manager = PublicCommon.getEntityManager();
		ArrayList<BookmarkDTO> alist = new ArrayList<>();
		List list = null;
		try {
			list = manager.createQuery("select b from Bookmark b where memberId=:memberId",	Bookmark.class).setParameter("memberId", memberId).getResultList();	
			Iterator it = list.iterator(); //오브젝트를 뽑아내기 좋게
			Object[] obj = null;
			while(it.hasNext()) {
				obj = (Object[]) it.next();
				alist.add(new BookmarkDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]), String.valueOf(obj[2])));
			}
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return alist;
	}
}
