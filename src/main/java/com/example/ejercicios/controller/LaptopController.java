package com.example.ejercicios.controller;

import com.example.ejercicios.entities.Laptop;
import com.example.ejercicios.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    /**
     * findOneById()
     *
     * @return devuelve una lista de laptops
     */
    @GetMapping("/api/laptops")
    @ApiOperation("Devuelve todos los registros de la base de datos")
    public List<Laptop> findAll() {
        return laptopRepository.findAll();
    }

    /**
     * findOneById()
     *
     * @return devuelve un ResponseEntity de laptop
     */
    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Devuelve un registro por id")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if (laptopOpt.isPresent()) return ResponseEntity.ok(laptopOpt.get());
        else return ResponseEntity.notFound().build();

    }

    /**
     * create()
     *
     * @param laptop crea un nuevo registro en la base de dato
     */
    @PostMapping("/api/laptop")
    @ApiOperation("Crea un nuevo de registro de laptop")
    public Laptop create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        // guardar la laptop recibida por parámetro en la base de datos
        return laptopRepository.save(laptop);
    }

    /**
     * update()
     *
     * @param laptop actualiza una laptop existente en la base de datos
     */
    @PutMapping("/api/laptops")
    @ApiOperation("Actualiza una registro en la base de datos")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        if (laptop.getId() == null) { // si no tiene id quiere decir que sí es una creación
             log.warn("Trying to update a non existent laptop");
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptop.getId())) {
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria
    }

    /**
     * delete()
     *
     * @param id elimina una laptop por id
     */
    //@ApiIgnore
    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation("Elimina un registro por id")
    public ResponseEntity<Laptop> delete(@PathVariable Long id) {

        if (!laptopRepository.existsById(id)) {
            log.warn("Trying to delete a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * deleteAll()
     * elimina todos los registros de la base de datos
     */
    // @ApiIgnore // ignorar este método para que no aparezca en la documentación de la api Swagger
    @DeleteMapping("/api/laptops")
    @ApiOperation("Elimina todos los registros")
    public ResponseEntity<Laptop> deleteAll() {
        //log.info("REST Request for delete all laptops");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
