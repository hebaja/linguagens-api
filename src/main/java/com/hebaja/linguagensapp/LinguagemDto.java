package com.hebaja.linguagensapp;

import java.util.List;
import java.util.stream.Collectors;

public class LinguagemDto {

	private String id;
	private String title;
	private String image;
	private String rating;

	public LinguagemDto(Linguagem linguagem) {
		this.id = linguagem.getId();
		this.title = linguagem.getTitle();
		this.image = linguagem.getImage();
		this.rating = String.valueOf(linguagem.getRating());
	}

	public static List<LinguagemDto> convertList(List<Linguagem> linguagens) {
		return linguagens.stream().map(LinguagemDto::new).collect(Collectors.toList());
	}

	public String getTitle() {
		return title;
	}

	public String getImage() {
		return image;
	}

	public String getRating() {
		return rating;
	}

	public String getId() {
		return this.id;
	}

}
