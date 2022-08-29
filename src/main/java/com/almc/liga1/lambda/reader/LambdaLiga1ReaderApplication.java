package com.almc.liga1.lambda.reader;

import com.almc.liga1.lambda.reader.handler.Request;
import com.almc.liga1.lambda.reader.handler.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.SerializationHint;

@SerializationHint(types = {Request.class, Response.class})
@SpringBootApplication
public class LambdaLiga1ReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LambdaLiga1ReaderApplication.class, args);
	}

}
