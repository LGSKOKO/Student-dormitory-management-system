package com.lgs.dao;

import com.lgs.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/18 9:44
 * @description：Admin实体类的Dao接口
 * @modified By：
 * @version: $
 */
@Repository("adminDao")
public interface AdminDao {
    void insert(Admin admin);//插入数据
    void delete(String userName);//删除数据
    void update(@Param("admin")Admin admin,@Param("No") String No);//更新数据
    void updatePassword(Admin admin);//更新数据
    Admin selectByAdminNo(String userName);//通过userName查询单条数据
    List<Admin> selectByBuildingNo(String bNO);//根据宿舍楼号查询所有的数据
    List<Admin> selectAll();//查询所有的数据
}
