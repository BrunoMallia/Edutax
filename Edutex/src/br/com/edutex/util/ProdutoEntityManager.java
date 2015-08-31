package br.com.edutex.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ProdutoEntityManager {
	
	public ProdutoEntityManager() {
		
	}
	
	private EntityManagerFactory entity =  Persistence.createEntityManagerFactory("Edutex");
	

	public EntityManager criarEntityManager() {
		return entity.createEntityManager();
	}
}
