package remind.Model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


/**
 * Created by user on 2017-04-07.
 */
@Entity
public class Answer {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long a_id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    @JsonProperty
    private User writer;

    @Column(name="content", nullable = false)
    @JsonProperty
    private String content;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    @JsonProperty
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

    @Override
    public String toString() {
        return "Answer{" +
                "a_id=" + a_id +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                ", question=" + question +
                '}';
    }
}
