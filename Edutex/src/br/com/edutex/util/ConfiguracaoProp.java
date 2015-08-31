package br.com.edutex.util;

import java.io.IOException;
import java.util.Properties;

public class ConfiguracaoProp {
	
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
				prop.load(MensagemProp.class.getResourceAsStream("/br/com/edutex/resources/config.properties"));
			} catch (IOException e) {
				LogUtil.getLog().error("ERRO: " + e.getMessage());
				e.printStackTrace();
				
			}
		}
		return prop;
		
	}
}
