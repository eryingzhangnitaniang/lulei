package cn.gson.oasys.service.impl;


import cn.gson.oasys.dao.LoginRecordDao;
import cn.gson.oasys.model.entity.user.LoginRecord;

import cn.gson.oasys.service.LoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginRecordServiceImpl implements LoginRecordService {
    @Autowired
    private LoginRecordDao loginRecordDao;
    @Override
    public List<LoginRecord> findAll() {
        return loginRecordDao.findAll();
    }
}
