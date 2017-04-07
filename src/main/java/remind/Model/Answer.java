package remind.Model;


import javax.persistence.*;


/**
 * Created by user on 2017-04-07.
 */
@Entity
public class Answer {

    @Id
    @GeneratedValue
    private Long a_id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @Column(name="content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    public void setA_id(Long a_id) {
        this.a_id = a_id;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
