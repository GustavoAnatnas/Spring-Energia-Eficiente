package com.energia.eficiente.controller;

import com.energia.eficiente.model.Usuario;
import com.energia.eficiente.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodos();
    }
}