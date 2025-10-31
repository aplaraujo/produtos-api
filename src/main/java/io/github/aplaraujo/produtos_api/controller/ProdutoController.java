package io.github.aplaraujo.produtos_api.controller;

import io.github.aplaraujo.produtos_api.model.Produto;
import io.github.aplaraujo.produtos_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Controlador - classe que recebe as requisições da API
@RestController // Anotação que sinaliza o recebimento de requisições REST
@RequestMapping("/produtos") // Anotação que mostra a URL base do controlador
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    // Injetar a interface
    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Método para salvar o produto
    @PostMapping // Chamada para salvar o produto no servidor
    public Produto salvar(@RequestBody  Produto produto) { // Anotação que envia o objeto para o corpo da requisição
        System.out.println("Produto recebido: " + produto);
        // Definir o código do produto
        var id = UUID.randomUUID().toString();
        produto.setId(id);
        produtoRepository.save(produto);
        return produto; // Retorna o objeto JSON
    }

    // Método para obter o produto do banco de dados
    @GetMapping("/{id}")
    public Produto obterProdutoPorId(@PathVariable("id") String id) { // Receber o id pela URL
        return produtoRepository.findById(id).orElse(null);
    }

    // Método para buscar produtos por critérios específicos
    @GetMapping
    public List<Produto> buscarProdutoPorNome(@RequestParam("nome") String nome) { // Busca por critérios específicos
        return produtoRepository.findByNome(nome);
    }

    // Método para atualizar produtos
    @PutMapping("/{id}")
    public void atualizarProduto(@PathVariable("id") String id, @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

    // Método para excluir produtos da base de dados
    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable("id") String id) {
        produtoRepository.deleteById(id);
    }
}
