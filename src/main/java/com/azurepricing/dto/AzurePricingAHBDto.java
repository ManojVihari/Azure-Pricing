package com.azurepricing.dto;

public class AzurePricingAHBDto {

	private String Instance;
	private String PayAsYouGoAHB;
	private String OneYearReservedAHB;
	private String ThreeYearReservedAHB;
	private String SpotAHB;
	
	public AzurePricingAHBDto() {
		
	}

	public AzurePricingAHBDto(String instance, String payAsYouGoAHB, String oneYearReservedAHB,
			String threeYearReservedAHB, String spotAHB) {
		super();
		Instance = instance;
		PayAsYouGoAHB = payAsYouGoAHB;
		OneYearReservedAHB = oneYearReservedAHB;
		ThreeYearReservedAHB = threeYearReservedAHB;
		SpotAHB = spotAHB;
	}

	public String getInstance() {
		return Instance;
	}

	public void setInstance(String instance) {
		Instance = instance;
	}

	public String getPayAsYouGoAHB() {
		return PayAsYouGoAHB;
	}

	public void setPayAsYouGoAHB(String payAsYouGoAHB) {
		PayAsYouGoAHB = payAsYouGoAHB;
	}

	public String getOneYearReservedAHB() {
		return OneYearReservedAHB;
	}

	public void setOneYearReservedAHB(String oneYearReservedAHB) {
		OneYearReservedAHB = oneYearReservedAHB;
	}

	public String getThreeYearReservedAHB() {
		return ThreeYearReservedAHB;
	}

	public void setThreeYearReservedAHB(String threeYearReservedAHB) {
		ThreeYearReservedAHB = threeYearReservedAHB;
	}

	public String getSpotAHB() {
		return SpotAHB;
	}

	public void setSpotAHB(String spotAHB) {
		SpotAHB = spotAHB;
	}
	
	


	
}
