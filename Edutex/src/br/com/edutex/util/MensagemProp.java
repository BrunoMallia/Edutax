/**
 * 
 */
package br.com.edutex.util;

import java.io.IOException;
import java.util.Properties;


/**
 * Classe que configura os arquivos de propriedades para 
 * as mensagens do sistema
 * @author bruno
 *
 */
public class MensagemProp {
	

	private static Properties prop;
	
	
	/**
	 * Por padrao do projeto as configuracoes do arquivo de 
	 * mensagens esta no pacote br.com.edutex.resources
	 * @return
	 */
	public static Properties getPropriedades(){
		
		
		
		if (prop == null) {
			try {
				prop = new Properties();
				prop.load(MensagemProp.class.getResourceAsStream("/br/com/edutex/resources/mensagens.properties"));
			} catch (IOException e) {
				LogUtil.getLog().error("ERRO: " + e.getMessage());
				e.printStackTrace();
				
			}
		}
		return prop;
		
	}
	
}
