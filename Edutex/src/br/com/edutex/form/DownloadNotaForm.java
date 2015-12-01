package br.com.edutex.form;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.tomcat.util.buf.ByteChunk.ByteOutputChannel;

import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class DownloadNotaForm extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request, HttpServletResponse response)  {
		
		ByteArrayOutputStream byteArrayOutput = (ByteArrayOutputStream) request.getSession(true).getAttribute("streamDownload");
		
	
		

		 if (byteArrayOutput != null) {
		response.setHeader("Content-disposition","attachment;filename= " + request.getSession(true).getAttribute("nomeNfe"));
        response.setContentType("application/xml");
        response.setCharacterEncoding("UTF-8");
        response.setContentLength(byteArrayOutput.size());
        
        ServletOutputStream servletOutput;
		try {
			servletOutput = response.getOutputStream();
			byteArrayOutput.writeTo(servletOutput);
		    servletOutput.flush();
		    servletOutput.close();
		        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
	  }
		
		
		return null;
		
	} 
	
}
