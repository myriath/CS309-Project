package com.requests.backend.models;

import org.springframework.data.repository.CrudRepository;

import com.requests.accessingdatamysql.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<com.requests.models.User, Integer> {

}