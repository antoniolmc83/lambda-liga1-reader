package com.almc.liga1.lambda.reader.webscraping;

import com.almc.liga1.lambda.reader.webscraping.impl.HtmlUnitWebScrappingService;
import com.almc.liga1.lambda.reader.webscraping.model.Tabla;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WebScrappingServiceTest {



    @Test
    public void webScrappingTest(){
        String url = "https://league.ovacion.pe/positioning_table/90+76";

        WebScrappingService wsService = new HtmlUnitWebScrappingService();

        try {
            Tabla tb = wsService.readTablaLiga1(url);

            ObjectMapper mapper = new ObjectMapper();
            //mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            String result = mapper.writeValueAsString(tb);

            System.out.println( result );


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
