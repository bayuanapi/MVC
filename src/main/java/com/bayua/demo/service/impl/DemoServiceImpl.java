package com.bayua.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayua.demo.entity.M_EMPLOYEE;
import com.bayua.demo.entity.M_POSITION;
import com.bayua.demo.entity.V_EMPLOYEE;
import com.bayua.demo.service.DemoService;
import com.google.gson.Gson;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	SessionFactory sfOA;

	public void setSessionFactoryOA(SessionFactory sessionFactory) {
		this.sfOA = sessionFactory;
	}

	// function to get all employee data
	@Override
	public List<V_EMPLOYEE> getAllVEmp() {
		List<V_EMPLOYEE> list = new ArrayList<V_EMPLOYEE>();
		Session session = sfOA.openSession();
		try {
			list = session.createQuery("from V_EMPLOYEE order by id desc").list();
		} finally {
			session.close();
		}
		return list;
	}

	// function to get all employee possition
	@Override
	public List<M_POSITION> getListPosition() {
		Session session = sfOA.openSession();
		List<M_POSITION> list = new ArrayList<M_POSITION>();
		try {
			list = session.createQuery("from M_POSITION").list();
		} finally {
			session.close();
		}
		return list;
	}

	// function to add object
	public Boolean addObject(Object object) {
		Session session = sfOA.openSession();
		try {
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			System.out.println(e);
			System.out.println(new Gson().toJson(object));
			session.close();
			return false;
		}

	}

	// function to delete object
	public boolean deleteObject(Object object, String docNo) {
		Session session = sfOA.openSession();
		try {
			session.beginTransaction();
			session.load(object, docNo);
			session.delete(object);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			System.out.println(e);
			session.close();
			return false;
		}

	}

	// function to execute query for map
	public List<Map<String, Object>> getMapQuery(String q) {
		Session session = sfOA.openSession();
		Query query = session.createSQLQuery(q);

		System.out.println(query.getQueryString());
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		List<Map<String, Object>> aliasToValueMapList = new ArrayList<Map<String, Object>>();
		aliasToValueMapList = query.list();
		session.close();

		return aliasToValueMapList;
	}

	// function to add object
	@Override
	public M_EMPLOYEE getMEmpById(String id) {
		Session session = sfOA.openSession();
		List<M_EMPLOYEE> employee = new ArrayList<M_EMPLOYEE>();
		try {
			employee = session.createQuery("from M_EMPLOYEE where id = " + id).list();
			return employee.get(0);
		} finally {
			session.close();
		}
	}

	// function to delete employee object
	@Override
	public void deleteEmployee(M_EMPLOYEE emp) {
		Session session = sfOA.openSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery("delete from M_EMPLOYEE where id = " + emp.getId());
			System.out.println(query.getQueryString());
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
		}
	}

	// function to add or update object
	@Override
	public void saveUpdateObject(Object object) {
		Session session = sfOA.openSession();
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			session.close();
		}

	}

}
