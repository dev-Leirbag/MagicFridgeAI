package dev.java10x.MagicFridgeAI.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatGptService {

    private final WebClient webClient;

    @Value("${api_key}")
    private String apiKey;

    public Mono<String> genereteRecipe(){
        String prompt = "Me sugira uma receita simples com ingredientes comuns porem sou alergico a massa e alho";

        Map<String, Object> body = Map.of(
                "model", "gpt-5.4-nano",
                "input", List.of(
                        Map.of("role", "system", "content", "Você é um assistente que cria receitas"),
                        Map.of("role", "user", "content", prompt)
                )
        );

        return webClient.post()
                .uri("https://api.openai.com/v1/responses")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var output = (List<Map<String, Object>>) response.get("output");

                    if (output != null && !output.isEmpty()) {
                        var content = (List<Map<String, Object>>) output.get(0).get("content");

                        if (content != null && !content.isEmpty()) {
                            return content.get(0).get("text").toString();
                        }
                    }
                    return "Desculpe, não consegui gerar uma receita no momento.";
                });

    }

}
