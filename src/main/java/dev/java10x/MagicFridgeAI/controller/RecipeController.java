package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.services.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final ChatGptService chatGptService;

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generatRecipe(){
        return chatGptService.genereteRecipe()
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}