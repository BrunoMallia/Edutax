package br.com.edutex.util;


public class NumeroFormato {
	
	public static float getNumero2digitos(float numero) {
		float number = numero * 100;
		int numB = (int) number;
		
		return  ((float)(numB % 100) / 100) + (numB/100);
	 
}
	
}
