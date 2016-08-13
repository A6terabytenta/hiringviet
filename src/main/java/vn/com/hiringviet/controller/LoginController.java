package vn.com.hiringviet.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.com.hiringviet.dto.SecurityAccount;
import vn.com.hiringviet.model.Account;
import vn.com.hiringviet.model.Company;
import vn.com.hiringviet.model.Member;
import vn.com.hiringviet.service.AccountService;
import vn.com.hiringviet.service.CompanyService;
import vn.com.hiringviet.service.MemberService;

@Controller
public class LoginController {
	private static final Logger LOGGER = Logger
			.getLogger(LoginController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	AuthenticationManager authenticationManager;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	@RequestMapping("/access-denied")
	public String accessDenied(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			SecurityAccount securityAccount = (SecurityAccount) auth
					.getPrincipal();
			model.addAttribute("account", securityAccount);
		}
		return "access_denied";
	}

	public static Account getAccountSession(HttpSession session) {
		return (Account) session.getAttribute("account");
	}

	public static Member getMemberSession(HttpSession session) {
		return (Member) session.getAttribute("memberSession");
	}

	public static Company getCompanySession(HttpSession session) {
		return (Company) session.getAttribute("companySession");
	}

	@RequestMapping(value = "/action/login", method = RequestMethod.POST)
	@ResponseBody
	public LoginStatus loginAjax(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) {
		LOGGER.info("email: " + email + " password: " + password);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				email, password);
		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			return new LoginStatus(true, "you signed in successfully");
		} catch (BadCredentialsException e) {
			return new LoginStatus(false, "Email or password is not correct");
		}
	}

	class LoginStatus {
		private boolean isSuccess;
		private String message;

		public LoginStatus(boolean isSuccess, String message) {
			super();
			this.setSuccess(isSuccess);
			this.setMessage(message);
		}

		public boolean isSuccess() {
			return isSuccess;
		}

		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}
