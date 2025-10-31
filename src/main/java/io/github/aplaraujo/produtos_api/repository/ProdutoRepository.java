package io.github.aplaraujo.produtos_api.repository;

import io.github.aplaraujo.produtos_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Interface que disponibiliza todos os métodos para a comunicação da entidade com o banco de dados (encapsular as operações de persistência de dados)
public interface ProdutoRepository extends JpaRepository<Produto, String> {
    List<Produto> findByNome(String nome);
}
