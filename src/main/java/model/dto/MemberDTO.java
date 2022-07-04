package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entity.Member;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberDTO {

	private String id;
	private String name;
	private String password;


	public Member toEntity() {
		return Member.builder().id(id).name(name).password(password).build(); //빌더를 이용해 Entity생성
	}
}
