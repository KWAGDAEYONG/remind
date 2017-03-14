package remind.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import remind.Model.Question;
import remind.Model.User;
import remind.Repository.QnaRepository;
import remind.utility.LoginSession;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/question")
public class QnaController {
	
	@Autowired
	QnaRepository qnaRepository;
	
	@GetMapping("/gotoquestion")
	public String gotoquestion(HttpSession session){
		if(LoginSession.isLogin(session)){
			return "/question/question_form";
		}
		return "/users/login_form";
	}
	
	@PostMapping("")
	public String getQ(Question question, Model model, HttpSession session){
		User user = (User)session.getAttribute("loginUser");
		question.setQ_user(user);
		qnaRepository.save(question);
		model.addAttribute("question", question);
		return "redirect:/";
	}
	
}
