package com.lgs.dao;

import com.lgs.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/18 9:38
 * @description：Room实体类的Dao接口
 * @modified By：
 * @version: $
 */
public interface RoomDao {
    void insert(Room room);//插入数据
    void delete(String roomNo);//删除数据
    void update(@Param("room") Room room,@Param("No") String No);//更新数据
    void updateMoney(@Param("room") Room room,@Param("No") String No);//更新宿舍电费余额
    Room selectByRoomNo(String roomNo);//通过roomNo查询单条数据
    List<Room> selectByBuildingNo(String buildingNo);//通过buildingNo查询宿舍列表
    List<Room> selectAll();//查询所有的数据
}
