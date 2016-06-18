package vn.com.hiringviet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.com.hiringviet.constant.ConstantValues;
import vn.com.hiringviet.model.Account;
import vn.com.hiringviet.model.Company;
import vn.com.hiringviet.model.Job;
import vn.com.hiringviet.model.Member;
import vn.com.hiringviet.service.CompanyService;
import vn.com.hiringviet.service.JobService;
import vn.com.hiringviet.service.MemberService;
import vn.com.hiringviet.service.ResumeService;
import vn.com.hiringviet.service.SkillService;
import vn.com.hiringviet.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeController.
 */
@Controller
public class HomeController {

	/** The job service. */
	@Autowired
	private JobService jobService;

	/** The company service. */
	@Autowired
	private CompanyService companyService;

	/** The member service. */
	@Autowired
	private MemberService memberService;

	/** The skill service. */
	@Autowired
	private SkillService skillService;

	/** The resume service. */
	@Autowired
	private ResumeService resumeService;
	
	/**
	 * Go home page.
	 *
	 * @param model the model
	 * @param session the session
	 * @return home page
	 */
	@RequestMapping(value = "home")
	public String goHomePage(Model model, HttpSession session) {

		String result = null;
		Account account = LoginController.getAccountSession(session);

		List<Job> jobList = null;
		List<Company> companyList = null;

		if (Utils.isEmptyObject(account)) {

			jobList = jobService.getJobList(0, ConstantValues.MAX_RECORD_COUNT, true, null);
			companyList = companyService.getListCompany(0, ConstantValues.MAX_RECORD_COUNT, true);
			result = "home";
		} else {

			Member member = memberService.getMemberByAccountId(account.getId());
			List<Integer> skillIds = resumeService.getListSkillByMemberId(member.getId());
			jobList = jobService.getJobList(0, ConstantValues.MAX_RECORD_COUNT, false, skillIds);
			companyList = companyService.getListCompany(0, ConstantValues.MAX_RECORD_COUNT, false);
			model.addAttribute("account", account);
			result = "home_login";
		}

		model.addAttribute("firstItem", 0);
		model.addAttribute("maxItem", ConstantValues.MAX_RECORD_COUNT);
		model.addAttribute("currentPage", ConstantValues.CURRENT_PAGE);
		if (ConstantValues.MAX_RECORD_COUNT > jobList.size()) {
			model.addAttribute("isDisabledLoadJob", true);
		}
		model.addAttribute("jobList", jobList);
		model.addAttribute("companyList", companyList);

		return result;
	}
}