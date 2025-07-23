package com.ifpr.nutri.controller;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.dto.LoginDto;
import com.ifpr.nutri.dto.pessoa.RelatorioDto;
import com.ifpr.nutri.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Pessoa pessoa) {
        pessoaService.create(pessoa);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(pessoaService.autenticar(loginDto.cpf(), loginDto.senha()));
    }

    @GetMapping("/generate")
    public ResponseEntity<RelatorioDto> generate(@RequestParam String cpf, @RequestParam LocalDate date) {
        return ResponseEntity.ok(pessoaService.generate(cpf, date));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Void> update(
            @PathVariable String cpf,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String senha,
            @RequestParam(required = false) Integer idade,
            @RequestParam(required = false) Double peso,
            @RequestParam(required = false) Double altura,
            @RequestParam(required = false) List<String> objetivos,
            @RequestParam(required = false) List<Alimento> restricoesAlimentares
    ) {
        pessoaService.update(
                username,
                nome,
                cpf,
                senha,
                idade,
                peso,
                altura,
                objetivos,
                restricoesAlimentares
        );
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        pessoaService.deleteByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
