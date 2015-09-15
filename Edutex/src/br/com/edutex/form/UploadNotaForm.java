package br.com.edutex.form;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.FinalidadeNfeDao;
import br.com.edutex.DAO.ImpostoNcmDao;
import br.com.edutex.exceptions.NotaInvalidaException;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.FinalidadeNfe;
import br.com.edutex.logic.ImpostoNcm;
import br.com.edutex.logic.NFE;
import br.com.edutex.logic.NotaValidadaItem;
import br.com.edutex.logic.Validacao;
import br.com.edutex.notafiscal.NotaFiscal;
import br.com.edutex.util.ConfiguracaoProp;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.MensagemProp;

public class UploadNotaForm extends Action {
	
	private String[] extensoesArray = null;
	private  Object[] arquivoUpload;
	
	private static final int CODIGO_FINALIDADE_NOTACORRETIVA = 4;
	
	public UploadNotaForm() {
		
		 //carrega lista de extensoes
		 StringTokenizer strtToken = new StringTokenizer(ConfiguracaoProp.getPropriedades().get("notafiscal.extensoes.upload").toString(), ",");
		 int tamanhoExtensaoArray = strtToken.countTokens();
		 extensoesArray = new String[tamanhoExtensaoArray];
		 
		 for(int i = 0 ; i < tamanhoExtensaoArray; i++) {
			 extensoesArray[i] = strtToken.nextToken();
		 }
		 
		 
	}
		
	
	/* (non-Javadoc)
	 * Action para tratar os uplods das notas fiscais
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
        String nomeArquivo = null,contentType, extensao = null;
        int cdFinalidadeNfe = 0;
        String extensoesValidas = "";
        InputStream ioStream = null;
        boolean errorExtension = true;
       // SimpleDateFormat dateFormat = new SimpleDateFormat();
		
		
			try {
				FileItemFactory factory = new DiskFileItemFactory();
		        ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
    
                
               for(FileItem item : items) {
                	if(!item.isFormField()){
                		nomeArquivo = item.getName();
                		contentType = item.getContentType();
                		ioStream = item.getInputStream();
                	}else {
                		if (item.getFieldName().equals("finalidadeNfe"))
                			cdFinalidadeNfe = Integer.parseInt(item.getString());
                	}
                
                	 
                }
               
               
                //valida extensao
                
                if(nomeArquivo == null || nomeArquivo.equals("")){
                	
                	request.setAttribute("erro", "Nome do arquivo inexistente");
                	return mapping.findForward("erro");
                }
                
                extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1, nomeArquivo.length());
                
                for(int i = 0 ; i < extensoesArray.length; i++){
                	if(extensao.equalsIgnoreCase(extensoesArray[i].trim())) {
                		errorExtension = false;
                	}
                	extensoesValidas += extensoesArray[i];
                }
                
                if(errorExtension) {
                	
                	request.setAttribute("erro", "Esta nao e uma extensao valida para o sistema. A(s) extensao(s) valida(s) são: " + extensoesValidas);
                	return mapping.findForward("erro");
                }
                
			}catch(Exception e){
				LogUtil.getLog().error(MensagemProp.getPropriedades().get("erro.validacaonota.upload"), e);
				
			}
			
			/*LE A NOTA FISCAL E VERFICA SE ELA É INVÁLIDA,
			 * A NOTA É SALVA NO DISCO SE ELA FOI VÁLIDA
			*/
			File diretorioFile = new File(ConfiguracaoProp.getPropriedades().get("notafiscal.diretorio").toString());
			File arquivo = new File( diretorioFile + "\\" + nomeArquivo);
			
		    
		    if(!diretorioFile.canWrite()){
		    	request.setAttribute("erro", "diretorio nao tem permissao de escrita, mude a permissao do diretorio");
		    	return mapping.findForward("erro");
		    }
		    
		    
		    if(!diretorioFile.isDirectory()){
		    	diretorioFile.mkdirs();
		    }
		    
			
		  try{
			  //cria arquivo e pega o stream do upload
			  OutputStream out = new FileOutputStream(arquivo);
			  OutputStreamWriter outw = new OutputStreamWriter(out);
			  BufferedWriter bfw = new BufferedWriter(outw);
			  
			  
			 
			  InputStreamReader isr = new InputStreamReader(ioStream);
			  BufferedReader br = new BufferedReader(isr);
			  String s = br.readLine();
			     
			     while (s != null) {
			    	 bfw.write(s);
			    	 s = br.readLine();
			     }
			  bfw.close();
		  } catch (IOException e) {
			  LogUtil.getLog().error("Erro ao salvar o arquivo no disco", e);
			  
			  
			  
		  }
		  
