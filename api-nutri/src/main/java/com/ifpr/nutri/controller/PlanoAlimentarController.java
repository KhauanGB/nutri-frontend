package com.ifpr.nutri.controller;

import com.ifpr.nutri.dto.plano.PlanoAlimentarDto;
import com.ifpr.nutri.dto.plano.PlanoAlimentarResponseDto;
import com.ifpr.nutri.service.PlanoAlimentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/planos")
public class PlanoAlimentarController {

    @Autowired
    private PlanoAlimentarService planoAlimentarService;

    @PostMapping
    public ResponseEntity<PlanoAlimentarResponseDto> create(@RequestBody PlanoAlimentarDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planoAlimentarService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PlanoAlimentarResponseDto>> findAll() {
        return ResponseEntity.ok(planoAlimentarService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
        @PathVariable Long id,
        @RequestParam(required = false) List<Long> refeicoesIds,
        @RequestParam(required = false) LocalDate dataInicio,
        @RequestParam(required = false) LocalDate dataFim,
        @RequestParam(required = false) String observacoes
    ) {
        planoAlimentarService.update(id, refeicoesIds, dataInicio, dataFim, observacoes);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planoAlimentarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
