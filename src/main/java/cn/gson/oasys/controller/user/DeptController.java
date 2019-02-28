package cn.gson.oasys.controller.user;



import cn.gson.oasys.dao.DeptDao;
import cn.gson.oasys.model.entity.user.Dept;

import cn.gson.oasys.service.UserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class DeptController {
	
	@Autowired
	DeptDao deptdao;
	@Autowired
	private UserDeptService uservice;



	@RequestMapping("/deptList")
	public   String   deptList(Model model){
		List<Dept> list = uservice.findAll();
		model.addAttribute("dept",list);
		return  "user/deptmanage";
	}

	@RequestMapping("/toAdd")
	public String toAdd() {
		return "user/deptadd";
	}

	@RequestMapping("/add")
	public String add(Dept user) {
		uservice.save(user);
		return "redirect:/deptList";
	}

	@RequestMapping("/toEdit")
	public String toEdit(Model model,Long id) {
		Dept dept = uservice.findDeptById(id);
		model.addAttribute("dept", dept);
		return "user/deptedit";
	}

	@RequestMapping("/edit")
	public String edit(Dept user) {
		uservice.update(user);
		return "redirect:/deptList";
	}


	@RequestMapping("/delete")
	public String delete(Long id) {
		uservice.deleteById(id);
		return "redirect:/deptList";
	}

/*	@Autowired
	UserDao udao;
	@Autowired
	PositionDao pdao;
	*/
/*	*//**
	 * 第一次进入部门管理页面
	 * @return
	 *//*
	@RequestMapping("deptmanage")
	public String deptmanage(Model model) {
		List<Dept> depts = (List<Dept>) deptdao.findAll();
		System.out.println(depts);
		model.addAttribute("depts",depts);
		return "user/deptmanage";
	}
	
	@RequestMapping(value = "deptedit" ,method = RequestMethod.POST)
	public String adddept(@Valid Dept dept,@RequestParam("xg") String xg,BindingResult br,Model model){
		System.out.println(br.hasErrors());
		System.out.println(br.getFieldError());
		if(!br.hasErrors()){
			System.out.println("没有错误");
			Dept adddept = deptdao.save(dept);
			if("add".equals(xg)){
				System.out.println("新增拉");
				Position jinli = new Position();
				jinli.setDeptid(adddept.getDeptId());
				jinli.setName("经理");
				Position wenyuan = new Position();
				wenyuan.setDeptid(adddept.getDeptId());
				wenyuan.setName("文员");
				pdao.save(jinli);
				pdao.save(wenyuan);
			}
			if(adddept!=null){
				System.out.println("插入成功");
				model.addAttribute("success",1);
				return "/deptmanage";
			}
		}
		System.out.println("有错误");
		model.addAttribute("errormess","错误！~");
		return "user/deptedit";
	}
	
	@RequestMapping(value = "deptedit" ,method = RequestMethod.GET)
	public String changedept(@RequestParam(value = "dept",required=false) Long deptId,Model model){
		if(deptId!=null){
			Dept dept = deptdao.findOne(deptId);
			model.addAttribute("dept",dept);
		}
		return "user/deptedit";
	}
	
	@RequestMapping("readdept")
	public String readdept(@RequestParam(value = "deptid") Long deptId,Model model){
		
		Dept dept = deptdao.findOne(deptId);
		User deptmanage = null;
		if(dept.getDeptmanager()!=null){
			deptmanage = udao.findOne(dept.getDeptmanager());
			model.addAttribute("deptmanage",deptmanage);
		}
		List<Dept> depts = (List<Dept>) deptdao.findAll();
		List<Position> positions = pdao.findByDeptidAndNameNotLike(1L, "%经理");
		System.out.println(deptmanage);
		List<User> formaluser = new ArrayList<>();
		List<User> deptusers = udao.findByDept(dept);
		
		for (User deptuser : deptusers) {
			Position position = deptuser.getPosition();
			System.out.println(deptuser.getRealName()+":"+position.getName());
			if(!position.getName().endsWith("经理")){
				formaluser.add(deptuser);
			}
		}
		System.out.println(deptusers);
		model.addAttribute("positions",positions);
		model.addAttribute("depts",depts);
		model.addAttribute("deptuser",formaluser);
		
		model.addAttribute("dept",dept);
		model.addAttribute("isread",1);
		
		return "user/deptread";
		
	}
	
	@RequestMapping("deptandpositionchange")
	public String deptandpositionchange(@RequestParam("positionid") Long positionid,
			@RequestParam("changedeptid") Long changedeptid,
			@RequestParam("userid") Long userid,
			@RequestParam("deptid") Long deptid,
			Model model){
		User user = udao.findOne(userid);
		Dept changedept = deptdao.findOne(changedeptid);
		Position position = pdao.findOne(positionid);
		user.setDept(changedept);
		user.setPosition(position);
		udao.save(user);
		System.out.println(deptid);
		
		model.addAttribute("deptid",deptid);
		return "/readdept";
	}
	
	@RequestMapping("deletdept")
	public String deletdept(@RequestParam("deletedeptid") Long deletedeptid){
		Dept dept = deptdao.findOne(deletedeptid);
		List<Position> ps = pdao.findByDeptid(deletedeptid);
		for (Position position : ps) {
			System.out.println(position);
			pdao.delete(position);
		}
		deptdao.delete(dept);
		return "/deptmanage";
		
	}
	
	@RequestMapping("deptmanagerchange")
	public String deptmanagerchange(@RequestParam(value="positionid",required=false) Long positionid,
			@RequestParam(value="changedeptid",required=false) Long changedeptid,
			@RequestParam(value="oldmanageid",required=false) Long oldmanageid,
			@RequestParam(value="newmanageid",required=false) Long newmanageid,
			@RequestParam("deptid") Long deptid,
			Model model){
		System.out.println("oldmanageid:"+oldmanageid);
		System.out.println("newmanageid:"+newmanageid);
		Dept deptnow = deptdao.findOne(deptid);
		if(oldmanageid!=null){
			User oldmanage = udao.findOne(oldmanageid);
			
			Position namage = oldmanage.getPosition();
			
			Dept changedept = deptdao.findOne(changedeptid);
			Position changeposition = pdao.findOne(positionid);
			
			oldmanage.setDept(changedept);
			oldmanage.setPosition(changeposition);
			udao.save(oldmanage);
			
			if(newmanageid!=null){
				User newmanage = udao.findOne(newmanageid);
				newmanage.setPosition(namage);
				deptnow.setDeptmanager(newmanageid);
				deptdao.save(deptnow);
				udao.save(newmanage);
			}else{
				deptnow.setDeptmanager(null);
				deptdao.save(deptnow);
			}
			
		}else{
			User newmanage = udao.findOne(newmanageid);
			Position manage = pdao.findByDeptidAndNameLike(deptid, "%经理").get(0);
			newmanage.setPosition(manage);
			deptnow.setDeptmanager(newmanageid);
			deptdao.save(deptnow);
			udao.save(newmanage);
		}
		
		
		
		model.addAttribute("deptid",deptid);
		return "/readdept";
	}*/
}
