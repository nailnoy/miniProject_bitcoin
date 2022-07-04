package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class Bookmark {
	
	@Id
	@Column(name="bookmarkId")
	private int bookmarkId;
	
	private String coinId;
	
	@ManyToOne(targetEntity = Member.class)
	@JoinColumn(name="memberId")
	private String memberId;
	
	
}
