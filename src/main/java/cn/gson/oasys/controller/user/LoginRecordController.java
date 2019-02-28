package cn.gson.oasys.controller.user;

import cn.gson.oasys.model.entity.user.LoginRecord;

import cn.gson.oasys.service.LoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginRecordController {
    @Autowired
    private LoginRecordService loginRecordService;
    @RequestMapping("/loginRecordList")
    public   String   loginRecordList(Model model){
        List<LoginRecord> list = loginRecordService.findAll();
        model.addAttribute("loginRecord",list);
        return  "user/loginrecordmanage";
    }
}
