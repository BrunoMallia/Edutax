package br.com.edutex.form;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import com.lowagie.text.pdf.codec.Base64.InputStream;

import br.com.edutex.DAO.EmpresaDao;
import br.com.edutex.DAO.GerarRelatorioDao;
import br.com.edutex.DAO.TipoUsuarioDao;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.NFE;
import br.com.edutex.logic.TipoUsuario;
import br.com.edutex.logic.Usuario;
import br.com.edutex.logic.Validacao;
import br.com.edutex.logic.ValidacaoErro;
import br.com.edutex.notafiscal.util.ValidacaoAux;
import br.com.edutex.DAO.UsuarioDao;

/**
 * validacao:
 * 2 = Email de acordo com o padrao
 * 3 = Email fora do padrao
 * 4 = Email desativado
 */


public class GerarRelatorioForm extends Action {
	

	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		JasperReport jasperReport = null;
		String nomeRelatorio = null;
	
		
		
		String tipoRelatorioParam = request.getParameter("tipoRelatorio");
		List<Validacao> listaValidacao = null;
		List<ValidacaoErro> listaValidacaoErro = null;
        
        String parametroDataInicial = request.getParameter("dataInicio");
        String parametroDataFinal = request.getParameter("dataFinal");
        String[] parametrosEmpresa = request.getParameter("empresaSelect").split(";");
        
        int empresaId = Integer.parseInt(parametrosEmpresa[0]);
        JRBeanCollectionDataSource beanollectionDataSource = null;
		
		
		if (tipoRelatorioParam != null) {
				switch (tipoRelatorioParam) {
				case "1":
					jasperReport=(JasperReport)JRLoader.loadObject(getClass().getResource("/br/com/edutex/ireport/edutNotasComplementaresPorEmpresa.jasper"));
					nomeRelatorio = new String("relatorioNotaComplementarPorEmpresa.pdf");
			        if (parametroDataInicial.equals("") ) {
			        	if (empresaId != 0)
			        		listaValidacao = GerarRelatorioDao.getInstance().preencherRelatorioNotasComplementares(empresaId);
			        	else
			        		listaValidacao = GerarRelatorioDao.getInstance().preencherRelatorioNotasComplementares();
			        	
		            } else {
		            	if (empresaId != 0)
		            		listaValidacao = GerarRelatorioDao.getInstance().preencherRelatorioNotasComplementaresPorData(empresaId, parametroDataInicial, parametroDataFinal);
		            	else
		            		listaValidacao = GerarRelatorioDao.getInstance().preencherRelatorioNotasComplementaresPorData(parametroDataInicial, parametroDataFinal);
		            }
			        
			        beanollectionDataSource = new JRBeanCollectionDataSource(listaValidacao);
		    
					break;
				
				case "2":
					jasperReport=(JasperReport)JRLoader.loadObject(getClass().getResource("/br/com/edutex/ireport/edutNotasRejeitadasPorEmpresa.jasper"));
					nomeRelatorio = new String("relatorioNotaRejeitadasPorEmpresa.pdf");
					if (parametroDataInicial.equals("")) {
						if(empresaId != 0) {
							listaValidacaoErro = GerarRelatorioDao.getInstance().preencherRelatorioNotasRejeitadas(empresaId);
						} else {
							listaValidacaoErro = GerarRelatorioDao.getInstance().preencherRelatorioNotasRejeitadas();
						}
						
					} else {
						if (empresaId != 0)
							listaValidacaoErro = GerarRelatorioDao.getInstance().preencherRelatorioNotasRejeitadasPorData(empresaId, parametroDataInicial, parametroDataFinal);
						else 
							listaValidacaoErro = GerarRelatorioDao.getInstance().preencherRelatorioNotasRejeitadasPorData(parametroDataInicial, parametroDataFinal);
					}
					
					beanollectionDataSource = new JRBeanCollectionDataSource(listaValidacaoErro);
					break;
					
				case "3":	
					jasperReport=(JasperReport)JRLoader.loadObject(getClass().getResource("/br/com/edutex/ireport/edutNotasAceitasPorEmpresa.jasper"));
					nomeRelatorio = new String("relatorioAceitasPorEmpresa.pdf");
					if (parametroDataInicial.equals("")) {
						if (empresaId != 0)
						listaValidacao = GerarRelatorioDao.getInstance().preencherRelatorioNotasAceitas(empresaId);
						else
						listaValidacao = GerarRelatorioDao.getInstance().preencherRelatorioNotasAceitas();	
					} else {
						if (empresaId != 0)
							listaValidacao = GerarRelatorioDao.getInstance().preencherRelatorioNotasAceitas(empresaId, parametroDataInicial, parametroDataFinal);
						else
							listaValidacao = GerarRelatorioDao.getInstance().preencherRelatorioNotasAceitas(parametroDataInicial, parametroDataFinal);
						
					}
					beanollectionDataSource = new JRBeanCollectionDataSource(listaValidacao);
					break;
					
				case "4":
					jasperReport=(JasperReport)JRLoader.loadObject(getClass().getResource("/br/com/edutex/ireport/edutNotasAceitasPorEmpresa.jasper"));
					nomeRelatorio = new String("relatorioBaseCaulculoAtacadistaPorEmpresa.pdf");
					List<ValidacaoAux> listaValidacaoAux = GerarRelatorioDao.getInstance().preencherRelatorioNotasAtacadistaPorEmpresa(empresaId);
					beanollectionDataSource = new JRBeanCollectionDataSource(listaValidacaoAux);
					break;
							
				
				}

		}
		
	        try {  
	        	Map<String,Object> map = new HashMap<String,Object>();
	        	if (parametrosEmpresa.length > 1) {
	        		map.put("nomeEmpresa", parametrosEmpresa[1].toString());
	        	}
	        	
	            JasperPrint jp = JasperFillManager.fillReport(jasperReport, map,beanollectionDataSource);
	            
	            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
	            byteOut.write(JasperExportManager.exportReportToPdf(jp));
	            byteOut.close();
	            
	            response.setHeader("Content-disposition", "inline; filename=" + nomeRelatorio);
	            response.setContentType("application/pdf");
	            response.setContentLength(byteOut.size());
	            
	            ServletOutputStream servletOutput = response.getOutputStream();
	            
	            byteOut.writeTo(servletOutput);
	            servletOutput.flush();
	            servletOutput.close();
	          
	        } catch (Exception e) {  
	              e.printStackTrace();
	        } 
		
		
		return null;
	}
	

	
}
	

	
	
	