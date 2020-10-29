package com.neelav.springbootcassandra.repository;

import java.util.List;
import java.util.Optional;

import com.neelav.springbootcassandra.models.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CassandraRepository<User, Integer> {

    @AllowFiltering
    Optional<List<User>> findByAgeGreaterThan(int age);

}
