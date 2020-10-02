package br.com.fiap.coolshoes.service;

import br.com.fiap.coolshoes.dto.AuthDTO;
import br.com.fiap.coolshoes.dto.JwtDTO;
import br.com.fiap.coolshoes.dto.UserCreateDTO;
import br.com.fiap.coolshoes.dto.UserDTO;

public interface UserService {

    UserDTO create(UserCreateDTO userCreateDTO);
    JwtDTO login(AuthDTO authDTO);

}
