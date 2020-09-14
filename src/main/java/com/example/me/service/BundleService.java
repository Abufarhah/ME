package com.example.me.service;

import com.example.me.Bundle;
import com.example.me.repository.BundleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BundleService {
	private static final Logger log = LoggerFactory.getLogger(BundleService.class);

	@Autowired
	BundleRepository bundleRepository;

	@PostConstruct
	public void initData() {
	}

	public Bundle getBundle(int id) {
		log.info("service to get bundle: " + id);
		try{
			Bundle bundle=bundleRepository.findOne(id);
			return bundle;
		}catch (Exception e){
			return null;
		}
	}

	public String addBundle(int id, String name, double price) {
		log.info("service to add bundle: " + id);
		try{
			if(bundleRepository.exists(id)){
				return "Bundle with id: "+id+" is already exists";
			}
			Bundle bundle=new Bundle();
			bundle.setId(id);
			bundle.setName(name);
			bundle.setPrice(price);
			bundleRepository.save(bundle);
			return "Bundle with id: "+id+" added successfully";
		}catch (Exception e){
			return "Bundle with id: "+id+" error in adding to database";
		}
	}

	public String deleteBundle(int id) {
		log.info("service to delete bundle: " + id);
		try {
			if(!bundleRepository.exists(id)){
				return "Bundle with id: "+id+" doesn't exists";
			}
			bundleRepository.delete(id);
			return "Bundle with id: "+id+" deleted successfully";
		}catch (Exception e){
			return "Bundle with id: "+id+" error in deleting from the database";
		}
	}
}
