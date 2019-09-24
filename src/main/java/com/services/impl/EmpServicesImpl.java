package com.services.impl;

import com.dao.EmpDao;
import com.github.pagehelper.PageHelper;
import com.po.Emp;
import com.services.EmpServices;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class EmpServicesImpl implements EmpServices {
    @Autowired
    private EmpDao empDao;

//    @Autowired
//    private  SqlSessionFactory sqlSessionFactory;


    @Override
    public List<Emp> searchAll() {
        return empDao.searchAll();
    }



    @Override
    public int getCount(SqlSession sqlSession) {

//        EmpDao empDao = getEmpDao();
//        int count = empDao.getCount();
        return empDao.getCount();
    }

//    private EmpDao getEmpDao( ){
        //      开启二级缓存
//        SqlSession session  = sqlSessionFactory.openSession();

//        EmpDao empDao  = session.getMapper(EmpDao.class);
//        return empDao;
//    }

    @Override
    public List<Emp> findbyCondition(Emp emp) {
        return empDao.findbyCondition( emp);
    }

    @Override
    public List<Emp> findbyCondition2(Map<String, Object> empMap) {

        return empDao.findbyCondition2(empMap);
    }
    //增加
    @Override
    public int insert(Emp emp) {
        return empDao.insert(emp);
    }

    @Override
    public int insert2(Emp emp) {
        return empDao.insert2(emp);
    }



//修改
    @Override
    public int update(Map<String, Object> map) {
        return empDao.update(map);
    }
//删除
    @Override
    public int delete(Map<String,Object> map) {
        return empDao.delete( map);
    }




}
