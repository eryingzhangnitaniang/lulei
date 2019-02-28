package cn.gson.oasys.dao;

import cn.gson.oasys.model.entity.user.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptDao extends JpaRepository<Dept, Long> {
}
