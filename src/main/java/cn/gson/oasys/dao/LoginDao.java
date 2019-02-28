package cn.gson.oasys.dao;

import cn.gson.oasys.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginDao  extends JpaRepository<User,Long>{

    List<User>  findByuserName(String userName);
}
