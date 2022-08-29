package com.almc.liga1.lambda.reader.webscraping;

import com.almc.liga1.lambda.reader.webscraping.model.Tabla;

import java.io.IOException;

public interface WebScrappingService {

    public Tabla readTablaLiga1(String url) throws IOException;

}
