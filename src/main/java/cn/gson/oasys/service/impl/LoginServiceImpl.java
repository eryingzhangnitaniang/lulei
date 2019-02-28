package cn.gson.oasys.service.impl;

import cn.gson.oasys.dao.LoginDao;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginDao   loginDao;
    @Override
    public Map<String, Object> userLogin(User user) {
        Map<String,Object>  map = new HashMap<>();
        String  str = "用户名不存在";
        List<User>   us = loginDao.findByuserName(user.getUserName());
        //List<User> us = loginDao.findAll();
        if (us!=null && us.size()>0){
            str = "密码错误";
            User  po = us.get(0);
            if(po.getPassword().equals(user.getPassword())){
                 str = "登录成功!";
                 map.put("userLogin",po);
            }
        }
        map.put("mag",str);
        return   map;

    }
}
