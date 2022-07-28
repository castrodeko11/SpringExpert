package br.com.aco.rest.controller;

import br.com.aco.domain.entity.Usuario;
import br.com.aco.exception.SenhaInvalidaException;
import br.com.aco.rest.dto.CredenciasDTO;
import br.com.aco.rest.dto.TokenDTO;
import br.com.aco.security.jwt.JwtService;
import br.com.aco.service.impl.UsuarioServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImp usuarioServiceImp;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioServiceImp.salvar(usuario);

    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciasDTO credencias) {
        try {
            Usuario usuario =
                    Usuario.builder()
                            .login(credencias.getLogin())
                            .senha(credencias.getSenha()).build();

            UserDetails userDetails = usuarioServiceImp.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

        }
    }
}
