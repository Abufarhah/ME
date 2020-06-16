package com.example.me.dao;

import com.aerospike.client.*;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.ScanPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.example.me.Bundle;
import org.springframework.stereotype.Component;

import static com.example.me.constants.Constants.*;

@Component
public class BundleDao {
    private static AerospikeClient aerospikeClient=new AerospikeClient(new ClientPolicy(), AEROSPIKE_SERVER_HOST_NAME, AEROSPIKE_SERVER_PORT);
    private Policy policy=new Policy();
    private WritePolicy writePolicy=new WritePolicy();
    private ScanPolicy scanPolicy=new ScanPolicy();

    public Bundle getBundle(int id){
        try {
            Key key = new Key(AEROSPIKE_SERVER_NAMESPACE, AEROSPIKE_SERVER_ME_SETNAME, "k" + id);
            Record record = aerospikeClient.get(scanPolicy, key);
            Bundle bundle = new Bundle();
            bundle.setId(record.getInt(AEROSPIKE_SERVER_BIN_ID));
            bundle.setName(record.getString(AEROSPIKE_SERVER_BIN_NAME));
            bundle.setPrice(record.getDouble(AEROSPIKE_SERVER_BIN_PRICE));
            return bundle;
        } catch (Exception e) {
            return null;
        }
    }

    public String addBundle(int id,String name,double price){
        try {
            Key key = new Key(AEROSPIKE_SERVER_NAMESPACE, AEROSPIKE_SERVER_ME_SETNAME, "k" + id);
            if (aerospikeClient.exists(policy, key)) {
                return "Bundle with id: "+id+" is already exists";
            }
            Bin binId = new Bin(AEROSPIKE_SERVER_BIN_ID, id);
            Bin binName = new Bin(AEROSPIKE_SERVER_BIN_NAME, name);
            Bin binPrice = new Bin(AEROSPIKE_SERVER_BIN_PRICE, price);
            aerospikeClient.add(writePolicy, key, binId, binName, binPrice);
            return "Bundle with id: "+id+" added successfully";
        } catch (AerospikeException e) {
            return "Bundle with id: "+id+" error in adding to database";
        }
    }

    public String deleteBundle(int id){
        try {
            Key key = new Key(AEROSPIKE_SERVER_NAMESPACE, AEROSPIKE_SERVER_ME_SETNAME, "k" + id);
            if (!aerospikeClient.exists(policy, key)) {
                return "Bundle with id: "+id+" doesn't exists";
            }
            aerospikeClient.delete(writePolicy, key);
            return "Bundle with id: "+id+" deleted successfully";
        } catch (AerospikeException e) {
            return "Bundle with id: "+id+" error in deleting from the database";
        }
    }

}
