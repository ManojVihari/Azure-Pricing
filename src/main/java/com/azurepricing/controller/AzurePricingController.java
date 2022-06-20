package com.azurepricing.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azurepricing.Dao.AzurePricingDao;
import com.azurepricing.dto.AzurePricingAHBDto;
import com.azurepricing.dto.AzurePricingWAHBDto;
import com.azurepricing.model.AzurePricing;
import com.azurepricing.service.AzurePricingService;

@RestController
public class AzurePricingController {

	@Autowired
	private AzurePricingService pricingService;
	
	@Autowired
	private AzurePricingDao pricingDao;
	
	@GetMapping("/api/v1/getAzurePrices")
	public String getAzurePrices() throws InterruptedException {
		String TimeUnit="hour";
		String Os="windows";
		String Region="us-east-2";
		String Currency="usd";
		
		
		Map<String, AzurePricingWAHBDto> AzurePricingWAHB=pricingService.GetPricesWAHB(TimeUnit,Os,Region,Currency);
		Map<String, AzurePricingAHBDto> AzurePricing=pricingService.GetPrices(TimeUnit,Os,Region,Currency);
		//CompletableFuture.allOf(AzurePricingWAHB,AzurePricing).join();
		List<AzurePricing> totalData=new ArrayList<AzurePricing>();
		 for (String i : AzurePricingWAHB.keySet()) 
	     {
			AzurePricing data=new AzurePricing();
			data.setRegion(AzurePricingWAHB.get(i).getRegion());
			data.setInstance(AzurePricingWAHB.get(i).getInstance());
			data.setCores(AzurePricingWAHB.get(i).getCores());
			data.setRam(AzurePricingWAHB.get(i).getRam());
			data.setStorage(AzurePricingWAHB.get(i).getStorage());
			data.setOneYearReserved(AzurePricingWAHB.get(i).getOneYearReserved());
			data.setPayAsYouGo(AzurePricingWAHB.get(i).getPayAsYouGo());
			data.setThreeYearReserved(AzurePricingWAHB.get(i).getThreeYearReserved());
			data.setSpot(AzurePricingWAHB.get(i).getSpot());
			data.setOneYearReservedAHB(AzurePricing.get(i).getOneYearReservedAHB());
			data.setPayAsYouGoAHB(AzurePricing.get(i).getPayAsYouGoAHB());
			data.setThreeYearReservedAHB(AzurePricing.get(i).getThreeYearReservedAHB());
			data.setSpotAHB(AzurePricing.get(i).getSpotAHB());
			totalData.add(data);
		}
	
		 pricingDao.saveAll(totalData);
		return "Cheers!!";
		
	}
	
	
}
