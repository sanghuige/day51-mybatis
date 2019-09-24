package com.dao;

import com.po.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmpDao {
    //查询
    public List<Emp> searchAll();
    public int getCount();
    public List<Emp> findbyCondition(Emp emp);
    public List<Emp> findbyCondition2(Map<String, Object> empMap);

    //增加
    public int insert(Emp emp);
    public int insert2(Emp emp);

    //修改
    public int update(Map<String, Object> map);

    //删除
    public int delete(Map<String, Object> map);

//    关联映射查询
    public Emp findbyId1(Integer eid);
    public Emp findbyId2(Integer eid);

}
