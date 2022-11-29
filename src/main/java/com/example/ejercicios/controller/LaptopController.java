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
/**
 * Login
 * */
@GetMapping("/login")
public String bootstrap(){
    return """
                <!doctype html>
                <html lang="en">
                  <head>
                    <!-- Required meta tags -->
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                                
                    <!-- Bootstrap CSS -->
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
                                
                    <title>Hello, world!</title>
                  </head>
                  <body>
                    <h1>Hola mundo desde Srping Boot!</h1>
                     <a class="btn btn-primary" href="https://www.google.com"> Google </a>
                    <!-- Optional JavaScript; choose one of the two! -->
                                
                    <!-- Option 1: Bootstrap Bundle with Popper -->
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
                                
                    <!-- Option 2: Separate Popper and Bootstrap JS -->
                    <!--
                    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
                    -->
                  </body>
                </html>
         
                """;
}
}
