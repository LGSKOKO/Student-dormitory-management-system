package com.lgs.dao;

import com.lgs.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/18 20:18
 * @description：Student实体类的Dao接口
 * @modified By：
 * @version: $
 */
@Repository("studentDao")
public interface StudentDao {
    void insert(Student student);//插入数据
    void delete(String username);//删除数据
    void updatePassword(Student student);//修改密码
    void updateMoney(Student student);//修改金额
    void update(@Param("student") Student student,@Param("No")String No);//更新数据
    Student selectByStudentNo(String username);//通过username查询单条数据
    List<Student> selectByBuildingNo(String bNo);//根据宿舍楼查询
    List<Student> selectByRoomNo(String rNo);//根据宿舍查询
    List<Student> selectByCollegeNo(String coNo);//根据学院查询
    List<Student> selectByCONOAndBNO(@Param("CONO") String CONO,@Param("BNO") String BNO);//根据学院查询和宿舍楼查询
    List<Student> selectByClassNo(String clNo);//根据班级查询
    List<Student> selectByCLNOAndBNO(@Param("CLNO")String CLNO,@Param("BNO") String BNO);//根据班级和宿舍楼查询
    List<Student> selectAll();//查询所有的数据
}
