package com.example.me;

import com.aerospike.client.*;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.ScanPolicy;
import com.aerospike.client.policy.WritePolicy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BundleService {

	ClientPolicy cpolicy = new ClientPolicy();
	WritePolicy wpolicy = new WritePolicy();
	Policy policy = new Policy();
	private AerospikeClient client = new AerospikeClient(cpolicy, "172.28.128.3", 3000);
	private List<Bundle> list = new ArrayList<Bundle>();

	@PostConstruct
	public void initData() {
	}

	public Bundle getBundle(int id) {
		Bundle temp;
		try {
			Key key = new Key("test", "mebundles", "k" + id);
			ScanPolicy policy = new ScanPolicy();
			Record record = client.get(policy, key);
			temp = new Bundle();
			temp.setId(record.getInt("id"));
			temp.setName(record.getString("name"));
			temp.setPrice(record.getDouble("price"));
			return temp;
		} catch (Exception e) {
			return null;
		}
	}

	public String addBundle(int id, String name, double price) {
		try {

			Key key = new Key("test", "mebundles", "k" + id);
			if (client.exists(policy, key)) {
				return "Bundle with id: "+id+" is already exists";
			}
			wpolicy.setTimeout(50);
			Bin bin1 = new Bin("id", id);
			Bin bin2 = new Bin("name", name);
			Bin bin3 = new Bin("price", price);
			client.put(wpolicy, key, bin1, bin2, bin3);
			return "Bundle with id: "+id+" added successfully";
		} catch (AerospikeException e) {
			return "Bundle with id: "+id+" error in adding to database";
		}
	}

	public String deleteBundle(int id) {
		try {
			wpolicy.setTimeout(50);
			Key key = new Key("test", "mebundles", "k" + id);
			if (!client.exists(policy, key)) {
				return "Bundle with id: "+id+" doesn't exists";
			}
			client.delete(wpolicy, key);
			return "Bundle with id: "+id+" deleted successfully";
		} catch (AerospikeException e) {
			return "Bundle with id: "+id+" error in deleting from the database";
		}
	}
}
