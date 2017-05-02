package com.fileapi.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fileapi.entity.FileUploaded;

@Component
public class FileDAOImpl implements FileDAO {

	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public FileUploaded findByName(String name) {
		String hql = "select f from FileUploaded f where f.name = :name";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("name", name);
		return (FileUploaded)query.uniqueResult();
	}

	@Override
	public boolean isFileExist(String name) {
		return findByName(name) != null;
	}

	@Override
	public void saveFile(FileUploaded metadata) { 
		sessionFactory.getCurrentSession().persist(metadata);
		System.out.println("--------------------------" + metadata);
	}

}