package com.jjcompany.productapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jjcompany.productapi.model.Brand;
import com.jjcompany.productapi.model.BrandRepository;


@RestController
public class BrandController {

    private final BrandRepository repository;

    BrandController(BrandRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/brand")
    List<Brand> all() {
        return repository.findAll();
    }

    @PostMapping("/brand")
    Brand newBrand(@RequestBody Brand newBrand) {
        return repository.save(newBrand);
    }


    @GetMapping("/brand/{id}")
    Brand one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new BrandNotFoundExcepetion(id));
    }


    @PutMapping("/brand/{id}")
    Brand replaceBrand(@RequestBody Brand newBrand, @PathVariable Long id) {
        return repository.findById(id)
                .map(brand -> {
                    brand.setName(newBrand.getName());
                    brand.setEmail(newBrand.getEmail());
                    brand.setDescription(newBrand.getDescription());
                    return repository.save(brand);
                })
                .orElseGet(() -> {
                    newBrand.setId(id);
                    return repository.save(newBrand);
                });
    }

    @DeleteMapping("/brand/{id}")
    void deleteBrand(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
