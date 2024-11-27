package com.catalogo.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.catalogo.entidades.Livro;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

    static {
        try {
            // Cria a fábrica de sessões a partir da configuração
            sessionFactory = new Configuration().configure().addAnnotatedClass(Livro.class).buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
