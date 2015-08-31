/**
 * 
 */
package br.com.edutex.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.JSONObject;

import br.com.edutex.logic.RegimeTributarioEmpresa;
import br.com.edutex.logic.TipoStatus;
import br.com.edutex.logic.Usuario;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.MensagemProp;

/**
 * @author bruno
 *
 */
public class UsuarioDao extends AbstractDao {

	
	private static UsuarioDao instance;
	
	private UsuarioDao(){
		
	}

	
	public static UsuarioDao getInstance(){
		if (instance == null) {
			instance = new UsuarioDao();
		}
		
		return instance;
	}
	
	
	
	/**
	 * 
	 * metodo para buscar lista de usuario 
	 * @info busca o usuario usando no nmUsuario como filtro e tipoStatus.cdStatus = 1  e retorna uma lista de usuarios
	 * @return lista de usuario de acorto com o filtro de nmUsuario
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuarios(String user) {
		String query = "SELECT usu from Usuario usu "
				+ "WHERE usu.nmLogin = :user "
				+ "and usu.tpStatus.nmStatus = :status " ;
		
		
		return  (ArrayList<Usuario>) getEntityManager().createQuery(query)
				.setParameter("user",user)
				.setParameter("status", "Ativo")
				.getResultList();
	}
	/** 
	 * 
	 * metodo para buscar usuario 
	 * @info busca o usuario usando no cdUsuario como filtro e tipoStatus.cdStatus = 1
	 * @return usuario de acorto com o filtro de cdUsuario
	 */
	public Usuario getUsuario(int userId) {		
		
		EntityManager manager = getEntityManager();
		return manager.find(Usuario.class, userId);
		
	}
	
	public List<Usuario> buscaUsuariosFull(String user) {
		String query = "SELECT usu from Usuario usu "
				+ "WHERE usu.nmLogin = :user ";
		
		
		return  (ArrayList<Usuario>) getEntityManager().createQuery(query)
				.setParameter("user",user)
				.getResultList();
	}
	
	/**
	 *  Salva usuário apenas na tabela Usuario e na tabela UsuarioEmpresa
	 * @param usu
	 * @return código de erro
	 */
	public Object salvarUsuario(Usuario usu) {

		 EntityManager manager = factory.createEntityManager(); 
		 EntityTransaction transaction = manager.getTransaction();
		 int id = 0;
		 
		 try{
			 transaction.begin();
			 usu.setTpStatus(manager.merge(usu.getTpStatus()));
			 usu.setTpUsuario(manager.merge(usu.getTpUsuario()));
			 manager.persist(usu);
			 manager.flush();
			 id = usu.getCdUsuario();
			 transaction.commit();
		 } catch (PersistenceException e) {
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 
			 if (e.getCause().getClass().equals(ConstraintViolationException.class)) {
				  return MensagemProp.getPropriedades().get("usuario.cadastro.existente");
			 }
		
		 
		 } catch(Exception e){
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 return MensagemProp.getPropriedades().get("usuario.cadastro.erro");
		 } finally {
			 manager.close();
			 transaction = null;
		 }
		 
		 
		 return id;
	}
	
	
	public Object atualizarUsuario(Usuario usu) {
		 EntityManager manager = factory.createEntityManager(); 
		 EntityTransaction transaction = manager.getTransaction();
		 int id = 0;
		 
		 try{
			 transaction.begin();
			 usu.setTpStatus(manager.merge(usu.getTpStatus()));
			 usu.setTpUsuario(manager.merge(usu.getTpUsuario()));
			 Usuario usuMerge = manager.merge(usu);
			 manager.persist(usuMerge);
			 manager.flush();
			 id = usu.getCdUsuario();
			 transaction.commit();
		 } catch (PersistenceException e) {
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 
			 if (e.getCause().getClass().equals(ConstraintViolationException.class)) {
				  return MensagemProp.getPropriedades().get("usuario.cadastro.existente");
			 }
		
		 
		 } catch(Exception e){
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 return MensagemProp.getPropriedades().get("usuario.cadastro.erro");
		 } finally {
			 transaction = null;
		 }
		 
		 return MensagemProp.getPropriedades().get("usuario.atualizado.sucesso");
		 
	}
	
	
	
	public Object atualizarStatusUsuario(Usuario usu) {
		 EntityManager manager = factory.createEntityManager(); 
		 EntityTransaction transaction = manager.getTransaction();
		 int id = 0;
		 JSONObject obj= new JSONObject();
		 
		 try{
			 transaction.begin();
			 usu.setTpStatus(manager.merge(usu.getTpStatus()));
			 usu.setTpUsuario(manager.merge(usu.getTpUsuario()));
			 Usuario usuMerge = manager.merge(usu);
			 manager.persist(usuMerge);
			 manager.flush();
			 id = usu.getCdUsuario();
			 transaction.commit();
		 } catch (PersistenceException e) {
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
				
				obj.put("erro", MensagemProp.getPropriedades().get("usuario.status.atualizacao.status").toString());
			 
			
		
		 
		 } catch(Exception e){
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 return MensagemProp.getPropriedades().get("usuario.cadastro.erro");
		 } finally {
			 transaction = null;
		 }
		 
		 obj.put("sucess",MensagemProp.getPropriedades().get("usuario.status.sucesso").toString());
		 
		 return obj;
		 
	}
	
	
}
