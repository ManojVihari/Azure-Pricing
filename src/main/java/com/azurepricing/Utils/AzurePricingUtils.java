package com.azurepricing.Utils;

public class AzurePricingUtils {
	
	public static String parseCost(String Cost) {
		
		int firstDigitOffset=-1;
		int seperatorOffSet=Cost.indexOf('/');
		
		if(seperatorOffSet==-1) {
			seperatorOffSet=Cost.indexOf(" per ");
		}
		if(seperatorOffSet==-1) {
			seperatorOffSet=Cost.indexOf("(~");
		}
		if(seperatorOffSet==-1) {
			seperatorOffSet=Cost.length();
		}
		for(int costTextOffset=0;costTextOffset<Cost.length();costTextOffset++)
		{
			if(Cost.charAt(costTextOffset)>='0' && Cost.charAt(costTextOffset)<='9')
			{
				firstDigitOffset=costTextOffset;
				break;
			}
		}
		String priceWithoutCurrencyAndDuration=null;
		
		if(firstDigitOffset>-1 && seperatorOffSet>firstDigitOffset ) {
			
		 priceWithoutCurrencyAndDuration=Cost.substring(firstDigitOffset,seperatorOffSet);
			
		}
		
		int lastIndexofDot=priceWithoutCurrencyAndDuration.lastIndexOf('.');
		int lastIndexofComma=priceWithoutCurrencyAndDuration.lastIndexOf(',');
		if(lastIndexofComma>-1 && lastIndexofComma>lastIndexofDot ) {
			 priceWithoutCurrencyAndDuration=priceWithoutCurrencyAndDuration.replace('.','\u0000').replace(',','.');
			}else if(lastIndexofComma>-1){
				priceWithoutCurrencyAndDuration=priceWithoutCurrencyAndDuration.replace(',','\u0000');
			}
		return priceWithoutCurrencyAndDuration;
	}

}
