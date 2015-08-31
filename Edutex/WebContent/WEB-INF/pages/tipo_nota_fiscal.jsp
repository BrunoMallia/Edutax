<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<jsp:include page="/header/header.jsp" />
</head>


<body>
 <header class="header_home">
     <div class="header_width">
       <jsp:include page="/header/cabecalho.jsp" />
       <jsp:include page="/header/aba_menu.jsp" /> 
     </div>
   </header> 
 <div class="main_container_default">
      <div class="container_default">
        <fieldset>
          <legend>Tipo de Nota Fiscal</legend>
          <div class="">
            <form action="notafiscal/validaRegraFiscal.do" method="post">
            	<div class="">
						
							<c:if test="${!empty erro}">
									
									<span>${erro}</span>
							
							</c:if>
						
							<c:if test="${empty erro}">
								<table id="tabelaNotaValidadeFiscal">
									<thead>
										<tr>
											<td class="colunaValidaNotaFiscal">NCM</td>
											<td class="colunaValidaNotaFiscal">Percentual de redução</td>
											<td class="colunaValidaNotaFiscal">ICMS</td>
											<td class="colunaValidaNotaFiscal">IPI</td>
											<td class="colunaValidaNotaFiscal">ICMS Interestadual</td>
											<td class="colunaValidaNotaFiscal">PIS</td>
											<td class="colunaValidaNotaFiscal">COFINS</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listaImposto}" var="imposto"
											varStatus="indice">
											<tr class="dif">
												<td class="colunaValidaNotaFiscal">${imposto.ncm}</td>
												<td class="colunaValidaNotaFiscal">${imposto.percentualReducao}</td>
												<td class="colunaValidaNotaFiscal">${imposto.aliquotaICMS}</td>
												<td class="colunaValidaNotaFiscal">${imposto.aliquotaIPI}</td>
												<td class="colunaValidaNotaFiscal">${imposto.aliquotaICMSST}</td>
												<td class="colunaValidaNotaFiscal">${imposto.aliquotaPIS}</td>
												<td class="colunaValidaNotaFiscal">${imposto.aliquotaCOFINS}</td>

											</tr>

										</c:forEach>


									</tbody>

								</table>
							</c:if>

						</div>
            <div class="">
            <input class="bt_cancel space" type="reset" value="cancelar" onclick="window.history.back();"/>
           		<c:if test="${empty erro}">
            		<input class="bt_generate space" type="submit" value="gerar" />
                </c:if>
                  
            </div>
            </form>
          </div>
        </fieldset>
      </div>
      </div>
        
      <jsp:include page="/footer/rodape.jsp" />
</html>