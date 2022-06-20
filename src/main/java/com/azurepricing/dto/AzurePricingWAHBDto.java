package com.azurepricing.dto;

public class AzurePricingWAHBDto {
	private String Region;
	private String Instance;
	private String Cores;
	private String Ram;
	private String Storage;
	private String PayAsYouGo;
	private String OneYearReserved;
	private String ThreeYearReserved;
	private String Spot;
	public AzurePricingWAHBDto() {
	}
	public AzurePricingWAHBDto(String region, String instance, String cores, String ram, String storage,
			String payAsYouGo, String oneYearReserved, String threeYearReserved, String spot) {
		super();
		Region = region;
		Instance = instance;
		Cores = cores;
		Ram = ram;
		Storage = storage;
		PayAsYouGo = payAsYouGo;
		OneYearReserved = oneYearReserved;
		ThreeYearReserved = threeYearReserved;
		Spot = spot;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public String getInstance() {
		return Instance;
	}
	public void setInstance(String instance) {
		Instance = instance;
	}
	public String getCores() {
		return Cores;
	}
	public void setCores(String cores) {
		Cores = cores;
	}
	public String getRam() {
		return Ram;
	}
	public void setRam(String ram) {
		Ram = ram;
	}
	public String getStorage() {
		return Storage;
	}
	public void setStorage(String storage) {
		Storage = storage;
	}
	public String getPayAsYouGo() {
		return PayAsYouGo;
	}
	public void setPayAsYouGo(String payAsYouGo) {
		PayAsYouGo = payAsYouGo;
	}
	public String getOneYearReserved() {
		return OneYearReserved;
	}
	public void setOneYearReserved(String oneYearReserved) {
		OneYearReserved = oneYearReserved;
	}
	public String getThreeYearReserved() {
		return ThreeYearReserved;
	}
	public void setThreeYearReserved(String threeYearReserved) {
		ThreeYearReserved = threeYearReserved;
	}
	public String getSpot() {
		return Spot;
	}
	public void setSpot(String spot) {
		Spot = spot;
	}
	
}
