package com.origin.tool.mapper;

import com.origin.tool.entity.Model;
import java.util.List;

public interface ModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model record);

    Model selectByPrimaryKey(Integer id);

    List<Model> selectAll();

    int updateByPrimaryKey(Model record);
}