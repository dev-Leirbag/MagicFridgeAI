package dev.java10x.MagicFridgeAI.services;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItem salvar(FoodItem foodItem){
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> listar(){
        return foodItemRepository.findAll();
    }

    public Optional<FoodItem> buscarPorId(Long id){
        return foodItemRepository.findById(id);
    }

    public FoodItem alterarPorId(Long id, FoodItem foodItem){
        foodItemRepository.findById(id)
                .map(itemExistente -> {
                    foodItem.setId(itemExistente.getId());
                    var atualizado = foodItemRepository.save(foodItem);
                    return atualizado;
                });
        return null;
    }

    public void deletarPorId(Long id){
        foodItemRepository.deleteById(id);
    }
}
