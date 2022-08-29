package com.almc.liga1.lambda.reader.webscraping.impl;

import com.almc.liga1.lambda.reader.webscraping.WebScrappingService;
import com.almc.liga1.lambda.reader.webscraping.model.Equipo;
import com.almc.liga1.lambda.reader.webscraping.model.Estadistica;
import com.almc.liga1.lambda.reader.webscraping.model.Posicion;
import com.almc.liga1.lambda.reader.webscraping.model.Tabla;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class HtmlUnitWebScrappingService implements WebScrappingService {
    @Override
    public Tabla readTablaLiga1(String url) throws IOException {

        HtmlPage page = preparePage( url );

        DomElement domElement = page.getElementById("block-system-main");
        DomElement table = findFirstDomElementByType(domElement, "table");

        //Get and process THead to get header list
        List<String> headersList = processTHead( table.getFirstElementChild() );

        //Get and process TBody
        Tabla tabla = processTBody( table.getLastElementChild(), headersList );


        return tabla;
    }


    public static Tabla processTBody(DomElement tBody, List<String> headerList ) {
        Tabla tabla = new Tabla();
        tabla.setFecha( new Date().toString() );
        tabla.setTabla( new ArrayList<>() );

        //System.out.println( tBody.getChildElementCount() );
        Iterable<DomElement> tRows = tBody.getChildElements();
        tRows.forEach( element -> {
            Posicion posicion = processRow(element, headerList);
            tabla.getTabla().add(posicion);
        } );

        return tabla;
    }

    public static Posicion processRow(DomElement row, List<String> headerList ) {

        Posicion posicion = new Posicion();
        posicion.setEquipo( new Equipo());
        Estadistica estadistica = new Estadistica();
        posicion.getEquipo().setEstadistica( estadistica );
        Iterator<DomElement> celdas = row.getChildElements().iterator();

        int i = 0;

        while ( celdas.hasNext() ) {
            DomElement td = celdas.next();
            if ( !"".equals( headerList.get(i) ) ) {
                String text = td.getTextContent()!=null ? td.getTextContent().trim() : "";
                switch ( headerList.get(i) ) {
                    case "Pos":
                        posicion.setOrden( text.length()>0 ? Integer.valueOf( text ): -1 );
                        break;
                    case "Equipo":
                        posicion.getEquipo().setNombre( text );
                        break;
                    case "PTS":
                        estadistica.setPuntos( Integer.valueOf( text ) );
                        break;
                    case "PJ":
                        estadistica.setPartidosJugados( Integer.valueOf( text ) );
                        break;
                    case "PG":
                        estadistica.setPartidosGanados( Integer.valueOf( text ) );
                        break;
                    case "PE":
                        estadistica.setPartidosEmpatados( Integer.valueOf( text ) );
                        break;
                    case "PP":
                        estadistica.setPartidosPerdidos( Integer.valueOf( text ) );
                        break;
                    case "GF":
                        estadistica.setGolesFavor( Integer.valueOf( text ) );
                        break;
                    case "GC":
                        estadistica.setGolesContra( Integer.valueOf( text ) );
                        break;
                    case "DG":
                        estadistica.setGolesDiferencia( Integer.valueOf( text ) );
                        break;
                }

            }
            i++;
        }

        return posicion;
    }


    private HtmlPage preparePage( String url ) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage page = webClient.getPage(url);

        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getCurrentWindow().getJobManager().removeAllJobs();
        webClient.close();

        return page;
    }

    public static List<String> processTHead( DomElement tHead ) {
        //Get First TR with headers name
        DomElement headerRow = tHead.getFirstElementChild();
        Iterable<DomElement> headers = headerRow.getChildElements();

        List<String> orderHeaders = new ArrayList<>();
        headers.forEach( header -> {
            orderHeaders.add( header.getTextContent()!=null ? header.getTextContent().trim() : "" );
        } );

        return orderHeaders;
    }


    private DomElement findFirstDomElementByType(DomElement domElement, String nodeType) {

        if (  nodeType.equals( domElement.getNodeName() ) ) {
            return domElement;
        }else {
            if( domElement.getChildElementCount() > 0 ) {
                Iterator<DomElement> it =  domElement.getChildElements().iterator();
                do  {
                    return findFirstDomElementByType( it.next(), "table" );
                }while ( it.hasNext() );
            }else {
                return null;
            }

        }

    }

}
