package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.userlogin.AuthenticationFacade;
import com.example.demo.model.Company;
import com.example.demo.model.Cv;
import com.example.demo.model.ERole;
import com.example.demo.model.Recruitment;
import com.example.demo.model.SaveJob;
import com.example.demo.model.User;
import com.example.demo.service.ApplyPostService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CompanyService;
import com.example.demo.service.CvService;
import com.example.demo.service.FollowCompanyService;
import com.example.demo.service.RecruitmentService;
import com.example.demo.service.SaveJobService;
import com.example.demo.service.ServiceResponse;
import com.example.demo.service.UploadService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService us = new UserService();

	@Autowired
	private RecruitmentService rs = new RecruitmentService();

	@Autowired
	private CompanyService coms = new CompanyService();

	@Autowired
	private CategoryService cats = new CategoryService();

	@Autowired
	private ApplyPostService aps = new ApplyPostService();
	
	@Autowired
	private SaveJobService sjs = new SaveJobService();
	
	@Autowired
	private CvService cvS = new CvService();
		
	
	@Autowired
	private FollowCompanyService fcS = new FollowCompanyService();
	
	private UploadService upload = new UploadService();
	

    private AuthenticationFacade authenticationFacade = new AuthenticationFacade();
    
    private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    
    public User userLogin() {
        Authentication authentication = authenticationFacade.getAuthentication();
        authentication.getName();
        authentication.getDetails().toString();
        return us.getUserByName(authentication.getName());
    }
    
    
	@GetMapping("/user/detail-company/{companiId}")
	public String detailCompany(Model model,@PathVariable("companiId") int companiId) {
		List<Company> companyList = coms.getListCompanies();
		
		model.addAttribute("company",coms.getComByid(companiId));
		return "public/detail-company";
	}
	
	@GetMapping("/user/profile")
	public String userProfile(Model model) {
		
		User user = new User();
		user = userLogin();
		
		Cv  cv = cvS.cvByUserId(user.getId());
		
		model.addAttribute("Cv",cv);
		model.addAttribute("userInformation",user);
		if(userLogin().geteRole()==ERole.ROLE_COMPANY) {
			System.out.println("day la erole");
		model.addAttribute("companyInformation",coms.getCompanyFromUser(userLogin().getId()));
		}
		return "public/profile";
	}
	
	@PostMapping("/user/update-profile")
	public String userUpdateProfile(@ModelAttribute("updateUser") User u,Model model) {
		User user = new User();
		user = userLogin();
		
		u.setId(user.getId());
		us.updateUser(u);
		
		return "redirect:/user/profile";
	}
	
	@GetMapping("/user/save-job/get-list")
	public String savejob(Model model) {
		List<SaveJob> saveJobs = sjs.getSaveJobs();

		User user = new User();
		user = userLogin();
		
		model.addAttribute("userInformation",user);
		model.addAttribute("saveJobList",saveJobs);

		
		return "public/list-save-job";
	}
	
	@GetMapping("/user/get-list-apply")
	public String getListApply(Model model) {


		User user = new User();
		user = userLogin();
		
		List<SaveJob> saveJobList= sjs.getSaveJobsFromUser(user.getId());
		
		model.addAttribute("saveJobList",saveJobList);
		
		model.addAttribute("userInformation",user);


		
		return "public/list-apply-job";
	}
	
	@GetMapping("/user/get-list-company")
	public String getListCompany(Model model) {

		User user = new User();
		user = userLogin();
			
		List<Company> companies= coms.getListFollowCompanyFromUser(user.getId());	
		List<SaveJob> saveJobList= sjs.getSaveJobsFromUser(user.getId());
		
		model.addAttribute("companies",companies);
		model.addAttribute("saveJobList",saveJobList);

		return "public/list-follow-company";
	}
	
	@GetMapping("/user/company-post/{companyId}")
	public String folloCompany(@PathVariable("companyId") int companyId,Model model) {
		
		List<Recruitment> recruitmentList = rs.getListReFromCompanyId(companyId);
		
		model.addAttribute("recruitmentList", recruitmentList);
		
		return "public/recruitment";
	}
	
	@GetMapping("/user/list-post")
	public String postlist(Model model) {
		
		User user = new User();
		user = userLogin();
		
		if(user.geteRole()==ERole.ROLE_COMPANY) {
			
			Company company = coms.getCompanyFromUser(user.getId());
			
			List<Recruitment> recruitmentList = company.getRecruitmentList();
			List<Recruitment> list = new ArrayList<>();
			
			for (Recruitment recruitment : recruitmentList) {
				
				if(recruitment.getStatus() != 3) {
					list.add(recruitment);
				}	
			}
			
			model.addAttribute("recruitmentList", list);
		}

		return "public/post-list";
	}
	@PostMapping( "/user/update-company")
	  public String updateCompany(@ModelAttribute("company") Company company) {

//		User user = new User();
//		user = userLogin();
//		company.setUser(userLogin());
	    coms.updateCompany(company,userLogin());
	    
	    return "redirect:/user/profile";

	  }
	@PostMapping( "/user/deleteCv")
	  public String deleteCv() {
		
		cvS.addCv(null, userLogin());

	    return "redirect:/user/profile";
	  }
	
	@PostMapping("/user/follow-company/{idCompany}")
	public ResponseEntity<Object> followCompany(@PathVariable Integer idCompany) {
		/*
		 * Boolean checkBoolean
		 * true : theo doi thanh cong 
		 * fales : ban phai dang nhap  
		 * khac : ban da theo doi
		 * 
		 */	
		Boolean checkBoolean = fcS.isFollow(userLogin(), idCompany);
		String checkString="";
		if(checkBoolean!=null) {
			checkString  = checkBoolean.toString();
		}
		ServiceResponse<Integer> response = new ServiceResponse<Integer>(checkString, idCompany);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("/user/apply-job1/{idRe}")
	public ResponseEntity<Object> applyJob1(@PathVariable Integer idRe) {
		
		Recruitment recruitment = rs.getRecruitmentById(idRe);
		
		Boolean checkBoolean = aps.addApplyPost(userLogin(), recruitment);
		String checkString="";
		if(checkBoolean!=null) {
			checkString  = checkBoolean.toString();
		}

		ServiceResponse<Integer> response = new ServiceResponse<Integer>(checkString, idRe);
		System.out.println(checkString);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
