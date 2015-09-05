package br.com.edutex.form;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.EmpresaDao;
import br.com.edutex.DAO.TipoUsuarioDao;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.TipoUsuario;
import br.com.edutex.logic.Usuario;
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
		
		
	    initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource dataSource = (DataSource) envContext.lookup(datasourceName);
		Connection con = dataSource.getConnection();
		
		
		
		
		 PreparedStatement statement = null;  
	        ResultSet rs = null;  
	        try {  
	            statement = con.prepareStatement("SELECT * FROM notascomplementaresporempresa");  
	           
	            
	            rs = statement.executeQuery();  
	            JRResultSetDataSource jrDataSource = new JRResultSetDataSource(rs);
	            JasperPrint jp = JasperFillManager.fillReport("ireport/edutNotasComplementaresPorEmpresa.jasper", new HashMap(),jrDataSource);
	            JasperViewer jasperViewer = new JasperViewer(jp);
	            jasperViewer.setVisible(true); 
	            
	            while (rs.next()) {
	            	
	                  
	            } 
	        } catch (SQLException e) {  
	              
	        } 
		
		
		return null;
	}
	

	
}
	

	
	
	