package com.po;

import java.util.HashSet;
import java.util.Set;

public class Dept {
    private Integer did;
    private String name;
    private String addres;
    private Set<Emp> emps=new HashSet<>();

    public Set<Emp> getEmps() {
        return emps;
    }

    public void setEmps(Set<Emp> emps) {
        this.emps = emps;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "did=" + did +
                ", name='" + name + '\'' +
                ", addres='" + addres + '\'' +
                '}';
    }
}
