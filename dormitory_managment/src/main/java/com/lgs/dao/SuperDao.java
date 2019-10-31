package com.lgs.dao;

import com.lgs.entity.Super;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/18 9:19
 * @description：super实体类的Dao层
 * @modified By：
 * @version: $
 */
@Repository("superDao")
public interface SuperDao {
    void update(Super supers);//更新数据
    void updatePassword(Super supers);////更新数据修改密码
    Super selectBySuperNo(String username);//通过userName查询单条数据
}
