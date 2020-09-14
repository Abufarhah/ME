package com.example.me.repository;

import com.example.me.Bundle;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundleRepository extends AerospikeRepository<Bundle,Integer> {

}
