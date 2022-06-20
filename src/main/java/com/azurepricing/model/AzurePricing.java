package com.azurepricing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pricing_table")
public class AzurePricing {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long sNo;
	
	@Column(name="region")
	private String Region;
	
	@Column(name="instance")
	private String Instance;
	
	@Column(name="cores")
	private String Cores;
	
	@Column(name="ram")
	private String Ram;
	
	@Column(name="storage")
	private String Storage;
	
	@Column(name="pay_as_you_go")
	private String PayAsYouGo;
	
	@Column(name="one_year_reserved")
	private String OneYearReserved;

	@Column(name="three_year_reserved")
	private String ThreeYearReserved;
	
	@Column(name="spot")
	private String Spot;
	
	@Column(name="pay_as_you_go_ahb")
	private String PayAsYouGoAHB;
	
	@Column(name="one_year_reserved_ahb")
	private String OneYearReservedAHB;

	@Column(name="three_year_reserved_ahb")
	private String ThreeYearReservedAHB;
	
	@Column(name="spot_ahb")
	private String SpotAHB;
	
	public AzurePricing() {
		
	}

	public AzurePricing(String region, String instance, String cores, String ram, String storage, String payAsYouGo,
			String oneYearReserved, String threeYearReserved, String spot, String payAsYouGoAHB,
			String oneYearReservedAHB, String threeYearReservedAHB, String spotAHB) {
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
		PayAsYouGoAHB = payAsYouGoAHB;
		OneYearReservedAHB = oneYearReservedAHB;
		ThreeYearReservedAHB = threeYearReservedAHB;
		SpotAHB = spotAHB;
	}

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
		this.sNo = sNo;
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
