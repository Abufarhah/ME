package com.example.me.service;

import com.example.me.Bundle;
import com.example.me.dao.BundleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BundleService {
	private static final Logger log = LoggerFactory.getLogger(BundleService.class);

	@Autowired
	BundleDao bundleDao;

	@PostConstruct
	public void initData() {
	}

	public Bundle getBundle(int id) {
		log.info("service to get bundle: " + id);

		return bundleDao.getBundle(id);
	}

	public String addBundle(int id, String name, double price) {
		log.info("service to add bundle: " + id);

		return bundleDao.addBundle(id,name,price);
	}

	public String deleteBundle(int id) {
		log.info("service to delete bundle: " + id);

		return bundleDao.deleteBundle(id);
	}
}
