<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html> 
  <head>
<jsp:include page="/header/header.jsp" />
 <script src="<%=request.getContextPath() %>/js/jquery.dataTables.min.js"></script>
<link href="<%=request.getContextPath() %>/css/bootstrap-lightbox.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/ncm.js"></script>

 <style type="text/css">

#maskNCM {
  position:absolute;
  left:0;
  top:0;
  z-index:9000;
  background-color:#000;
  display:none;
}

#boxesNCM {
  position:absolute;
  left:0;
  top:0;
  width:440px;
  height:200px;
  display:none;
  z-index:9999;
  padding:20px;
}


#boxesNCM #dialogNCM {
  width:375px; 
  height:203px;
  padding:10px;
  background-color:#ffffff;
}




#dialog1 .d-header input {
  position:relative;
  top:60px;
  left:100px;
  border:3px solid #cccccc;
  height:22px;
  width:200px;
  font-size:15px;
  padding:5px;
  margin-top:4px;
}

#boxesNCM  {
  position:absolute;
  left:0;
  top:0;
  display:block;
  z-index:9999;
  padding:20px;
}

.dataTables_length {
	padding-left: 30px;
	margin-bottom: 10px;
	padding-top: 10px;

}

.dataTables_filter {
	padding-left: 30px;
}

.dataTables_info {
	padding-left: 30px;
}

.dataTables_paginate {
	padding-left: 30px;
	padding-top: 5px;
	padding-bottom: 5px;
	margin-bottom:5px;	
}

td .sorting_1 {
	min-width:20px;
}
</style>
<script  type="text/javascript">
var contextPath='<%=request.getContextPath()%>';
 </script>
</head>

<body bgcolor="#f5f5f5">
<jsp:include page="/header/modal.jsp"></jsp:include>

	<div id="boxesNCM">
	

			<div class="icms_pop" id="divTabela">
	
			
			
			
			
		  </div>
		</div>
		<div id="maskNCM"></div>

	<header class="header_home">
        <div class="header_width">
        	 <jsp:include page="/header/cabecalho.jsp" />
	    	 <jsp:include page="/header/aba_menu.jsp" />
        </div>
      </header>

      
      <div class="main_container_default">
      <div class="container_default">
        <div class="TabControl">
          <div id="header">
            <ul class="abas">
              <li>
                <div class="aba">
                  <span>NCM/CST</span>
                </div>
              </li>
              <li>
                <div class="aba">
                  <span>Upload arquivo</span>
                </div>
              </li>
            </ul>
          </div>
          
          
          <div id="content">
            <div class="conteudo">
              <fieldset class="remove_status_ncm">
                <p class="">
                  
							<form name="NCMForm" id="NCMForm"  method="post">
							<div id="containerCampoNCM" style="display: none;margin: 0 0 0 0;">
								
								 <span >
								 	<span>Finalidade da nota</span>
									<c:forEach items="${finalidadesNfe}"  var="finalidade" >
											<input type="radio" value="${finalidade.cdFinalidadeNfe}" id="escolhaRadioFinalidade" name="escolhaRadioFinalidade" /><label><c:out value="${finalidade.nmFinalidade}"></c:out> </label>&nbsp;
									</c:forEach>
									 <br/>
									</span>
								
								<span>
								 <label>Ncm </label> 
								<input type="text" id="ncmCampo" name="ncmCampo" placeholder="ex: 79"> 
								<input type="hidden" id="cdNmc" name="cdNmc"/>
								<input class="bt_pesquisar" id="btPesquisar" value="gerar" type="button" />
								</span>
								
								<div id="textContainer1" style="display: none">
									<span class="messageError"> </span>
								</div>
								</div>
								
								<div id="containerCamposNCM" style="display: none;margin: 0 0 0 0;">
								<input type="hidden" id="tpOperacao" name="tpOperacao">
								 <span>	
									<label>Cst </label> 
								 	<input type="text" id="cst" placeholder="ex: 35" />
								 </span>
								
								<br /> 
									
									 <span>
											<table>
												<thead>
													<tr>
														<th>icms interno (%)</th>
														<th>icms interestadual (%)</th>
														<th>IPI (%)</th>
														<th>PIS (%)</th>
														<th>cofins (%)</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td><input id="icmsCampo" type="text" class="fieldsetICMS" placeholder="ex: 10"></td>
														<td><input id="icmsInterestadualCampo" class="fieldsetICMS" type="text" placeholder="ex: 10"></td>
														<td><input id="ipiCampo" type="text" class="fieldsetICMS" placeholder="ex: 10"></td>
														<td><input id="pisCampo" type="text" class="fieldsetICMS" placeholder="ex: 10"></td>
														<td><input id="cofinsCampo" type="text" class="fieldsetICMS" placeholder="ex: 10"></td>
													</tr>
												</tbody>
											</table>
									</span>
									 <span> 
									 <input class="btn btn-primary btn-lg bt_icms space"data-toggle="modal" id="icmsModal" data-target="#myModal" type="button" value="icms st" />
									
									</span>
									<span>
									<br /> <label>Percentual de redução (%) </label>
									 <input type="text" id="percReducao" placeholder="ex: 13">
									</span><br /> 
									<span> <input class="bt_cancel space" onclick="desabilitarNovoNCM();" 
										type="reset" value="cancelar" /> <input class="bt_save space" id="btSalvarEditarNCM" type="button" value="gerar" />
									</span>
									
								<div id="textContainer2" style="display: none">
									<span class="messageError"> </span>
								</div>
								</div>
							</form>
						</p>
              </fieldset>
            </div>
            <div class="conteudo" style="display: none;">
              <fieldset class="remove_status_ncm">
                <p class="">
                  <form action="" method="post">
                  <span>
                    <input type="text" placeholder="Ncm" class="greatmail">
                  </span>
                  <p class="">
                  <input class="bt_cancel space" type="reset" value="cancelar" />
                  <a href="alterar_usuario.html" class=""><input class="bt_check space" value="gerar"/></a>
                  </p>
                  </form>
                </p>
              </fieldset>
            </div>
            <div class="conteudo" style="display: none;">
              <fieldset class="remove_status">
                <p class="">
                  <form action="" method="post">
                  <span>
                    <input type="text" placeholder="Ncm" class="greatmail">
                  </span>
                  <p class="">
                  <input class="bt_cancel space" type="reset" value="cancelar" />
                  <input class="bt_check space" type="submit" value="gerar" />
                  </p>
                  </form>
                </p>
              </fieldset>
            </div>
            <div class="conteudo" style="display: none;">
              <fieldset class="remove_status_ncm">
                <p class="">
                  <form action="" method="post">
                  <span>
                    <label>Arquivo</label>
                    <input type="file">
                  </span>
                  <p class="">
                  <input class="bt_cancel space" type="reset" value="cancelar" />
                  <input class="bt_check space" type="button" value="gerar" />
                  </p>
                  </form>
                </p>
              </fieldset>
            </div>
          </div>
        </div>
      </div>
      </div>
	  
	  
      <footer class="footer_login">Sistema Gerenciamento de Cosultoria e Contabilidade  -  Versão 1.0  -  Desenvolvido por: Grupo Academico. </footer>
    </body>
</html>
