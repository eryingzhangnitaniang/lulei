package cn.gson.oasys.service;

import cn.gson.oasys.model.entity.user.Dept;

import java.util.List;

public interface UserDeptService {
    /**
     * 查询
     * @return
     */
    public List<Dept> findAll();

    /**
     * 添加
     * @param dept
     */
    public void  save(Dept dept);

    /**
     * 修改第一步
     * @param id
     * @return
     */
    public  Dept  findDeptById(long id);

    /**
     * 删除
     * @param id
     */
    public   void     deleteById(Long id);

    /**
     * 修改第二步
     * */
    public  void  update(Dept dept);
}
