package com.vidigal.sprint_boot_essencials.service;

import com.vidigal.sprint_boot_essencials.config.TokenProvider;
import com.vidigal.sprint_boot_essencials.database.model.AlunosEntity;
import com.vidigal.sprint_boot_essencials.database.model.RolesEntity;
import com.vidigal.sprint_boot_essencials.database.repository.IAlunosRepository;
import com.vidigal.sprint_boot_essencials.database.repository.IRolesRepository;
import com.vidigal.sprint_boot_essencials.dto.AlunosDto;
import com.vidigal.sprint_boot_essencials.dto.LoginRequestDto;
import com.vidigal.sprint_boot_essencials.dto.RegisterRequestDto;
import com.vidigal.sprint_boot_essencials.dto.TokenResponseDto;
import com.vidigal.sprint_boot_essencials.enums.RoleTypeEnum;
import com.vidigal.sprint_boot_essencials.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IAlunosRepository alunosRepository;
    private final IRolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Value("${spring.jwt.expiration}")
    private long expirationTime;

    public void register(RegisterRequestDto dto) throws BadRequestException {
        AlunosEntity aluno = alunosRepository.findByEmail(dto.getEmail()).orElse(null);

        if (aluno != null){
            throw new BadRequestException("Email já cadastrado");
        }

        RolesEntity role = rolesRepository.findByNome(RoleTypeEnum.ROLE_ALUNO.name())
                        .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                                        .nome(RoleTypeEnum.ROLE_ALUNO.name())
                                .build()));

        alunosRepository.save(AlunosEntity.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .roles(Set.of(role))
                .senha(passwordEncoder.encode(dto.getSenha()))
                .build()
        );
    }

    public TokenResponseDto login(LoginRequestDto dto) throws Exception{

        try {
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));
           String token = tokenProvider.gerarToken(authentication);

           return new TokenResponseDto(token, expirationTime);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Credenciais inválias");
        }catch (Exception e) {
            throw e;
        }
    }
}
