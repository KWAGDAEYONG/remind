package remind.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import remind.Repository.QnaRepository;


@Controller
public class HomeController {
	
	@Autowired
	private QnaRepository qnaRepository;
	
	@GetMapping("/")
	public String home(Model model){
		model.addAttribute("question", qnaRepository.findAll());
		return "/index";	
	}

	@GetMapping("/yt")
	public String yt(){
		return "/yt_test";
	}

	@GetMapping("/dApp")
	public String dApp(){
		return "/dApp";
	}
}
