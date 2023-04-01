package com.hebaja.linguagensapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LinguagemController {

	@Autowired
	private LinguagemRepository repositorio;

	@GetMapping("/linguagens")
	public List<LinguagemDto> obterLinguagens() {
		List<Linguagem> linguagens = repositorio.findByOrderByRatingDesc();
		return LinguagemDto.convertList(linguagens);
	}

	@PostMapping("/linguagens")
	public ResponseEntity<LinguagemDto> cadastrarLinguagem(@RequestBody Linguagem linguagem) {
		Linguagem linguagemSalva = repositorio.save(linguagem);
		return new ResponseEntity<>(new LinguagemDto(linguagemSalva), HttpStatus.CREATED) ;
	}

	@GetMapping("/linguagens/{id}")
	public ResponseEntity<LinguagemDto> obterLinguagem(@PathVariable("id") String id) {
		Optional<Linguagem> optionalLinguagem = repositorio.findById(id);
		if(optionalLinguagem.isPresent()) return ResponseEntity.ok(new LinguagemDto(optionalLinguagem.get()));
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/linguagens/{id}")
	public Linguagem atualizaLinguagem(@PathVariable("id") String id, @RequestBody Linguagem linguagemEnviada) {
		Linguagem linguagemNoBanco = repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		linguagemNoBanco.setTitle(linguagemEnviada.getTitle());
		linguagemNoBanco.setImage(linguagemEnviada.getImage());
		linguagemNoBanco.setRating(linguagemEnviada.getRating());
		return repositorio.save(linguagemNoBanco);
	}

	@DeleteMapping("/linguagens/{id}")
	public void deletaLinguagem(@PathVariable("id") String id) {
		repositorio.deleteById(id);
	}

}
