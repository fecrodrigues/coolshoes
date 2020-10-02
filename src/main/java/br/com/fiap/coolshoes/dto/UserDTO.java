package br.com.fiap.coolshoes.dto;

import br.com.fiap.coolshoes.entity.User;

public class UserDTO {

    private Long id;
    private String username;

    public UserDTO() { }

    public UserDTO(User userSaved) {
        this.id = userSaved.getId();
        this.username = userSaved.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
