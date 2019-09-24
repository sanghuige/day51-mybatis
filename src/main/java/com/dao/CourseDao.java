package com.dao;

import com.po.Course;

public interface CourseDao {
    public Course findBycId(Integer cid);
}
