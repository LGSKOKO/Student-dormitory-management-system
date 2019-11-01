package com.lgs.dao;

import com.lgs.entity.College;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/18 9:34
 * @description：College实体类的Dao接口
 * @modified By：
 * @version: $
 */
@Repository("collegeDao")
public interface CollegeDao {
    void insert(College college);//插入数据
    void delete(String collegeNo);//删除数据
    void update(College college);//更新数据
    College selectByCollegeNo(String collegeNo);//通过collegeNo查询单条数据
    List<College> selectAll();//查询所有的数据
}
