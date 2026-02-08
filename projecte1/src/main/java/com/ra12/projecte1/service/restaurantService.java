package com.ra12.projecte1.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.Logging.CustomLogging;
import com.ra12.projecte1.model.restaurantModel;
import com.ra12.projecte1.repository.restaurantRepository;

@Service
public class restaurantService {
    
    @Autowired
    restaurantRepository restaurantRepository;
    
    @Autowired
    private CustomLogging customLogging;


    public ResponseEntity<String> save(restaurantModel restaurantModel){
        try {
            // Log de inicio
            customLogging.logInfo("restaurantService", "save", 
                                "Guardando nuevo restaurante: " + restaurantModel.getName());
            
            restaurantRepository.save(restaurantModel);
            
            // Log de exit
            customLogging.logInfo("restaurantService", "save", 
                                "Restaurante guardado exitosamente: " + restaurantModel.getName());
            
            return ResponseEntity.ok("Restaurant creat correctament");
            
        } catch (Exception e) {
            // Log de error
            customLogging.logError("restaurantService", "save", 
                                 "Error guardando restaurante: " + restaurantModel.getName(), e);
            throw e;
        }
    }


    public ResponseEntity<List<restaurantModel>> findAll(){
        try {
            // Log de inicio
            customLogging.logInfo("restaurantService", "findAll", 
                                "Obteniendo todos los restaurantes");
            
            List<restaurantModel> restaurants = (List<restaurantModel>) restaurantRepository.findAll();
            
            // Log de exit
            customLogging.logInfo("restaurantService", "findAll", 
                                "Encontrados " + restaurants.size() + " restaurantes");
            
            return ResponseEntity.ok(restaurants);
            
        } catch (Exception e) {
            // Log de error
            customLogging.logError("restaurantService", "findAll", 
                                 "Error obteniendo restaurantes", e);
            throw e;
        }
    }

    public Optional<restaurantModel> findById(Long restaurant_id){
        try {
            // Log de inicio
            customLogging.logInfo("restaurantService", "findById", 
                                "Buscando restaurante con ID: " + restaurant_id);
            
            Optional<restaurantModel> result = restaurantRepository.findById(restaurant_id);
            
            if (result.isPresent()) {
                customLogging.logInfo("restaurantService", "findById", 
                                    "Restaurante encontrado ID: " + restaurant_id);
            } else {
                customLogging.logInfo("restaurantService", "findById", 
                                    "Restaurante NO encontrado ID: " + restaurant_id);
            }
            
            return result;
            
        } catch (Exception e) {
            // Log de error
            customLogging.logError("restaurantService", "findById", 
                                 "Error buscando restaurante ID: " + restaurant_id, e);
            throw e;
        }
    }

    public void deleteAll(){
        try {
            // Log de inicio
            customLogging.logInfo("restaurantService", "deleteAll", 
                                "Eliminando todos los restaurantes");
            
            restaurantRepository.deleteAll();
            
            // Log de exit
            customLogging.logInfo("restaurantService", "deleteAll", 
                                "Todos los restaurantes eliminados");
            
        } catch (Exception e) {
            // Log de error
            customLogging.logError("restaurantService", "deleteAll", 
                                 "Error eliminando todos los restaurantes", e);
            throw e;
        }
    }

    public ResponseEntity<String> createByCsv(MultipartFile cvsFile) throws IOException{
        ArrayList<String> noAcceptat = new ArrayList<>();
        int insert = 0;
        int lineNumber = 0;

        try {
            // Log de inicio
            customLogging.logInfo("restaurantService", "createByCsv", 
                                "Procesando archivo CSV: " + cvsFile.getOriginalFilename());
            
            try(BufferedReader br = new BufferedReader(new InputStreamReader(cvsFile.getInputStream()))){
                String linea = br.readLine();
                if (linea == null) {
                    customLogging.logInfo("restaurantService", "createByCsv", 
                                        "Archivo CSV vacío");
                    return ResponseEntity.ok("Archivo CSV vacío");
                }
                
                customLogging.logInfo("restaurantService", "createByCsv", 
                                    "Cabecera CSV: " + linea);

                while ((linea = br.readLine()) != null) {
                    lineNumber++;
                    String[] recursos = linea.split(",");
                    
                    if(recursos.length != 15){
                        String restaurantName = recursos.length > 0 ? limpiar(recursos[0]) : "Desconocido";
                        noAcceptat.add(restaurantName);
                        customLogging.logInfo("restaurantService", "createByCsv", 
                                            "Línea " + lineNumber + " rechazada: " + restaurantName + 
                                            " (campos: " + recursos.length + ")");
                        continue;
                    }
                    
                    try {
                        restaurantModel restaurante = new restaurantModel(
                            limpiar(recursos[0]),                         // name (String)
                            Double.parseDouble(limpiar(recursos[1])),     // rating (Double)
                            Boolean.parseBoolean(limpiar(recursos[2])),   // isVerified (Boolean)
                            Boolean.parseBoolean(limpiar(recursos[3])),   // senseGluten (Boolean)
                            Boolean.parseBoolean(limpiar(recursos[4])),   // senseLactosa (Boolean)
                            Boolean.parseBoolean(limpiar(recursos[5])),   // senseFruitsSecs (Boolean)
                            Boolean.parseBoolean(limpiar(recursos[6])),   // senseMarisc (Boolean)
                            Boolean.parseBoolean(limpiar(recursos[7])),   // esVega (Boolean)
                            Boolean.parseBoolean(limpiar(recursos[8])),   // esHalal (Boolean)
                            Boolean.parseBoolean(limpiar(recursos[9])),   // esKosher (Boolean)
                            Boolean.parseBoolean(limpiar(recursos[10])),  // teOu (Boolean)
                            limpiar(recursos[11]),                        // tipusCuina (String)
                            limpiar(recursos[12]),                        // rangPreu (String)
                            limpiar(recursos[13]),                        // ubicacio (String)
                            Integer.parseInt(limpiar(recursos[14]))       // telefon (Integer)
                        );
                        
                        restaurantRepository.save(restaurante);
                        insert++;
                        
                        customLogging.logInfo("restaurantService", "createByCsv", 
                                            "Restaurante creado línea " + lineNumber + ": " + restaurante.getName());
                        
                    } catch (NumberFormatException e) {
                        customLogging.logError("restaurantService", "createByCsv", 
                                             "Error formato numérico línea " + lineNumber, e);
                        noAcceptat.add("Línea " + lineNumber + " - Error formato");
                    } catch (Exception e) {
                        customLogging.logError("restaurantService", "createByCsv", 
                                             "Error línea " + lineNumber, e);
                        noAcceptat.add("Línea " + lineNumber + " - Error general");
                    }
                }
                
                // Resultado final
                String resultado = "Restaurants inserits: " + insert + " usuaris no inserits: " + noAcceptat.size();
                
                customLogging.logInfo("restaurantService", "createByCsv", 
                                    "Proceso CSV completado: " + resultado);
                
                if (!noAcceptat.isEmpty()) {
                    customLogging.logInfo("restaurantService", "createByCsv", 
                                        "Restaurantes no aceptados: " + noAcceptat);
                }
                
                return ResponseEntity.ok(resultado);
            }
            
        } catch (Exception e) {
            // Log de error
            customLogging.logError("restaurantService", "createByCsv", 
                                 "Error procesando archivo CSV", e);
            throw e;
        }
    }

    private String limpiar(String valor) {
        return valor.trim().replaceAll("\"", "");
    }
}