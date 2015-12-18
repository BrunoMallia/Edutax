
	package br.com.edutex.form;
	 
	 import java.io.File;
	 import java.util.ArrayList;
	 import java.util.Calendar;
	 import java.util.List;
	 

	 import javax.persistence.EntityManager;
	 import javax.persistence.EntityManagerFactory;
	 import javax.persistence.EntityTransaction;
	 import javax.persistence.Persistence;
	 


	 import br.com.edutex.logic.NFE;
	 import br.com.edutex.notafiscal.NotaFiscal;
	 
	 public class TestePersist {
	 
	 	public static void main(String[] args) {
	 		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Edutex");
	 		EntityManager manager = factory.createEntityManager(); 
	 		EntityTransaction transaction = manager.getTransaction();
	 		
	 		
	 		try {
	 		 transaction.begin();
	 		 
	 		/* //C�DIGO PARA TESTE DE PERSIST�NCIA
	 		
	 		 
	 			NCM ncm = new NCM();
	 			ncm.setDtCriacao(Calendar.getInstance());
	 			ncm.setNmNCM("78");
	 			ncm.setDtAtualizacao(Calendar.getInstance());
	 			ncm.setTpStatus(manager.find(TipoStatus.class, 1));
	 			ncm.setEmpresa(manager.find(Empresa.class, 1));
	 			ncm.setNuValor(12.0);
	 			
	 			
	 			List<ImpostoNcm> listaImpostoNcm = new ArrayList<ImpostoNcm>();
	 			ImpostoNcm impostoNcmICMS = new ImpostoNcm();
	 			
	 			//Tipo Imposto ICMS
	 			impostoNcmICMS.setFinalidadeNfe(manager.find(FinalidadeNfe.class, 1));
	 			impostoNcmICMS.setTipoImposto(manager.find(TipoImposto.class, 1));
	 			impostoNcmICMS.setTpStatus(manager.find(TipoStatus.class, 1));
	 			impostoNcmICMS.setNcm(ncm);
	 			impostoNcmICMS.setEmpresa(manager.find(Empresa.class, 1));
	 			
	 			ImpostoNcmEstado impostoNcmEstado = new ImpostoNcmEstado();
	 			List<ImpostoNcmEstado> impostosNcmEstado = new ArrayList<ImpostoNcmEstado>();
	 			
	 			
	 			impostoNcmEstado.setEstado(manager.find(Estado.class,35));
	 			impostoNcmEstado.setImpostoNCM(impostoNcmICMS);
	 			impostoNcmEstado.setMva((float)12.00);
	 			impostoNcmEstado.setMvaAjustado((float)10.00);
	 			
	 			
	 			impostosNcmEstado.add(impostoNcmEstado);
	 			
	 			//impostoNcmICMS.setImpostosNcmEstado(impostosNcmEstado);
	 			
	 			listaImpostoNcm.add(impostoNcmICMS);
	 			
	 			ncm.setImpNcm(listaImpostoNcm);
	 
	 			
	 			
	 			//PIS
	 			impostoNcmICMS.setFinalidadeNfe(manager.find(FinalidadeNfe.class, 1));
	 			impostoNcmICMS.setTipoImposto(manager.find(TipoImposto.class, 2));
	 			impostoNcmICMS.setTpStatus(manager.find(TipoStatus.class, 1));
	 			impostoNcmICMS.setNcm(ncm);
	 			impostoNcmICMS.setEmpresa(manager.find(Empresa.class, 1));
	 			
	 			ImpostoNcmEstado impostoNcmEstado2 = new ImpostoNcmEstado();
	 			List<ImpostoNcmEstado> impostosNcmEstado2 = new ArrayList<ImpostoNcmEstado>();
	 			
	 			
	 			impostoNcmEstado2.setEstado(manager.find(Estado.class,35));
	 			impostoNcmEstado2.setImpostoNCM(impostoNcmICMS);
	 			impostoNcmEstado2.setMva((float)12.00);
	 			impostoNcmEstado2.setMvaAjustado((float)10.00);
	 			
	 			
	 			impostosNcmEstado2.add(impostoNcmEstado2);
	 			
	 			//impostoNcmICMS.setImpostosNcmEstado(impostosNcmEstado2);
	 			
	 			listaImpostoNcm.add(impostoNcmICMS);
	 			
	 			ncm.setImpNcm(listaImpostoNcm);
	 			*/
	 			
	 		 	File arquivo = new File("C:\\notasFiscais\\35150583108712003503550010000029271515838486-nfe.xml");
	 			NotaFiscal nota = new NotaFiscal();
	 			
	 			
	 			NFE nfe1 = null;
	 			
	 		 	nfe1 = nota.lerXML(arquivo);
	 			nfe1.getNotaValidada().setNmFornecedor("FORNECEDOR TESTE");
	 		 	nfe1.setDtUpload(Calendar.getInstance());
	 		 
	 			manager.persist(nfe1);
	 		 
	 			transaction.commit();
	 			
	 		} catch (Exception e) {
	 			e.getStackTrace();
	 			System.out.println(e.getMessage());
	 			transaction.rollback();
	 			
	 		}
	 
	 	}
	 
	 }

