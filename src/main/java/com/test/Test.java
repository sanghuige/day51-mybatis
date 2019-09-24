package com.test;

import com.dao.CourseDao;
import com.dao.DeptDao;
import com.dao.EmpDao;
import com.dao.TeacherDao;
import com.po.Course;
import com.po.Dept;
import com.po.Emp;
import com.po.Teacher;
import com.services.EmpServices;
import com.services.impl.EmpServicesImpl;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-context-druid.xml",
        "classpath:spring-context-mybatis.xml",
        "classpath:spring-context.xml"})
public class Test {
    @Autowired
    private EmpServices empServices;
    @Autowired
    private EmpDao empDao;
    @Autowired
    private DeptDao deptDao;


    @org.junit.Test
    public void test() {
        List<Emp> emps = empServices.searchAll();
        emps.forEach(emp -> System.out.println(emp));
    }

    @org.junit.Test
    public void test1() {
//        int count = empServices.getCount();
//        System.out.println(count);
    }

    @org.junit.Test
    public void test2() {
        Emp emp = new Emp();
//        emp.setEid(1);
        emp.setEname("庆丰");
        List<Emp> emps = empServices.findbyCondition(emp);
        emps.forEach(e -> System.out.println(e));
    }

    @org.junit.Test
    public void test3() {
        Map<String, Object> map = new HashMap<>();
//        map.put("ename","%东%");
//        map.put("job","%海贼%");
        map.put("start", 2);
        map.put("size", 4);
//        List<Emp> emps = empServices.findbyCondition2(map,);
//        emps.forEach(e -> System.out.println(e));
    }


    //    增加
    //自增
    @org.junit.Test
    public void test4() {
        Emp emp = new Emp();
        emp.setEid(1);
        emp.setEname("阿灿");
        emp.setJob("公务员");
        emp.setSalary(10000.00);
        int rows = empServices.insert2(emp);
        System.out.println(rows);
        System.out.println(emp.getEid());
    }

    // 不自增
    @org.junit.Test
    public void test5() {
        Emp emp = new Emp();
        emp.setEid(1);
        emp.setEname("阿灿");
        emp.setJob("公务员");
        emp.setSalary(10000.00);
        int rows = empServices.insert(emp);
        System.out.println(rows);
    }

    //    修改
    @org.junit.Test
    public void test6() {
        Map<String, Object> map = new HashMap<>();
        map.put("eid", 12);
//        List<Emp> emps = empServices.findbyCondition2(map);
//        emps.forEach(emp -> System.out.println(emp));
//        if(emps!=null && emps.size()>0){
//            map.put("ename","北皇庆丰");
//            map.put("job","海贼");
//            map.put("salary",50000.00);
//            int rows = empServices.update(map);
//            System.out.println(rows);
//        }

    }

    // 删除
    @org.junit.Test
    public void test7() {
        Map<String, Object> map = new HashMap<>();
        map.put("eid", 12);
        int rows = empServices.delete(map);
        System.out.println(rows);
    }

    // 多对一
//    推荐版
    @org.junit.Test
    public void test8() {
        Emp emp = empDao.findbyId1(2);
        System.out.println(emp);
    }

    //    繁杂版
    @org.junit.Test
    public void test9() {
        Emp emp = empDao.findbyId2(2);
        System.out.println(emp);
    }


    //    一对多
    @org.junit.Test
    public void test10() {
        Dept dept = deptDao.findById(2);
        System.out.println(dept);
        System.out.println(dept.getEmps());
    }

    @Autowired
    private CourseDao courseDao;

    @org.junit.Test
    public void test11() {
        Course course = courseDao.findBycId(1);
        System.out.println(course);
        System.out.println("-----------------------");
        System.out.println(course.getTeachers());
    }


    @Autowired
    private TeacherDao teacherDao;

    @org.junit.Test
    public void test12() {
        Teacher teacher = teacherDao.findBytId(1);
        System.out.println(teacher);
        System.out.println("-----------------------");
        System.out.println(teacher.getCourses());
    }


    //    会话缓存
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @org.junit.Test
    public void test13() {
        getsqlsession();
//        System.out.println("----------------");
//        sqlSession = sqlSessionFactory.openSession();
//        emps = empServices.findbyCondition(emp);
//        emps.forEach(emp1 -> System.out.println(emp1));
//        sqlSession.close();
    }

    private void getsqlsession() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Emp emp = new Emp();
        emp.setEid(1);
        List<Emp> emps = empServices.findbyCondition(emp);
        emps.forEach(emp1 -> System.out.println(emp1));
        sqlSession.close();
    }


    @org.junit.Test
    public void test16() {
        getsqlsession();
        System.out.println("------------------");
        getsqlsession();
    }


    @org.junit.Test
    public void test15() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpDao mapper = sqlSession.getMapper(EmpDao.class);

        int count = mapper.getCount();
        System.out.println(count);
        System.out.println("---------------------------");
        int count2 = mapper.getCount();
        System.out.println(count2);
    }

    @org.junit.Test
    public void test17() {
//        1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

//        2.创建HttpGet对象
        String uri="http://10.3.144.25:8080/day52/stu/15";
        HttpGet httpGet=new HttpGet(uri);

//         // 3、设置参数
//        // 设置长连接
        httpGet.setHeader("Connection", "keep-alive");
//        // 设置代理（模拟浏览器版本）
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
//        // 设置 Cookie
        httpGet.setHeader("Cookie", "UM_distinctid=16442706a09352-0376059833914f-3c604504-1fa400-16442706a0b345; CNZZDATA1262458286=1603637673-1530123020-%7C1530123020; JSESSIONID=805587506F1594AE02DC45845A7216A4");



        try {
            //    4、执行请求并获得响应对象
            HttpResponse httpResponse = httpClient.execute(httpGet);
//            获得响应结果
            HttpEntity entity = httpResponse.getEntity();
            String s = EntityUtils.toString(entity);
            System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpClient!=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
