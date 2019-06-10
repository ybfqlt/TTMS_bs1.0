package com.coco.dao;

import com.coco.entity.Salestatistics;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SalestatisticsMapper {
    int deleteByPrimaryKey(Integer salesStatisticsId);

    int insert(Salestatistics record);

    //根据movieId更新对应的票房信息
    Salestatistics selectBymovieId(Integer movieId);

    List<Salestatistics> selectAll();

    //根据movieId更新对应的票房信息
    int updateBymovieId(Salestatistics record);

    int updateByPrimaryKey(Salestatistics record);
}