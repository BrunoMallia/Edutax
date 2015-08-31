package br.com.edutex.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.EstadoDao;
import br.com.edutex.DAO.FinalidadeNfeDao;
import br.com.edutex.DAO.ImpostoNcmEstadoDao;
import br.com.edutex.DAO.NcmDao;
import br.com.edutex.DAO.RegimeTributarioDao;
import br.com.edutex.logic.CST;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.Estado;
import br.com.edutex.logic.FinalidadeNfe;
import br.com.edutex.logic.ImpostoNcm;
import br.com.edutex.logic.ImpostoNcmEstado;
import br.com.edutex.logic.NCM;
import br.com.edutex.logic.RegimeTributarioEmpresa;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.logic.TipoStatus;
import br.com.edutex.logic.TipoUsuario;

/**
 * validacao:
 * 0 = parametro do id inexistente
 * -1 = parametro do id invalido
 * -2 = ncm não preenchido
 * -3 = ncm ja existe
 * -4 = icms invalido
 * -5 = finalidade da nota nao preenchida
 * -6 = ipi invalido
 * -7 = pis invalido
 * -8 = cofins invalido
 * -9 = cst invalido
 * 1 = ncm criado com sucesso
 * -10 = ncm não existe
 * -11 = ncm não preenchido
 * 2 = ncm localizado
 */

public class NcmForm extends Action{
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		List<FinalidadeNfe> listaFinalidadeNfe = FinalidadeNfeDao.getInstance().buscaFinalidadesNfe();
	
		if (!listaFinalidadeNfe.isEmpty()) {
			request.setAttribute("finalidadesNfe", listaFinalidadeNfe);
		} else {
			throw new IllegalArgumentException("Não existe finalidade da nota fiscal cadastrado no banco de dados");
		}
		
		
		return(mapping.findForward("sucesso"));
		
	}

}