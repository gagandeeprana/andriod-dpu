package com.dpu.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.dpu.adaptor.POAdaptor;
import com.dpu.bean.PurchaseOrderModel;
import com.dpu.services.util.AndroidHttpRequestSender;
import com.dpu.services.util.Config;

public class POActivity extends Fragment implements OnClickListener {

	// Root URL of our web service
	ListView lvPOs;

	private List<PurchaseOrderModel> pos;
	final String ROOT_URL = Config.url;
	Button btnAddPO;
	View rootView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.po_screen, container, false);

		lvPOs = (ListView) rootView.findViewById(R.id.poListView);
		new POCall().execute();
		initialize();
		return rootView;
	}

	private void initialize() {
		btnAddPO = (Button) rootView.findViewById(R.id.btnAddPO);
		btnAddPO.setOnClickListener(this);
	}

	public List<PurchaseOrderModel> getAllPOs() {
		List<PurchaseOrderModel> lstPOs = null;
		try {
//			String url = ROOT_URL + "po/status/Active";
			String url = ROOT_URL + "po";
			Log.e("REQ URL ", url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("name", name));
			String response = new AndroidHttpRequestSender().getRequest(url,
					params);
			Log.e("REQ RES ", response);
			lstPOs = convertJSONArrayIntoList(response);
		} catch (Exception e) {
			Log.e("getAllContacts()", e.toString());
		}
		return lstPOs;
	}

	public List<PurchaseOrderModel> convertJSONArrayIntoList(String resp) {
		List<PurchaseOrderModel> lstRequests = new ArrayList<PurchaseOrderModel>();
		try {
			JSONArray jsonArray = new JSONArray(resp);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				PurchaseOrderModel obj = new PurchaseOrderModel();
				obj.setPoNo(jsonObject.getLong("poNo"));
				obj.setTitle(jsonObject.getString("message"));
				obj.setStatusName(jsonObject.getString("statusName"));
				obj.setUnitTypeName(jsonObject.getString("unitTypeName"));
				obj.setCategoryName(jsonObject.getString("categoryName"));
				obj.setVendorName(jsonObject.getString("vendorName"));
				// obj.setContact(jsonObject.getString("contact"));
				// obj.setName(jsonObject.getString("name"));
				lstRequests.add(obj);
			}
		} catch (Exception e) {
			Log.e("convertJSONArrayIntoList", e.toString());
		}
		return lstRequests;
	}

	private class POCall extends
			AsyncTask<String, Void, List<PurchaseOrderModel>> {

		@Override
		protected void onPostExecute(List<PurchaseOrderModel> result) {
			super.onPostExecute(result);
			POAdaptor adaptor = new POAdaptor(getActivity(),
					R.layout.po_row_layout, result);
			lvPOs.setAdapter(adaptor);
		}

		@Override
		protected List<PurchaseOrderModel> doInBackground(String... params) {
			List<PurchaseOrderModel> lstPOs = getAllPOs();
			return lstPOs;

		}
	}

	@Override
	public void onClick(View v) {
		if (v == btnAddPO) {
			startActivity(new Intent(getActivity(), AddPOActivity.class));
		}

	}

}