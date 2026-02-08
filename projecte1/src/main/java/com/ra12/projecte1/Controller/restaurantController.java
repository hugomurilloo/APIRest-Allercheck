package com.ra12.projecte1.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.dto.restaurantRequestDTO;
import com.ra12.projecte1.dto.restaurantResponseDTO;
import com.ra12.projecte1.model.restaurantModel;
import com.ra12.projecte1.service.restaurantService;

@RestController
@RequestMapping("/api")
public class restaurantController {

    @Autowired
    restaurantService restaurantService;

    /**
     * [b1] - Crear nou restaurant
     * Endpoint para crear un nuevo restaurante desde JSON
     * Recibe restaurantRequestDTO y lo convierte a Model
     */
    @PostMapping("/restaurant")
    public ResponseEntity<String> createRestaurant(@RequestBody restaurantRequestDTO requestDTO) {
        try{
            // Convertir DTO a Model
            restaurantModel restaurant = new restaurantModel(
                requestDTO.getName(),
                requestDTO.getRating(),
                requestDTO.getIsVerified(),
                requestDTO.getSenseGluten(),
                requestDTO.getSenseLactosa(),
                requestDTO.getSenseFruitsSecs(),
                requestDTO.getSenseMarisc(),
                requestDTO.getEsVega(),
                requestDTO.getEsHalal(),
                requestDTO.getEsKosher(),
                requestDTO.getTeOu(),
                requestDTO.getTipusCuina(),
                requestDTO.getRangPreu(),
                requestDTO.getUbicacio(),
                requestDTO.getTelefon()
            );
            
            ResponseEntity<String> result = restaurantService.save(restaurant);
            return result;
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Restaurant no creat: " + e.getMessage());
        }       
    }
    
    /**
     * [c1] - Llistar todos los restaurants
     * Retorna lista de restaurantes en formato ResponseDTO
     */
    @GetMapping("/restaurant")
    public ResponseEntity<List<restaurantResponseDTO>> getRestaurants() {
        try{
            ResponseEntity<List<restaurantModel>> result = restaurantService.findAll();
            
            if (result.getStatusCode().is2xxSuccessful() && result.getBody() != null) {
                // Convertir lista de Model a lista de ResponseDTO
                List<restaurantResponseDTO> responseDTOs = result.getBody().stream()
                    .map(restaurantResponseDTO::new)
                    .collect(Collectors.toList());
                
                return ResponseEntity.ok(responseDTOs);
            } else {
                return ResponseEntity.status(result.getStatusCode()).body(List.of());
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of());
        }
    }

    /**
     * [d1] - Llistar restaurant per ID
     * Retorna un restaurante en formato ResponseDTO
     */
    @GetMapping("/restaurants/{restaurant_id}")
    public ResponseEntity<restaurantResponseDTO> getRestaurantById(@PathVariable Long restaurant_id) {
        try{
            Optional<restaurantModel> restaurant = restaurantService.findById(restaurant_id);
            
            if (restaurant.isPresent()) {
                // Convertir Model a ResponseDTO
                restaurantResponseDTO responseDTO = new restaurantResponseDTO(restaurant.get());
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    /**
     * [a1] - Crear restaurants desde CSV
     * Endpoint para subir archivo CSV con múltiples restaurantes
     */
    @PostMapping("/restaurants/upload-csv")
    public ResponseEntity<String> createRestaurantsByCsv(@RequestParam MultipartFile csvFile) {
        try {
            ResponseEntity<String> result = restaurantService.createByCsv(csvFile);
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Restaurants no creats: " + e.getMessage());
        }
    }
    
    /**
     * [c2] - Eliminar todos los registres
     * Endpoint para eliminar todos los restaurantes
     */
    @DeleteMapping("/restaurants")
    public ResponseEntity<String> deleteAll() {
        try {
            restaurantService.deleteAll();
            return ResponseEntity.ok("Todos los restaurantes eliminados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                               .body("Error eliminando restaurantes: " + e.getMessage());
        }
    }
}