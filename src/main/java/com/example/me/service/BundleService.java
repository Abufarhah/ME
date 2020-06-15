package com.example.me.service;

import com.example.me.Bundle;
import com.example.me.dao.BundleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BundleService {

	@Autowired
	BundleDao bundleDao;

	@PostConstruct
	public void initData() {
	}

	public Bundle getBundle(int id) {
		return bundleDao.getBundle(id);
	}

	public String addBundle(int id, String name, double price) {
		return bundleDao.addBundle(id,name,price);
	}

	public String deleteBundle(int id) {
		return bundleDao.deleteBundle(id);
	}
}
