package cn.gson.oasys.controller.login;


import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


@Controller
public class LoginController {

     @Resource
     private LoginService loginService;
     /**
      * 登录界面的显示
      * @return
      */
     @RequestMapping("/")
     public  String  index(){
          return  "login/login";
     }

     @RequestMapping("/login")
     public String loginCheck(HttpSession session, HttpServletRequest req, Model model, User user) throws Exception {

          String ca=req.getParameter("code").toLowerCase();
          String sesionCode = (String) req.getSession().getAttribute("codeid");

          if(!ca.equals(sesionCode.toLowerCase())){
               System.out.println("验证码输入错误!");
               model.addAttribute("errormess", "验证码输入错误!");
               req.setAttribute("errormess","验证码输入错误!");
               return "login/login";
          }

          Map<String,Object> map= loginService.userLogin(user);

          User upo= (User) map.get("userLogin");
          if(upo!=null){
               session.setAttribute("upo", upo);
          }
          String str = (String) map.get("mag");
          if(str.equals("登录成功!")){
               return "index/index";
          }else{
               return "login/login";
          }

     }
     @RequestMapping("captcha")
     public void captcha(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
          response.setHeader("Pragma", "No-cache");
          response.setHeader("Cache-Control", "no-cache");
          response.setDateHeader("Expires", 0);
          response.setContentType("image/jpeg");

          // 生成随机字串
          String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

          // 生成图片
          int w = 135, h = 40;
          VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);

          // 将验证码存储在session以便登录时校验
          session.setAttribute("codeid", verifyCode.toLowerCase());
     }
}
