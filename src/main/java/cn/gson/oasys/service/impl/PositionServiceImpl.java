package cn.gson.oasys.service.impl;


import cn.gson.oasys.dao.PositionDao;
import cn.gson.oasys.model.entity.user.Position;

import cn.gson.oasys.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDao positionDao;

    @Override
    public List<Position> findAll() {
        return positionDao.findAll();
    }

    @Override
    public void save(Position position) {
       positionDao.save(position);
    }

    @Override
    public Position findpositionById(Long id) {
        return positionDao.findOne(id);
    }

    @Override
    public void update(Position position) {
      positionDao.save(position);
    }
}