		  //Lê a nota fiscal e retorna no objeto NFE
		  NotaFiscal notaLeitura = new NotaFiscal();
		  NFE nfeLeitura = null;
		  
		  try {
			  nfeLeitura = notaLeitura.lerXML(arquivo);
			  
			  
			  
		  } catch (NullPointerException enull) {
			  request.setAttribute("erro", enull.getMessage());
			  LogUtil.getLog().error(enull.getMessage(), enull);
          	return mapping.findForward("erro");
		  } catch (NotaInvalidaException notaException) {
			  request.setAttribute("erro", "Nota inválida, verifique o log para mais detalhes");
			  LogUtil.getLog().error(notaException.getMessage(), notaException);
			  return mapping.findForward("erro");
		  }
		  
		  //Para cada item da nota, buscar o NCM com as alíquotas cadastradas

		  
		  Empresa empresa = (Empresa) request.getSession().getAttribute("empresa");
		  List<ImpostoAliquota> listaImposto = new ArrayList<ImpostoAliquota>();
		  
		  
		  for (NotaValidadaItem notaValidadaItem: nfeLeitura.getNotaValidada().getNotasValidadaItem()) {
			  
			  ImpostoAliquota impostoAliquota = new ImpostoAliquota();
			  
			  
			 //busca para o imposto
			 List<ImpostoNcm> listaImpostoNcm = ImpostoNcmDao.getInstance().buscarImpostoNcm(notaValidadaItem.getNcm().getNmNCM(),
					  			cdFinalidadeNfe, empresa.getCdcnpj());
			 
	
			 
			 if (listaImpostoNcm.isEmpty()) {
				 	request.setAttribute("erro", "NCM " + notaValidadaItem.getNcm().getNmNCM() + " não foi cadastrado para esta a finalidade");
					  return mapping.findForward("erro");
			 }
			 notaValidadaItem.getNcm().setEmpresa(empresa);
			 impostoAliquota.setNcm(notaValidadaItem.getNcm().getNmNCM());
			 
			 //alíquota ICMS
			 if (listaImpostoNcm.get(0) != null)
				 impostoAliquota.setAliquotaICMSST(listaImpostoNcm.get(0).getNuPercentualImposto());
			 
			//aliquota PIS
			 if (listaImpostoNcm.get(1) != null)
				 impostoAliquota.setAliquotaPIS(listaImpostoNcm.get(1).getNuPercentualImposto());
			 
			//alíquota COFINS
			 if (listaImpostoNcm.get(2) != null)
				 impostoAliquota.setAliquotaCOFINS(listaImpostoNcm.get(2).getNuPercentualImposto());
			 
			//alíquota IPI
			 if (listaImpostoNcm.get(3) != null)
				 impostoAliquota.setAliquotaIPI(listaImpostoNcm.get(3).getNuPercentualImposto());
			 
			//alíquota ICMS Interestadua
			 if (listaImpostoNcm.get(4) != null)
				 impostoAliquota.setAliquotaICMS(listaImpostoNcm.get(4).getNuPercentualImposto());
			 
			 //percentual de redução
			 impostoAliquota.setPercentualReducao(listaImpostoNcm.get(0).getNcm().getCsts().get(0).getNuPercentualReducao());
			 listaImposto.add(impostoAliquota);
			  
		  }
		  
		  
		  //Persiste o objeto 
		  //Object resp = NfeDao.getInstance().salvarNfe(nfeLeitura);
		  
	/*	  if (resp instanceof String) {
			  request.setAttribute("erro", resp.toString());
			 return  mapping.findForward("erro");
		  }*/
		  
		  Validacao validacao = new Validacao();
		  
		  validacao.setNfeInicial(nfeLeitura);
		  validacao.setEmpresa(empresa);
		  validacao.setDtValidacao(Calendar.getInstance());
		  
		  FinalidadeNfe finalidadeNota = FinalidadeNfeDao.getInstance().getFinalidadeNfe(cdFinalidadeNfe);
		  
		  if (finalidadeNota != null)
			  validacao.setFinalidadeNfe(finalidadeNota);
		  
		  if (cdFinalidadeNfe == CODIGO_FINALIDADE_NOTACORRETIVA) 
			   validacao.getNfeInicial().setNotaComplementar(true);
		  
		  request.getSession().setAttribute("validacaoNotaFiscal", validacao);
		  request.setAttribute("listaImposto", listaImposto);
		  
		return mapping.findForward("sucesso");
	}
	
	
}
