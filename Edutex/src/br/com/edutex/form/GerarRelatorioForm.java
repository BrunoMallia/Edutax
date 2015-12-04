package br.com.edutex.form;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import br.com.edutex.DAO.UsuarioDao;

/**
 * validacao:
 * 2 = Email de acordo com o padrao
 * 3 = Email fora do padrao
 * 4 = Email desativado
 */


public class GerarRelatorioForm extends Action {
	
	private InitialContext	initContext = null;
	protected String datasourceName = "jdbc/Edutdb";

	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		JasperReport jasperReport = null;
		String nomeRelatorio = null;
	    initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource dataSource = (DataSource) envContext.lookup(datasourceName);
		Connection con = dataSource.getConnection();
		
		
		String tipoRelatorioParam = request.getParameter("tipoRelatorio");
		
		if (tipoRelatorioParam != null) {
				switch (tipoRelatorioParam) {
				case "1":
					jasperReport=(JasperReport)JRLoader.loadObject(getClass().getResource("/br/com/edutex/ireport/edutNotasComplementaresPorEmpresa.jasper"));
					nomeRelatorio = new String("relatorioNotaComplementarPorEmpresa.pdf");
					break;
				
				}

		}
		
	        try {  
	       
	            
	            
	            
	            List<Object[]> listaObjetos = GerarRelatorioDao.getInstance().preencherRelatorioNotasComplementares(1);
	            
	            JRBeanCollectionDataSource beanollectionDataSource = new JRBeanCollectionDataSource(listaObjetos);
	            JasperPrint jp = JasperFillManager.fillReport(jasperReport, new HashMap(),beanollectionDataSource);
	            
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
	

	
	
	