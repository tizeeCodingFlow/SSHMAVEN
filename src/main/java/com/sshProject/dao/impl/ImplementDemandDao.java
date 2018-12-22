package com.sshProject.dao.impl;
import com.sshProject.dao.DemandDao;
import com.sshProject.entity.Employee;
import com.sshProject.entity.Training;
import com.sshProject.entity.TrainingDemand;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;

@Repository
@Transactional
public class ImplementDemandDao implements DemandDao
{
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SessionFactory sessionFactory;
    public  void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory=sessionFactory;
    }
    public boolean addDemand(TrainingDemand demand)
    {
        try{
            sessionFactory.getCurrentSession().save(demand);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    public boolean updateDemand(TrainingDemand demand)
    {
        String hql="Update TrainingDemand td set td.demandName=?,td.employeeIndex=?,td.description=?,td.demandStatus=? where demandIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(demand.getDemandName()));
        query.setString(1,String.valueOf(demand.getEmployeeIndex()));
        query.setString(2,String.valueOf(demand.getDescription()));
        query.setString(3,String.valueOf(demand.getDemandStatus()));
        query.setString(4,String.valueOf(demand.getDemandIndex()));
        return (query.executeUpdate()>0);
    }
    public  boolean deleteDemand(int demandIndex)
    {
        String hql="delete TrainingDemand td where td.demandIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(demandIndex));
        return  (query.executeUpdate()>0);
    }
    /*public ArrayList<TrainingDemand> getDemands(int employeeId)
    {
        String hql="form TrainingDemand td where td.employeeId=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(employeeId));
        return (ArrayList<TrainingDemand>)query.list();
    }*/
    public ArrayList<TrainingDemand> getAllDemands()
    {
        String hql="from TrainingDemand";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        return  (ArrayList<TrainingDemand>)query.list();
    }

    public  TrainingDemand getDemand(int employeeIndex)
    {
        String hql="from TrainingDemand td where td.employeeIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(employeeIndex));
        return (TrainingDemand)query.uniqueResult();
    }

    public Employee getEmployee(int employeeIndex)
    {
        String hql="from Employee e where e.employeeIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(employeeIndex));
        return (Employee) query.uniqueResult();
    }
}