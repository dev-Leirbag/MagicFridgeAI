package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.services.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    //GET
    @GetMapping("/listar")
    public ResponseEntity<List<FoodItem>> listar(){
        List<FoodItem> foodItems = foodItemService.listar();
        return ResponseEntity.ok(foodItems);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Optional<FoodItem>> listaPorId(@PathVariable Long id){
        return ResponseEntity.ok(foodItemService.buscarPorId(id));
    }

    //POST
    @PostMapping("/criar")
    public ResponseEntity<FoodItem> criar(@RequestBody FoodItem foodItem){
        FoodItem salvo = foodItemService.salvar(foodItem);
        return ResponseEntity.ok(salvo);
    }

    //UPDATE
    @PutMapping("/alterar/{id}")
    public ResponseEntity<FoodItem> alterar(@PathVariable Long id, @RequestBody FoodItem foodItem){
        return ResponseEntity.ok(foodItemService.alterarPorId(id, foodItem));
    }

    //DELETE
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        foodItemService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
