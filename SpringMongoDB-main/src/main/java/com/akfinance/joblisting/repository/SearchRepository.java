package com.akfinance.joblisting.repository;

import java.util.List;

import com.akfinance.joblisting.model.Post;

public interface SearchRepository {

    List<Post> findByText(String text);

}
