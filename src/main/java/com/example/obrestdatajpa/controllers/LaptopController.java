package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Laptop;
import com.example.obrestdatajpa.repository.LaptopRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;




@RestController
public class LaptopController {

    //Atributo
    private LaptopRepository laptopRepository;

    //Constructor

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }


    //METODOS

    ////Metodo para recuperar todas las laptops guardadas.
    @GetMapping ("/api/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    //Buscar un solo laptop por id
    @GetMapping ("/api/laptop/{id}")
    public ResponseEntity<Laptop> findOneId (@PathVariable Long id){
        Optional<Laptop> request = laptopRepository.findById(id);
        if (request.isPresent()){
            return ResponseEntity.ok(request.get());
        }else {
            return ResponseEntity.notFound().build();
        }
        //Formasde devolver un Objeto cuando no trabajamos con ResponseEntity
        //Opción 1
//      if (request.isPresent()){
//          return request.get();
//       }else {
//          return null:
//      }
        //Opcion 2
//       return request.orElse(null);

    }

    @PostMapping ("/api/crearlaptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){

        if(laptop.getId() != null){ // quiere decir que existe el id y por tanto no es una creación
            System.out.println("Intenta crear una laptop con id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result); // la laptop devuelta tiene una clave primaria
    }

    @PutMapping ("/api/laptop")
    public ResponseEntity <Laptop> update (@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            System.out.println("Intenta modificar una laptop que no existe");
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptop.getId())){
            System.out.println("Intenta modificar una laptop no existente");
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping ("/api/laptop/{id}")
    public ResponseEntity<Laptop> delete (@PathVariable Long id){
        if (!laptopRepository.existsById(id)){
            System.out.println ("Intenta eliminar una laptop no existente");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping ("api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
