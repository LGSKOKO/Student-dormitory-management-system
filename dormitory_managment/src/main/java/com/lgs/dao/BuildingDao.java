package com.lgs.dao;

import com.lgs.entity.Building;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/18 9:27
 * @description：Building实体类的Dao接口
 * @modified By：
 * @version: $
 */
@Repository("buildingDao")
public interface BuildingDao {
    void insert(Building building);//插入数据
    void delete(String buildingNo);//删除数据
    void update(@Param("building") Building building,@Param("No") String No);//更新数据
    Building selectByBuildingNo(String buildingNo);//通过buildingNo查询单条数据
    List<Building> selectAll();//查询所有的数据
}
