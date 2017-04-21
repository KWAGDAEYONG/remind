package remind.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import remind.Model.Answer;
import remind.Model.User;
import remind.Repository.AnswerRepository;
import remind.Repository.QnaRepository;

import javax.servlet.http.HttpSession;

/**
 * Created by user on 2017-04-07.
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {
    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    QnaRepository qnaRepository;

    @Autowired
    AnswerRepository answerRepository;

    @PostMapping("/register/{q_id}")
    public Answer registerAnswer(@PathVariable Long q_id, Answer answer, HttpSession session){
        answer.setWriter((User)session.getAttribute("loginUser"));
        answer.setQuestion(qnaRepository.findOne(q_id));
        return answerRepository.save(answer);
    }

    @GetMapping("/form/{q_id}")
    public String gotoAnswerForm(@PathVariable Long q_id ,Model model){
        model.addAttribute("q_id",q_id);
        return "/answer/answer_form";
    }
}
