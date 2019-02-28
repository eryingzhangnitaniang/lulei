package cn.gson.oasys.service.impl;

import cn.gson.oasys.dao.DeptDao;
import cn.gson.oasys.model.entity.user.Dept;

import cn.gson.oasys.service.UserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDeptServiceImpl implements UserDeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> findAll() {
        return deptDao.findAll();
    }

    @Override
    public void save(Dept dept) {
        deptDao.save(dept);
    }

    @Override
    public Dept findDeptById(long id) {
        return deptDao.findOne(id);
    }

    @Override
    public void deleteById(Long id) {
        deptDao.delete(id);
    }

    @Override
    public void update(Dept dept) {
        deptDao.save(dept);
    }
}
