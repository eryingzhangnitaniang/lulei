package cn.gson.oasys.service;

import cn.gson.oasys.model.entity.user.LoginRecord;

import java.util.List;

public interface LoginRecordService {
    /**
     * 查询全部
     * @return
     */
    public List<LoginRecord> findAll();
}
