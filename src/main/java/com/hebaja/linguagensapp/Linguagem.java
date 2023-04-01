package com.hebaja.linguagensapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "principaisLinguagens")
public class Linguagem {
	
	@Id
	private String id;
	private String title;
	private String image;
	private int rating;
	
	public Linguagem(String title, String image, int rating) {
		this.title = title;
		this.image = image;
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public String getImage() {
		return image;
	}

	public int getRating() {
		return rating;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}