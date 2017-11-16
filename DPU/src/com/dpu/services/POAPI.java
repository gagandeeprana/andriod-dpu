package com.dpu.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import com.dpu.bean.PurchaseOrderModel;
import com.dpu.services.util.Config;

public interface POAPI {

	final String poUrl = Config.url + "po";
	
	@GET("po")
    public Call<List<PurchaseOrderModel>> getPO();
}
