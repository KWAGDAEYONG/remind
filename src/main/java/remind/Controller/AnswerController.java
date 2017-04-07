package remind.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import remind.Model.Answer;
import remind.Model.Question;
import remind.Model.User;
import remind.Repository.AnswerRepository;
import remind.Repository.QnaRepository;

import javax.servlet.http.HttpSession;

/**
 * Created by user on 2017-04-07.
 */
@Controller
@RequestMapping("/answer")
public class AnswerController {
    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    QnaRepository qnaRepository;

    @Autowired
    AnswerRepository answerRepository;

    @PostMapping("/register/{q_id}")
    public String registerAnswer(@PathVariable Long q_id, Answer answer , Model model, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        answer.setWriter(user);
        Question question = qnaRepository.findOne(q_id);
        answer.setQuestion(question);
        answerRepository.save(answer);
        model.addAttribute("question", question);
        return "/question/question_detail";
    }

    @GetMapping("/form/{q_id}")
    public String gotoAnswerForm(@PathVariable Long q_id ,Model model){
        model.addAttribute("q_id",q_id);
        return "/answer/answer_form";
    }
}
