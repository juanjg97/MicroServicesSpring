package com.bosonit.api.gateway.config;

import com.bosonit.api.gateway.dto.TokenDto;
import com.bosonit.api.gateway.dto.TokenInputDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>  {

    private WebClient.Builder webClient;

    public AuthFilter(WebClient.Builder webClient){
        super(Config.class);
        this.webClient = webClient;
    }


    /*
    * Este código define una función apply() en Java que implementa un método de una interfaz . El propósito de este código es aplicar un filtro en un gateway para validar el token de autenticación en una solicitud HTTP antes de permitir que la solicitud pase a través del gateway.

Explicación paso a paso del código:

La función apply() toma como argumento un objeto Config y devuelve un objeto GatewayFilter.

Se crea un filtro GatewayFilter utilizando una expresión lambda que toma dos argumentos: exchange y chain. exchange es un objeto que contiene información sobre la solicitud y la respuesta HTTP, mientras que chain es el objeto que permite continuar con el siguiente filtro en la cadena de filtros.

Se verifica si la cabecera Authorization está presente en la solicitud HTTP. Si no lo está, se llama a la función onError() y se devuelve un error HttpStatus.BAD_REQUEST.

Si la cabecera Authorization está presente, se extrae su valor y se divide en dos partes utilizando el espacio como separador. Se espera que la cabecera tenga el formato "Bearer {token}".

Si la cabecera no tiene exactamente dos partes o la primera parte no es igual a "Bearer", se llama a la función onError() y se devuelve un error HttpStatus.BAD_REQUEST.

Se utiliza un cliente web para realizar una solicitud POST a un servicio de autenticación (http://auth-service/auth/validate) con el token como parámetro.

Se crea un objeto TokenInputDto con la ruta de la solicitud y el método HTTP, y se pasa como cuerpo de la solicitud POST.

La respuesta del servicio de autenticación se espera que sea un objeto TokenDto. Se utiliza el operador map para extraer el token del objeto TokenDto y continuar con el objeto exchange.

Se llama al siguiente filtro en la cadena utilizando el método flatMap y chain::filter.

En resumen, este código define un filtro de gateway que verifica si una solicitud HTTP contiene un token de autenticación válido en su cabecera Authorization. Si el token es válido, la solicitud continúa a través del gateway. Si no lo es, se devuelve un error HttpStatus.BAD_REQUEST.
    * */
    @Override
    public GatewayFilter apply(Config config) {
        return ((((exchange, chain) -> {
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(exchange, HttpStatus.BAD_REQUEST);
            String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] chunks = tokenHeader.split(" ");
            if(chunks.length != 2 || !chunks[0].equals("Bearer"))
                return onError(exchange,HttpStatus.BAD_REQUEST);
            return webClient.build()
                    .post()
                    .uri("http://auth-service/auth/validate?token=" + chunks[1])
                    .bodyValue(new TokenInputDto(exchange.getRequest().getPath().toString(),exchange.getRequest().getMethod().toString()))
                    .retrieve()
                    .bodyToMono(TokenDto.class)
                    .map(t -> {
                        t.getToken();
                        return exchange;
                    }).flatMap(chain::filter);
        })));
    }

    public Mono<Void> onError(ServerWebExchange exchange,HttpStatus httpStatus){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    public static class Config{

    }
}
