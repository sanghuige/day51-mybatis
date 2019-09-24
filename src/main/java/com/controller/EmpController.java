package com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.po.Emp;
import com.po.Page;
import com.services.EmpServices;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpServices empServices;


    @RequestMapping("/get")
    @ResponseBody
    public PageInfo<Emp> List(Integer pageSize, Integer pageNum, String[] orderName, String[] orderValue) {
//
        System.out.println("size：" + pageSize);
        System.out.println("start：" + pageNum);
//        第一条数据是第几条
//        Integer start = (page.getPageNum() - 1) * page.getPageSize();

        Map<String, Object> map = new HashMap<>();

        for(int i=0;i<orderName.length;i++){
            if(!orderValue[i].equals("")){
                if(orderName[i].equals("eid")){
                    map.put(orderName[i],orderValue[i]);
                }else {
                    map.put(orderName[i],"%"+orderValue[i]+"%");
                }

            }
        }
//        map.put("start", start);
//        map.put("size", page.getPageSize());
        //分页处理，显示第一页的数据
//        启动github pagehelper插件
        PageHelper.startPage(pageNum, pageSize);
//        开始查询
        List<Emp> emps = empServices.findbyCondition2(map);
        //        获取查询得相关信息
        PageInfo<Emp> pageInfo = new PageInfo<>(emps);

        return pageInfo;
    }


}
