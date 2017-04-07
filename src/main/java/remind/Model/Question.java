package remind.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private Long q_id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="content", nullable=false)
	private String content;

	@OneToMany(mappedBy="question")
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
