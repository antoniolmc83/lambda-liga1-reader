package com.almc.liga1.lambda.reader.handler;


import com.almc.liga1.lambda.reader.webscraping.WebScrappingService;
import com.almc.liga1.lambda.reader.webscraping.model.Tabla;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Function;

@Component
public class ReadTablaAcumulada implements Function<Request, Response> {

    private WebScrappingService webScrappingService;

    public ReadTablaAcumulada(WebScrappingService webScrappingService) {
        this.webScrappingService = webScrappingService;
    }

    @Override
    public Response apply(Request s) {

        String url = "https://league.ovacion.pe/positioning_table/90+7";

        Response response = new Response();

        try {
            Tabla tabla = null;
            System.out.println("Start reading table");
            tabla = webScrappingService.readTablaLiga1( url );
            System.out.println("Finished reading table");

            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(tabla);

            response.setStatus("Success");
            response.setMessage(result);

        } catch (IOException e) {
            System.out.println("Error");
            response.setMessage(e.getMessage());
            response.setStatus("Error");
            e.printStackTrace();
        }



        return response;
    }
}
