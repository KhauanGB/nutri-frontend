package com.ifpr.nutri.controller;

import com.ifpr.nutri.dto.refeicao.RefeicaoDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoResponseDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoUpdateDto;
import com.ifpr.nutri.service.RefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refeicoes")
public class RefeicaoController {

    @Autowired
    private RefeicaoService refeicaoService;

    @PostMapping
    public ResponseEntity<RefeicaoResponseDto> create(@RequestBody RefeicaoDto refeicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(refeicaoService.create(refeicao));
    }

    @GetMapping
    public ResponseEntity<List<RefeicaoResponseDto>> findAll() {
        return ResponseEntity.ok(refeicaoService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody RefeicaoUpdateDto dto) {
        refeicaoService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        refeicaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
