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

import remind.Model.Question;
import remind.Model.User;
import remind.Repository.QnaRepository;
import remind.utility.LoginSession;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/question")
public class QnaController {

	private static final Logger log = LoggerFactory.getLogger(UsersController.class);

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
	@GetMapping("/{q_id}")
	public String questionDetail(@PathVariable Long q_id, Model model){
		Question question = qnaRepository.findOne(q_id);
		model.addAttribute("question", question);

		return "/question/question_detail";
	}

	@GetMapping("/updateForm/{q_id}")
	public String questionUpdateForm(@PathVariable Long q_id,HttpSession session, Model model){
		Question question = qnaRepository.findOne(q_id);

		if(!LoginSession.isLogin(session)){
			return "/users/login_form";
		}
		if(!LoginSession.matchLogin(session,question.getWriter())){
			log.debug("사용자가 틀립니다.");
			return "redirect:/";
		}
		model.addAttribute("question",question);
		return "/question/question_updateForm";
	}

	@PostMapping("/update/{q_id}")
	public String questionUpdate(Question question, @PathVariable Long q_id, HttpSession session,Model model){
		Question dbQuestion = qnaRepository.findOne(q_id);

		if(!LoginSession.isLogin(session)){
			return "/users/login_form";
		}
		if(!LoginSession.matchLogin(session,dbQuestion.getWriter())){
			log.debug("사용자가 틀립니다.");
			return "redirect:/";
		}
		dbQuestion.update(question);
		qnaRepository.save(dbQuestion);
		model.addAttribute("question",dbQuestion);
		return "/question/question_detail";
	}
	
}
