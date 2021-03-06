package vn.com.hiringviet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.hiringviet.api.dto.request.SearchRequestDTO;
import vn.com.hiringviet.api.dto.response.SearchSuggestResponseDTO;
import vn.com.hiringviet.common.AccountRoleEnum;
import vn.com.hiringviet.common.StatusResponseEnum;
import vn.com.hiringviet.constant.ConstantValues;
import vn.com.hiringviet.dto.AccountDTO;
import vn.com.hiringviet.dto.CompanyDTO;
import vn.com.hiringviet.dto.JobDTO;
import vn.com.hiringviet.dto.MemberDTO;
import vn.com.hiringviet.dto.MessageDTO;
import vn.com.hiringviet.dto.SkillDTO;
import vn.com.hiringviet.model.Account;
import vn.com.hiringviet.model.Company;
import vn.com.hiringviet.model.Country;
import vn.com.hiringviet.model.Job;
import vn.com.hiringviet.model.Member;
import vn.com.hiringviet.service.AccountService;
import vn.com.hiringviet.service.CompanyService;
import vn.com.hiringviet.service.CountryService;
import vn.com.hiringviet.service.JobService;
import vn.com.hiringviet.service.MailboxService;
import vn.com.hiringviet.service.MemberService;
import vn.com.hiringviet.service.ResumeService;
import vn.com.hiringviet.service.SkillService;
import vn.com.hiringviet.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchController.
 */
@Controller
public class SearchController {

	/** The mailbox service. */
	@Autowired
	private MailboxService mailboxService;
	
	/** The account service. */
	@Autowired
	private AccountService accountService;
	
	/** The skill service. */
	@Autowired
	private SkillService skillService;

	/** The member service. */
	@Autowired
	private MemberService memberService;

	/** The company service. */
	@Autowired
	private CompanyService companyService;

	/** The job service. */
	@Autowired
	private JobService jobService;

	/** The resume service. */
	@Autowired
	private ResumeService resumeService;

	/** The country service. */
	@Autowired
	private CountryService countryService;

	/**
	 * Go search.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String goSearch(Model model) {

		Account account = getLoggedAccount();

		List<Job> jobList = null;
		List<Company> companyList = null;

		if (Utils.isEmptyObject(account) || AccountRoleEnum.COMPANY == account.getUserRole()) {

//			jobList = jobService.getJobList(null, 0, ConstantValues.MAX_RECORD_COUNT,
//					true, null);
//			companyList = companyService.getListCompany(0,
//					ConstantValues.MAX_RECORD_COUNT, true);
		} else {

			Member member = memberService.getMemberByAccount(account);
			List<Integer> skillIds = resumeService.getListSkillByMemberId(member.getId());
//			jobList = jobService.getJobList(null, 0, ConstantValues.MAX_RECORD_COUNT, false, skillIds);
//			companyList = companyService.getListCompany(0, ConstantValues.MAX_RECORD_COUNT, false);
			model.addAttribute("account", account);
		}

		List<Country> countries = countryService.getCountryList();
		model.addAttribute("countries", countries);

		model.addAttribute("firstItem", 0);
		model.addAttribute("maxItem", ConstantValues.MAX_RECORD_COUNT);
		model.addAttribute("currentPage", ConstantValues.CURRENT_PAGE);
		if (ConstantValues.MAX_RECORD_COUNT > jobList.size()) {
			model.addAttribute("isDisabledLoadJob", true);
		}
		model.addAttribute("jobList", jobList);
		model.addAttribute("companyList", companyList);

		return "/search";
	}

	/**
	 * Gets the suggest.
	 *
	 * @param searchRequestDTO the search request dto
	 * @param session the session
	 * @return the suggest
	 */
	@RequestMapping(value = "/search/suggest", method = RequestMethod.POST)
	public @ResponseBody SearchSuggestResponseDTO getSuggest(@RequestBody SearchRequestDTO searchRequestDTO, HttpSession session) {

		searchRequestDTO.setKeyWord(searchRequestDTO.getKeyWord().replace("\"", ""));
		SearchSuggestResponseDTO response = new SearchSuggestResponseDTO();

		List<MemberDTO> memberResponseDTOs = null;
		if (searchRequestDTO.isSearchMember()) {
			memberResponseDTOs = memberService.getListMemberSuggest(searchRequestDTO.getKeyWord());
		}

		List<CompanyDTO> companyResponseDTOs = null;
		if (searchRequestDTO.isSearchCompany()) {
			companyResponseDTOs = companyService.getListCompanySuggest(searchRequestDTO.getKeyWord());
		}

		List<SkillDTO> skillDTOs = null;
		if (searchRequestDTO.isSearchSkill()) {
			skillDTOs = skillService.searchSkillByKeyWord(searchRequestDTO.getKeyWord());
		}

		List<JobDTO> jobDTOs = null;
		if (searchRequestDTO.isSearchJob()) {
			jobDTOs = jobService.searchJobByKeyWord(searchRequestDTO.getKeyWord());
		}

		response.setResult(StatusResponseEnum.SUCCESS.getStatus());
		response.setCompanyResponseDTOs(companyResponseDTOs);
		response.setMemberResponseDTOs(memberResponseDTOs);
		response.setJobDTOs(jobDTOs);
		response.setSkillDTOs(skillDTOs);
		return response;
	}

	/**
	 * Suggest skill.
	 *
	 * @param keyWord the key word
	 * @param session the session
	 * @return the list
	 */
	@RequestMapping(value = "/search/suggestSkill", method = RequestMethod.POST)
	public @ResponseBody List<SkillDTO> suggestSkill(@RequestBody String keyWord, HttpSession session) {

		return skillService.searchSkillByKeyWord(keyWord);
	}

	/**
	 * Gets the follow list.
	 *
	 * @param accountDTO the account dto
	 * @param session the session
	 * @return the follow list
	 */
	@RequestMapping(value = "/search/follow/list", method = RequestMethod.POST)
	public @ResponseBody List<AccountDTO> getFollowList(@RequestBody AccountDTO accountDTO, HttpSession session) {
		List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
		if (accountService.getFollowList(String.valueOf(accountDTO.getId())) != null) {
			accountDTOs = accountService.getFollowList(String.valueOf(accountDTO.getId()));
		}
		return accountDTOs;
	}

	/**
	 * Gets the owner message list.
	 *
	 * @param accountDTO the account dto
	 * @param session the session
	 * @return the owner message list
	 */
	@RequestMapping(value = "/search/message/owner", method = RequestMethod.POST)
	public @ResponseBody List<MessageDTO> getOwnerMessageList(@RequestBody AccountDTO accountDTO, HttpSession session) {

		Account account = getLoggedAccount();
		if (account != null) {
		}

		return mailboxService.getOwnerMailList(accountDTO.getId());
	}
	
	/**
	 * Gets the logged account.
	 *
	 * @return the logged account
	 */
	private Account getLoggedAccount() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof Account) {
			Account loginedAccount = (Account) principal;
			return loginedAccount;
		}
		return null;
	}
}
