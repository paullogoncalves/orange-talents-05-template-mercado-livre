package com.oragentalent5.mercadolivre.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadFake {

	public Set<String> send(List<MultipartFile> imagens){
		return imagens.stream().map(imagem -> "http://bucket.io"+ 
				imagem.getOriginalFilename()).collect(Collectors.toSet());
	}
}
