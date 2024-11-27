package com.catalogo;

import java.util.List;

import com.catalogo.dao.LivroDAO;
import com.catalogo.entidades.Livro;


public class Main {
	public static void main(String[] args) {
		LivroDAO livroDAO = new LivroDAO();

		// Criação de livros
//        Livro livro1 = new Livro("O Lado Feio do Amor", "Collen Hoover");
//        Livro livro2 = new Livro("Harry Potter e a Pedra Filosofal", "J.K. Rowling");
//
//        // Salvar os livros no banco
//        livroDAO.salvar(livro1);
//        livroDAO.salvar(livro2);
//

		// informe o id para deletar um livro
		//livroDAO.deletar((long) 8);

		// Buscar um livro por ID
        //livroDAO.buscarPorId(5L); 

		// Listar todos os livros
		List<Livro> livros = livroDAO.listarTodos();
		for (Livro livro : livros) {
			System.out.println(livro.getTitulo());
		}
		
		//editar livro id, titulo, autor
		livroDAO.editarLivro(5L, "OIII", "Collen Hoover");

	}
}
