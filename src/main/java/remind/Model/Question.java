package remind.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	@JsonProperty
	private Long q_id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonProperty
	private User writer;
	
	@Column(name="title", nullable=false)
	@JsonProperty
	private String title;
	
	@Column(name="content", nullable=false)
	@JsonProperty
	private String content;

	@OneToMany(mappedBy="question")
	@OrderBy("a_id ASC")
	private List<Answer> answers;

	public void setQ_id(Long q_id) {
		this.q_id = q_id;
	}
	
	public void setQ_user(User q_user) {
		this.writer = q_user;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public User getWriter() {
		return writer;
	}

	public void update(Question question){
		this.content = question.content;
		this.title = question.title;
	}
}
