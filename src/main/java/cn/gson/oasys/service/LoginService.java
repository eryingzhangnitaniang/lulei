package cn.gson.oasys.service;

import cn.gson.oasys.model.entity.user.User;

import java.util.Map;

public interface LoginService {

     public Map<String,Object> userLogin(User user);
}
