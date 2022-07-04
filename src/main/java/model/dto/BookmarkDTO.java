package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entity.Bookmark;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookmarkDTO {

	private int bookmarkId;
	private String coinId;
	private String memberId;

	public Bookmark toEntity() {
		return Bookmark.builder().bookmarkId(bookmarkId).coinId(coinId).memberId(memberId).build(); //빌더를 이용해 Entity생성
	}
}
