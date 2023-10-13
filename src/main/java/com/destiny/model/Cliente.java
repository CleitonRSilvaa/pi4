package com.destiny.model;

import com.destiny.utils.EncriptSenha;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(length = 12)
    private String telefone;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private StatusConta statusConta;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date dataCriacao;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }
}
