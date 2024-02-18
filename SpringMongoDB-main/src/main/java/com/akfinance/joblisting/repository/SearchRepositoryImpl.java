package com.akfinance.joblisting.repository;

import com.akfinance.joblisting.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository {

	@Autowired
	MongoClient client;

	@Autowired
	MongoConverter converter;
	
	  @Override
	  public List<Post> findByText(String searchText) {
		     final List<Post> posts = new ArrayList<>();

			MongoDatabase database = client.getDatabase("testdb");
			MongoCollection<Document> collection1 = database.getCollection("JobPost");
			
			System.out.println("hello");

	        // Create a search query
	        Document searchQuery = new Document("$text", new Document("$search", searchText));

	        // Search the collection
	        FindIterable<Document> findIterable = collection1.find(searchQuery);

	        // Iterate over the search results
	        for (Document document : findIterable) {
	            posts.add(converter.read(Post.class,document));
	        }

	        return posts;
	    }

//	@Override
//	public List<Post> findByText(String text) {
//
//		
//
////		AggregateIterable<Document> result = collection
////				.aggregate(Arrays.asList(
////						new Document("$search",
////								new Document("index", "default").append("text",
////										new Document("query", text).append("path",
////												Arrays.asList("profile", "desc", "techs")))),
////						new Document("$sort", new Document("exp", 1L))));
////		result.forEach(doc -> posts.add(converter.read(Post.class, doc)));
//
////		return posts;
//		
//
//
//	}
}
