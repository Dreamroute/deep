package com.github.dreamroute.deep.repository;

import com.github.dreamroute.deep.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long> {

    List<User> findByName(String name);

}
