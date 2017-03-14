package remind.utility;

import remind.Model.User;

import javax.servlet.http.HttpSession;

public class LoginSession {
	 public static final String SESSION_USER_KEY="loginUser";
	 
	 public static Boolean isLogin(HttpSession session){
		 Object logined = session.getAttribute(SESSION_USER_KEY);
		 if(logined==null){
			 return false;
		 }
		 return true;
	 }
	 public static Boolean matchLogin(HttpSession session, User loginUser){
	 	User sessionUser = (User)session.getAttribute(SESSION_USER_KEY);
	 	if(sessionUser.getUserId().equals(loginUser.getUserId())){

	 		return true;
		}
		return false;
	 }
}
