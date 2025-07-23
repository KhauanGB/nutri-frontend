package com.ifpr.nutri.controller;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.dao.Alimento.Unidade;
import com.ifpr.nutri.dto.alimento.AlimentoResponseDto;
import com.ifpr.nutri.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @PostMapping
    public ResponseEntity<AlimentoResponseDto> create(@RequestBody Alimento alimento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentoService.create(alimento));
    }

    @GetMapping
    public ResponseEntity<List<AlimentoResponseDto>> findAll() {
        return ResponseEntity.ok(alimentoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
        @PathVariable Long id,
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) Double calorias,
        @RequestParam(required = false) Double proteinas,
        @RequestParam(required = false) Double carboidratos,
        @RequestParam(required = false) Double gorduras,
        @RequestParam(required = false) String porcao
    ) {
        alimentoService.update(id, nome, calorias, proteinas, carboidratos, gorduras, porcao);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alimentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
