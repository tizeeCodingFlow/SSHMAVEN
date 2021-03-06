package com.sshProject.dao.impl;

import com.sshProject.dao.RoleDao;
import com.sshProject.entity.Employee;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public class ImplementRoleDao implements RoleDao
{
    @Autowired
    private SessionFactory sessionFactory;
    public  void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory=sessionFactory;
    }
    public boolean addModule(int employeeIndex,int moduleIndex)
    {
        //??
    }
    public boolean removeModule(int employeeIndex,int moduleIndex)
    {
        //??
    }
    public  boolean addEmployee(Employee employee)
    {
        String hql="update Employee e set e.identicalNumber=?,e.realName=?,e.address=? where employeeIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,employee.getIdenticalNumber());
        query.setString(1,employee.getRealName());
        query.setString(2,employee.getAddress());
        query.setString(3,String.valueOf(employee.getEmployeeIndex()));
        return (query.executeUpdate()>0);
    }
    public  boolean deleteEmployee(int employeeIndex)
    {
        String hql="delete Employee e where e.employeeIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(employeeIndex));
        return (query.executeUpdate()>0);
    }
    public ArrayList<Employee> getEmployees()
    {
        String hql="form Employee";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        return (ArrayList<Employee>)query.list();
    }
}
