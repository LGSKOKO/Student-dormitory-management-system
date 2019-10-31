package com.lgs.dao;

import com.lgs.entity.Repair;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/25 9:04
 * @description：Repair实体类的Dao接口
 * @modified By：
 * @version: $
 */
public interface RepairDao {

    void insert(Repair repair);//插入数据
    void delete(String repairNo);//删除数据
    void update(@Param("repair")Repair repair, @Param("No") String No);//更新数据
    void updateStatus(@Param("status")String status,@Param("No") String No);//更新报销单处理状态
    Repair selectByRepairNo(String repairNo);//通过repairNo查询单条数据
    List<Repair> selectByBuildingNo(String bNo);//通过宿舍楼号查询数据
    List<Repair> selectByRoomNo(String rNo);//通过宿舍号查询数据
    List<Repair> selectAll();//查询所有的数据
}
