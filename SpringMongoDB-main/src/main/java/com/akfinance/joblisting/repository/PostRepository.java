package com.akfinance.joblisting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.akfinance.joblisting.model.Post;

public interface PostRepository extends MongoRepository<Post,String>
{

}
