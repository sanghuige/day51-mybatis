package com.dao;

import com.po.Dept;
import com.po.Emp;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface DeptDao {
public Dept findById(Integer did);
}
