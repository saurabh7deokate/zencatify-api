package com.zencatify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zencatify.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public abstract User findByUserName(String userName);

}
