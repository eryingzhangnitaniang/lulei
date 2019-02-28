package cn.gson.oasys.service;

import cn.gson.oasys.model.entity.user.Position;

import java.util.List;

public interface PositionService {
    /**
     * 查询全部
     * @return
     */
    List<Position> findAll();

    /**
     * 添加
     * @param position
     */
    void save(Position position);

    /**
     * 查单个对象
     * @param id
     * @return
     */
    Position findpositionById(Long id);

    /**
     * 修改
     * @param position
     */
    void update(Position position);
}
