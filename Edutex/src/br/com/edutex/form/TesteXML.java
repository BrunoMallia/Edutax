package br.com.edutex.form;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;



import br.com.edutex.exceptions.NotaInvalidaException;
import br.com.edutex.logic.NFE;
import br.com.edutex.notafiscal.NotaFiscal;

public class TesteXML {

	String str = new String();	
	private static final String MENSAGEM_NOTA_INVALIDA = "Erro na validação da nota, contacte com o Administrador do sistema";
	
	
	public static void main(String[] args) {
		
		


		//File xmlFile = new File("c:\\notasFiscais\\35150583108712003503550010000029271515838486-nfe.xml");
		
		NFE nfe = new NFE();
			
		//nfe.setNmFilePath("c:\\notasFiscais\\ELETRICAPJ585434.xml");
	
		
		nfe.setDtUpload(Calendar.getInstance());
		
		//nfe.setNmFilePath("C:\\notasFiscais\\pgadmin.log");
		//NotaFiscalInput nota = new NotaFiscalInput(nfe);
		
		File arquivo = new File("C:\\notasFiscais\\35150583108712003503550010000029271515838486-nfe.xml");
		NotaFiscal nota = new NotaFiscal();
		
		
		NFE nfe1 = null;
		
				try {
					
					nfe1 = nota.lerXML(arquivo);
					nfe1.getNotaValidada().setNmNFornecedor("FORNECEDOR TESTE");
					//notaOutput = new NotaFiscalOutput(nfe1);
					//notaOutput.escreverXML(nfe1);
				
					
					nota.escreverXML(nfe1);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotaInvalidaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.out.println("Execução realizada " + nfe.toString());
				}

		
		
		
		//System.out.println("Finalidade da nota" + nfe1.getNotaValidada());
		
		
		
		//System.out.println(map.toString());
		
		/*HashMap<String,Object> resultado = lerXML(xmlFile);
		
		
		if (resultado.get("erro") == null) {
			System.out.println(resultado.get("cUF").toString());
			System.out.println(resultado.get("cFN").toString());
			HashMap<String,Object> prodMap = (HashMap<String,Object>) resultado.get("prod1");
			HashMap<String,Object> impostoMap = (HashMap<String,Object>)resultado.get("ICMS1");
			
			System.out.println(resultado.toString());
			
			
			
			
		} else {
			System.out.println(resultado.get("erro").toString());
		}
		
		*/

		
	}


}
