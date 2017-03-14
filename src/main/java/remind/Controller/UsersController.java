package remind.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import remind.Model.User;
import remind.Repository.UserRepository;
import remind.utility.LoginSession;

@Controller
@RequestMapping("/users")
public class UsersController {

	private static final Logger log = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/gotojoin")
	public String goJoin() {
		System.out.println("회원가입폼으로");
		return "/users/join_form";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("loginUser");
		return "redirect:/";
	}

	@PostMapping("/join")
	public String join(User user, Model model) {
		System.out.println("회원가입처리");
		System.out.println(user);
		userRepository.save(user);
		model.addAttribute("users", user);
		return "/users/UserList";
	}

	@GetMapping("/{id}/gotoupdate")
	public String gotoupdate(@PathVariable Long id, Model model, HttpSession session) {

		if(!LoginSession.isLogin(session)){
			return "/users/login_form";
		}
		if(!LoginSession.matchLogin(session,userRepository.findOne(id))){
			log.debug("사용자가 틀립니다.");
			return "redirect:/";
		}
		model.addAttribute("user", userRepository.findOne(id));
		return "/users/update_form";
	}

	@GetMapping("")
	public String userList(Model model, HttpSession session) {

		model.addAttribute("users", userRepository.findAll());
		return "/users/UserList";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, User user, HttpSession session) {
		if(!LoginSession.isLogin(session)){
			return "/users/login_form";
		}
		if(!LoginSession.matchLogin(session,userRepository.findOne(id))){
			log.debug("사용자가 틀립니다.");
			return "redirect:/";
		}
		User dbUser = userRepository.findOne(id);
		dbUser.update(user);
		userRepository.save(dbUser);
		return "redirect:/users";
	}

	@GetMapping("/gotologin")
	public String gotologin() {
		log.debug("로그인폼으로");
		return "/users/login_form";
	}

	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		log.debug("로그인처리로직");
		User dbUser = userRepository.findByUserId(userId);
		if (dbUser == null) {
			log.debug("dbUser = null");
			return "/users/login_form";
		}

		if (!dbUser.matchPassword(password)) {
			log.debug("matchPassword false");
			return "/users/login_form";
		}

		session.setAttribute("loginUser", dbUser);
		log.debug("session값:{}",session.getAttribute("loginUser").toString());
		log.debug("로그인 세션 추가");
		return "redirect:/";

	}
}
