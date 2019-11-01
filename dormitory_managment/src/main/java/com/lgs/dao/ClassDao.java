package com.lgs.dao;

import com.lgs.entity.Class;

import java.util.List;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/18 9:40
 * @description：Class实体类的Dao接口
 * @modified By：
 * @version: $
 */
public interface ClassDao {
    void insert(Class classes);//插入数据
    void delete(String classNo);//删除数据
    void update(Class classes);//更新数据
    Class selectByClassNo(String classNo);//通过classNo查询单条数据
    List<Class> selectAll();//查询所有的数据
}
