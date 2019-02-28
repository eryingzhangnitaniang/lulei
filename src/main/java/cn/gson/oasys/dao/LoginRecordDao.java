package cn.gson.oasys.dao;

import cn.gson.oasys.model.entity.user.LoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRecordDao extends JpaRepository<LoginRecord, Long> {
}
