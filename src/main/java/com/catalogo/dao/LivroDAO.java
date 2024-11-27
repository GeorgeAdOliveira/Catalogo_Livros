package com.catalogo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.catalogo.entidades.Livro;
import com.catalogo.util.HibernateUtil;


public class LivroDAO {
	
	 // Método para salvar um livro
    public void salvar(Livro livro) {
        Session session = HibernateUtil.getSessionFactory().openSession(); // Obtém a sessão
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction(); // Inicia a transação
            session.save(livro); // Salva o livro no banco
            transaction.commit(); // Realiza o commit da transação
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Se ocorrer erro, faz o rollback
            }
            e.printStackTrace();
        } finally {
            session.close(); // Fecha a sessão
        }
    }
    
    
    
    // Método para deletar um livro por ID
    public void deletar(Long id) {
        Transaction transacao = null;
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();
            Livro livro = sessao.find(Livro.class, id);
            if (livro != null) {
                sessao.remove(livro);
                System.out.println("Livro deletado com sucesso!");
            } else {
                System.out.println("Livro com ID " + id + " não encontrado.");
            }
            transacao.commit();
        } catch (Exception e) {
            if (transacao != null) transacao.rollback();
            e.printStackTrace();
        }
    }

    // Método para buscar um livro por ID
    public Livro buscarPorId(Long id) {
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            Livro livro = sessao.find(Livro.class, id);
            if (livro != null) {
                System.out.println("Livro encontrado: " + livro.getTitulo());
            } else {
                System.out.println("Livro com ID " + id + " não encontrado.");
            }
            return livro;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
 // Método para editar um livro
    public void editarLivro(Long id, String novoTitulo, String novoAutor) {
        Transaction transacao = null;
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();
            
            // Localiza o livro pelo ID
            Livro livro = sessao.find(Livro.class, id);
            
            if (livro != null) {
                // Atualiza os dados do livro
                livro.setTitulo(novoTitulo);
                livro.setAutor(novoAutor);
                
                // Salva as mudanças
                sessao.merge(livro);
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Livro com ID " + id + " não encontrado.");
            }
            
            transacao.commit();
        } catch (Exception e) {
            if (transacao != null) transacao.rollback();
            e.printStackTrace();
        }
    }   

    // Método para listar todos os livros
    public List<Livro> listarTodos() {
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            Query<Livro> query = sessao.createQuery("FROM Livro", Livro.class);
            List<Livro> livros = query.list();
            System.out.println("Livros encontrados: " + livros.size());
            return livros;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
