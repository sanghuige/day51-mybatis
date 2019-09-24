package com.services;

import com.po.Emp;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public interface EmpServices {
    //查询
    public List<Emp> searchAll();
    public  int getCount(SqlSession sqlSession);

    public List<Emp> findbyCondition(Emp emp);
    public List<Emp> findbyCondition2(Map<String,Object> empMap);


    //增加
    public int insert(Emp emp);
    public int insert2(Emp emp);
    //修改
    public int update(Map<String,Object> map);

    //删除
    public int delete(Map<String,Object> map);
}
