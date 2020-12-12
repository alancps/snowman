package com.snowman.touristspot.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Erro {
	private String userMsg;
	private String developerMsg;
}
