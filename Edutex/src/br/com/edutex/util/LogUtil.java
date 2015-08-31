/**
 * 
 */
package br.com.edutex.util;

import org.apache.log4j.Logger;

/**
 * Classe que globaliza o objeto de log
 * e seta as configuracoes dos mesmos
 * @author bruno
 *
 */
public class LogUtil {
	
	 private static Logger log;
	
	/**
	 *  Recupera o objeto global root logger
	 * @return Logger
	 */
	public static Logger getLog()
	{
		if (log == null)
			log =  Logger.getRootLogger();
		
		return log;
		
		
	}
}
