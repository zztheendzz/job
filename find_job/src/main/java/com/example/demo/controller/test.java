package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.DTO.RecruitmentDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.config.userlogin.AuthenticationFacade;
import com.example.demo.model.ApplyPost;
import com.example.demo.model.Category;
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
import com.example.demo.service.MyFile;
import com.example.demo.service.RecruitmentService;
import com.example.demo.service.SaveJobService;
import com.example.demo.service.ServiceResponse;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class test {
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
	
//	private UploadService upload = new UploadService();
	

    private AuthenticationFacade authenticationFacade = new AuthenticationFacade();
    
    private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    
    public User userLogin() {
        Authentication authentication = authenticationFacade.getAuthentication();
        authentication.getName();
        authentication.getDetails().toString();
        return us.getUserByName(authentication.getName());
    }
    
	@GetMapping("/login")
	public String login() {
		return "public/login";
	}
	
	@PostMapping("/login")
	public String loginPost(Model model) {

		Authentication authentication = authenticationFacade.getAuthentication();
		if(authentication != null) {
			return"redirect:/home";
		}
		else return "public/login";

	}

	@PostMapping("/public/register")
	 public String register(@ModelAttribute("uDto") UserDTO uDTO,Model model) {
		
		us.saveUser(uDTO);
		return "redirect:/login";
	    }
	@GetMapping("/home")
	public String home(Model model) {
	/* hiển thị công ty, công việc , danh mục công việc nổi bật */
		
		List<User> userList = us.getListUsers();
		List<Recruitment> recruitmentList = rs.recruitmentsMap();
		List<Company> companyList = coms.companyMap();
		List<Category> categoryList = cats.categoryMap();
		model.addAttribute("categories", categoryList);
		model.addAttribute("users", userList);
		model.addAttribute("companies", companyList);
		model.addAttribute("recruitments", recruitmentList);
		return "public/home";
	}
//	 tìm kiếm theo công việc
	@PostMapping("/public/recruitment/search")
	public String SearchWord(Model model, @ModelAttribute("searchRecruitment") String searchRecruitment) {

		List<Category> listCategory = cats.getListCategory();
		List<Category> listCategoryResult = new ArrayList<>();
		List<Recruitment> listRecruitment  = new ArrayList<>();
	
		for(int i=0;i< listCategory.size();i++) {
			/* tìm kiếm theo các kí tự ng dùng gõ */
			if(Search(searchRecruitment, listCategory.get(i).getName())) {
				listCategoryResult.add(listCategory.get(i));
			}	
		}
		for(int i=0;i< listCategoryResult.size();i++) {
			rs.joinListRecruitment(listRecruitment, listCategoryResult.get(i).getRecruitmentList());
		}
		model.addAttribute("list", listRecruitment);
		model.addAttribute("keySearch", searchRecruitment);
		return "public/result-search";
	}
//	 tìm kiếm theo công ty
	@PostMapping("/public/user/search")
	public String SearchUser( @ModelAttribute("searchUser") String searchUser,Model model) {
		List<Company> list = coms.getListCompanies();
		List<Company> resultList = new ArrayList<>();
		for(int i=0;i< list.size();i++) {
			/* tìm kiếm theo các kí tự ng dùng gõ */
		if(Search(searchUser, list.get(i).getNameCompany())) {
			resultList.add(list.get(i));
		}
	}
		
		model.addAttribute("list", resultList);
		model.addAttribute("keySearch", searchUser);
		return "public/result-search-user";
	}
//	tìm kiếm theo địa chỉ
	@PostMapping("/public/recruitment/searchaddress")
	public String SearchAddress(Model model, @ModelAttribute("searchAddress") String searchAddress) {
		List<Recruitment> list = rs.getListRecruitment();
		List<Recruitment> resultList = new ArrayList<>();
		for(int i=0;i< list.size();i++) {
			if(Search(searchAddress, list.get(i).getAddress())) {

				resultList.add(list.get(i));
			}
		}
		model.addAttribute("list", resultList);
		model.addAttribute("keySearch", searchAddress);
		return "public/result-search-address";
	}
	
//	chi tiết công ty
	@GetMapping("/user/detail-company/{companiId}")
	public String detailCompany(Model model,@PathVariable("companiId") int companiId) {		
		model.addAttribute("company",coms.getComByid(companiId));
		return "public/detail-company";
	}
	
	@GetMapping("public/recruitment/category/{categorieId}")
	public String detailpost(@PathVariable("categorieId") int categorieId,Model model) {
		Recruitment recruitment = rs.getRecruitmentById(categorieId);
		model.addAttribute("company", recruitment.getCompany());
		model.addAttribute("recruitment",recruitment);
		
		return "public/detail-post";
	}
//	trang cá nhân
	@GetMapping("/user/profile")
	public String userProfile(Model model) {
		User user = new User();
		user = userLogin();
		Cv  cv = cvS.cvByUserId(user.getId());
		model.addAttribute("Cv",cv);
		model.addAttribute("userInformation",user);
		if(userLogin().geteRole()==ERole.ROLE_COMPANY) {
		model.addAttribute("companyInformation",coms.getCompanyFromUser(userLogin().getId()));
		}
		return "public/profile";
	}
//	cập nhật trang cá nhân
	@PostMapping("/user/update-profile")
	public String userUpdateProfile(@ModelAttribute("updateUser") User u,Model model) {
		User user = new User();
		user = userLogin();
		
		u.setId(user.getId());
		us.updateUser(u);
		
		return "redirect:/user/profile";
	}


//	danh sách công việc đã lưu
	@GetMapping("/user/save-job/get-list")
	public String savejob(Model model) {
		List<SaveJob> saveJobs = sjs.getSaveJobs();
		User user = new User();
		user = userLogin();

		model.addAttribute("userInformation",user);
		model.addAttribute("saveJobList",saveJobs);

		return "public/list-save-job";
	}
//	danh sách công việc đã apply
	@GetMapping("/user/get-list-apply")
	public String getListApply(Model model) {
		User user = new User();
		user = userLogin();
		List<ApplyPost> applyPosts= user.getApplyPostList();
		model.addAttribute("applyPosts",applyPosts);
		model.addAttribute("userInformation",user);
		System.out.println(user.getApplyPostList().size());
		return "public/list-apply-job";
	}
	
//	danh sách công ty theo dõi
	
	@GetMapping("/user/get-list-company")
	public String getListCompany(Model model) {
		User user = new User();
		user = userLogin();
		List<Company> companies= coms.getListFollowCompanyFromUser(user.getId());	
		model.addAttribute("companies",companies);
		return "public/list-follow-company";
	}
	
//	xoá follow công ty
	@GetMapping("/user/delete-follow/{idc}")
	public String deleteFollow(@PathVariable("idc") int idc) {
		User user = new User();
		user = userLogin();
		fcS.deleteFl(user, idc);
		return "redirect:/user/get-list-company";
	}
	
//	xem công việc của công ty đăng
	
	@GetMapping("/user/company-post/{companyId}")
	public String folloCompany(@PathVariable("companyId") int companyId,Model model) {
		List<Recruitment> recruitmentList = rs.getListReFromCompanyId(companyId);
		List<Company> companies = coms.companyMap();
		model.addAttribute("recruitmentList", recruitmentList);
		model.addAttribute("companies", companies);
		return "public/recruitment";
	}

//8
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
	
	@GetMapping("/recruitment/editpost/{recruitmentId}")
	public String editjob(@PathVariable("recruitmentId") int recruitmentId,Model model) {
		Recruitment recruitment = rs.getRecruitmentById(recruitmentId);
		List<Category>categories = cats.getListCategory();
		model.addAttribute("categories",categories);
		model.addAttribute("recruitment",recruitment);
		return "public/edit-job";
	}
//	cập nhật lis thông tin bài đăng
	@PostMapping("/recruitment/edit")
	public String editpost(@ModelAttribute("recruitmentDTO") RecruitmentDTO recruitmentDTO) {
		User user = new User();
		user = userLogin();
		Company company = coms.getCompanyFromUser(user.getId());
		rs.addRecruitment(recruitmentDTO,company);
		return "redirect:/user/list-post";

	}
//	xem chi tiết công việc
	@GetMapping("/recruitment/detail/{recruitmentId}")
	public String detailReCom(@ModelAttribute("recruitmentId") int recruitmentId,Model model ) {	
		Recruitment recruitment = rs.getRecruitmentById(recruitmentId);
		List<ApplyPost>  listApplyPosts = aps.getListApplyByReId(recruitmentId);
		 model.addAttribute("applyPosts",listApplyPosts);
		 model.addAttribute("recruitment",recruitment);
		return "public/detail-post";
	}
// xoá bài đăng
	@GetMapping("/recruitment/delete/{recruitmentId}")
	public String deletejob(@PathVariable("recruitmentId") int recruitmentId,Model model) {
		rs.delete(recruitmentId);
		return "redirect:/user/list-post";
	}
	
	@PostMapping("/recruitment/delete/{recruitmentId}")
	public String deletejob1(@PathVariable("recruitmentId") int recruitmentId,Model model) {
		rs.delete(recruitmentId);
		return "redirect:/user/list-post";
	}
//	lấy danh sách ng đã ứng tuyển
	@GetMapping("/list/user")
	public String listUser(Model model) {
		User user = new User();
		user = userLogin();
		List<ApplyPost> applyPostList = aps.getApplyPosts(coms.getCompanyFromUser(user.getId()).getId());
		model.addAttribute("applyPostList",applyPostList);
		return "public/list-user";
	}
//	tải/thêm cv
	@PostMapping( "/uploadFile")
	  public String uploadFile(@ModelAttribute("myFile") MyFile myFile, Model model) {
		User user = new User();
		user = userLogin();
	    model.addAttribute("message", "Upload success");
	    model.addAttribute("description", myFile.getDescription());
	    String cvPath = myFile.uploadFile(myFile);
	    cvS.addCv(cvPath, userLogin());   
	    return "redirect:/user/profile";
	  }
//	tải ảnh đại diện
	@PostMapping( "/uploadImage")
	  public String uploadImage(@ModelAttribute("myFile") MyFile myImage, Model model) {
	    model.addAttribute("message", "Upload success");
	    model.addAttribute("description", myImage.getDescription());
		User user = new User();
		user = userLogin();
	    String imageString = myImage.uploadFile(myImage);
		user.setImage(imageString);
		us.updateUser(user);
	    return "redirect:/user/profile";
	  }
//	cập nhâth thông tin công ty
	@PostMapping( "/user/update-company")
	  public String updateCompany(@ModelAttribute("company") Company company) {
	    coms.updateCompany(company,userLogin());
	    return "redirect:/user/profile";
	  }
//	tải logo công ty
	@PostMapping( "/uploadLogo")
	  public String updateLogoCompany(@ModelAttribute("logo") MyFile logo, Model model) {
	    model.addAttribute("message", "Upload success");
	    model.addAttribute("description", logo.getDescription());
	    Company company = getUserCompany();
	    String imageString = logo.uploadFile(logo);
	    company.setLogo(imageString);
		coms.updateCompany(company,userLogin());
	    return "redirect:/user/profile";
	  }
// xoá cv
	@PostMapping( "/user/deleteCv")
	  public String deleteCv() {
		cvS.addCv(null, userLogin());
	    return "redirect:/user/profile";
	  }
// chi tiết bài đăng
	@GetMapping("/recruitment/post")
	public String postjob1(Model model) {	
		List<Category>categories = cats.getListCategory();
		
		model.addAttribute("categories",categories);
			
		return "public/post-job";
	}
// thêm bài đăng
	@PostMapping("/recruitment/add")
	public String recruitmentadd(@ModelAttribute("recruitmentDTO") RecruitmentDTO recruitmentDTO) {
		rs.addRecruitment(recruitmentDTO, getUserCompany(), true);
		return "redirect:/postjob";
	}
// người dùng lưu bài đăng
		@PostMapping("/save-job/save/{idRe}")
		public ResponseEntity<Object> addBook(@PathVariable Integer idRe) {
			Boolean checkBoolean = sjs.addSaveJob(userLogin(), idRe);
			String checkString="";
			if(checkBoolean!=null) {
				checkString  = checkBoolean.toString();
			}
			ServiceResponse<Integer> response = new ServiceResponse<Integer>(checkString, idRe);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
//	theo dõi công ty
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
				System.out.println("daylaemail"+ checkBoolean.toString());
				checkString  = checkBoolean.toString();
			}
			ServiceResponse<Integer> response = new ServiceResponse<Integer>(checkString, idCompany);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
//		xoá bài đăng đã lưu
		@PostMapping("/save-job/delete/{idSj}")
		public ResponseEntity<Object> deleteRe(@PathVariable Integer idSj) {
			sjs.deleteSj(idSj);
			ServiceResponse<Integer> response = new ServiceResponse<Integer>("success", idSj);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
//	ứng tuyển dùng cv đã lưu
		@PostMapping("/user/apply-job1/{idRe}")
		public ResponseEntity<Object> applyJob1(@PathVariable Integer idRe) {
			
			Recruitment recruitment = rs.getRecruitmentById(idRe);
			Boolean checkBoolean = aps.addApplyPost(userLogin(), recruitment);
			String checkString="";
			if(checkBoolean!=null) {
				checkString  = checkBoolean.toString();
			}

			ServiceResponse<Integer> response = new ServiceResponse<Integer>(checkString, idRe);
			System.out.println(checkString+"123check");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}

//	ứng tuyển dùng cv mơi
		@PostMapping("/user/apply-job/{idRe}")
		public ResponseEntity<Object> uploadFile(HttpServletRequest request) 
				throws Exception {
			MyFile myFile = new MyFile();
			String checkString="";
			int idRe =Integer.parseInt(request.getParameter("idRe"));
			Recruitment recruitment = rs.getRecruitmentById(idRe);
		    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		    Collection<MultipartFile> filesCollection = multipartRequest.getFileMap().values();
		    try {
		        for (MultipartFile multipartFile : filesCollection) {
		        	myFile.setMultipartFile(multipartFile);
		        		}
		    	} catch (Exception e) {
				    	e.printStackTrace();
				    }    
		        	String cvPath =	myFile.uploadFile(myFile);
				    cvS.addCv(cvPath, userLogin());
				    
				    Boolean checkBoolean = aps.addApplyPost(userLogin(), recruitment);
					if(checkBoolean!=null) {
						checkString  = checkBoolean.toString();
					}
				    ServiceResponse<Integer> response = new ServiceResponse<Integer>(checkString, idRe);
				    return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		
//		duyệt cv 
		@PostMapping( "/user/approve/{idApply}")
		  public ResponseEntity<Object> approve(@PathVariable Integer idApply) {
			
//			applypost.status : 0 : chua duyet
//								1: da duyet
			
			aps.approve(idApply);
			ServiceResponse<Integer> response = new ServiceResponse<Integer>("true", idApply);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		  }
// lấy ra công ty (user.role = role.company)
	public Company getUserCompany() {
		User user = new User();
		user = userLogin();
		Company company = coms.getCompanyFromUser(user.getId());
		return company;
	}

//	hàm kiểm tra từ ng dùng viết
	public boolean Search(String keyWord, String refeWord) {
		// keyWord : từ tìm kiếm ng dùng gõ
		// refeWord: từ so sánh
		int count = 0;
		if(refeWord == null || refeWord=="") {return false;}
		for (int i = 0; i < refeWord.length(); i++) { 
			for (int j = 0; j < keyWord.length(); j++) {
				if (keyWord.charAt(j) == refeWord.charAt(i)) {
					count++;
				}
			}
		}
		if (count *2  >=  refeWord.length()) {
			return true;
		}
		return false;
	}

}
