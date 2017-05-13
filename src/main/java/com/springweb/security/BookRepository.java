package com.springweb.security;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, String>{

}