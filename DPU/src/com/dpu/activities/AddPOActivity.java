package com.dpu.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.dpu.adaptor.AddPOAdapter;
import com.dpu.bean.CategoryReq;
import com.dpu.bean.IssueModel;
import com.dpu.bean.PurchaseOrderModel;
import com.dpu.bean.TypeResponse;
import com.dpu.bean.VendorModel;
import com.dpu.services.util.AndroidHttpRequestSender;
import com.dpu.services.util.Config;

public class AddPOActivity extends Activity {

	final String ROOT_URL = Config.url;
	Spinner ddlVendor, ddlUnitType, ddlCategory;
	ListView lvIssues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.po_add_screen);
		initialize();
		new POCall().execute();
	}

	private void initialize() {
		ddlVendor = (Spinner) findViewById(R.id.ddlVendor);
		ddlUnitType = (Spinner) findViewById(R.id.ddlUnitType);
		ddlCategory = (Spinner) findViewById(R.id.ddlCategory);
		lvIssues = (ListView) findViewById(R.id.poListView);
		showIssues();
	}

	public PurchaseOrderModel getOpenAdd() {
		PurchaseOrderModel purchaseOrderModel = null;
		try {
			String url = ROOT_URL + "po/openAdd";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("name", name));
			String response = new AndroidHttpRequestSender().getRequest(url,
					params);
			Log.e("REQ RES ", response);
			purchaseOrderModel = convertJSONArrayIntoObject(response);
		} catch (Exception e) {
			Log.e("getAllContacts()", e.toString());
		}
		return purchaseOrderModel;
	}

	public PurchaseOrderModel convertJSONArrayIntoObject(String resp) {
		PurchaseOrderModel obj = null;
		try {
			JSONObject jsonO = new JSONObject(resp);
			obj = new PurchaseOrderModel();
			List<VendorModel> vendorList = new ArrayList<VendorModel>();
			JSONArray jsonArray = jsonO.getJSONArray("vendorList");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject js2 = jsonArray.getJSONObject(i);
				VendorModel vendorModel = new VendorModel();
				vendorModel.setVendorId(js2.getLong("vendorId"));
				vendorModel.setName(js2.getString("name"));
				vendorList.add(vendorModel);
			}
			obj.setVendorList(vendorList);

			/*
			 * List<TypeResponse> statusList = new ArrayList<TypeResponse>();
			 * JSONArray jsonArray1 = jsonO.getJSONArray("statusList"); for (int
			 * i = 0; i < jsonArray1.length(); i++) { JSONObject js2 =
			 * jsonArray1.getJSONObject(i); TypeResponse typeResponse = new
			 * TypeResponse(); typeResponse.setTypeId(js2.getLong("typeId"));
			 * typeResponse.setTypeName(js2.getString("typeName"));
			 * statusList.add(typeResponse); } obj.setStatusList(statusList);
			 */

			List<CategoryReq> categoryList = new ArrayList<CategoryReq>();
			JSONArray jsonArray2 = jsonO.getJSONArray("categoryList");
			for (int i = 0; i < jsonArray2.length(); i++) {
				JSONObject js2 = jsonArray2.getJSONObject(i);
				CategoryReq categoryReq = new CategoryReq();
				categoryReq.setCategoryId(js2.getLong("categoryId"));
				categoryReq.setName(js2.getString("name"));
				categoryList.add(categoryReq);
			}
			obj.setCategoryList(categoryList);

			List<TypeResponse> unitTypeList = new ArrayList<TypeResponse>();
			JSONArray jsonArray3 = jsonO.getJSONArray("unitTypeList");
			for (int i = 0; i < jsonArray3.length(); i++) {
				JSONObject js2 = jsonArray3.getJSONObject(i);
				TypeResponse typeResponse = new TypeResponse();
				typeResponse.setTypeId(js2.getLong("typeId"));
				typeResponse.setTypeName(js2.getString("typeName"));
				unitTypeList.add(typeResponse);
			}
			Log.e("Unit List size", unitTypeList.size() + " SS");
			obj.setUnitTypeList(unitTypeList);
		} catch (Exception e) {
			Log.e("convertJSONArrayIntoObject", e.toString());
		}
		return obj;
	}

	List<TypeResponse> unitTypeList = null;
	List<CategoryReq> categoryList = null;

	private class POCall extends AsyncTask<String, Void, PurchaseOrderModel> {

		@Override
		protected void onPostExecute(PurchaseOrderModel result) {
			super.onPostExecute(result);

			String arr[] = new String[result.getVendorList().size()];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = result.getVendorList().get(i).getName();
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					AddPOActivity.this, android.R.layout.simple_spinner_item,
					arr);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ddlVendor.setAdapter(adapter);

			unitTypeList = result.getUnitTypeList();
			String arrUnit[] = new String[result.getUnitTypeList().size()];
			for (int i = 0; i < arrUnit.length; i++) {
				arrUnit[i] = result.getUnitTypeList().get(i).getTypeName();
			}
			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
					AddPOActivity.this, android.R.layout.simple_spinner_item,
					arrUnit);
			adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ddlUnitType.setAdapter(adapter1);

			categoryList = result.getCategoryList();
			String arrCategory[] = new String[result.getCategoryList().size()];
			for (int i = 0; i < arrCategory.length; i++) {
				arrCategory[i] = result.getCategoryList().get(i).getName();
			}
			ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
					AddPOActivity.this, android.R.layout.simple_spinner_item,
					arrCategory);
			adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ddlCategory.setAdapter(adapter2);
			/*
			 * POAdaptor adaptor = new POAdaptor(POActivity.this,
			 * R.layout.po_row_layout, result); lvPOs.setAdapter(adaptor);
			 */}

		@Override
		protected PurchaseOrderModel doInBackground(String... params) {
			PurchaseOrderModel purchaseOrderModel = getOpenAdd();
			return purchaseOrderModel;
		}
	}

	private void showIssues() {

		try {

			ddlCategory.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long id) {

					Long categoryId = categoryList.get(position)
							.getCategoryId();
					Long unitTypeId = unitTypeList.get(
							ddlUnitType.getSelectedItemPosition()).getTypeId();
					new FetchPoIssues().execute(categoryId + "", unitTypeId
							+ "");
				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
				}
			});

			ddlUnitType.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long id) {

					Long unitTypeId = unitTypeList.get(position).getTypeId();
					Long categoryId = categoryList.get(
							ddlCategory.getSelectedItemPosition())
							.getCategoryId();
					new FetchPoIssues().execute(categoryId + "", unitTypeId
							+ "");
				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
				}
			});
		} catch (Exception e) {
			Log.e("getAllContacts()", e.toString());
		}
	}

	public List<IssueModel> convertJSONArrayIntoList(String resp) {
		List<IssueModel> lstRequests = new ArrayList<IssueModel>();
		try {
			JSONArray jsonArray = new JSONArray(resp);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				IssueModel obj = new IssueModel();
				obj.setTitle(jsonObject.getString("title"));
				obj.setCategoryName(jsonObject.getString("categoryName"));
				obj.setStatusName(jsonObject.getString("statusName"));
				obj.setUnitTypeName(jsonObject.getString("unitTypeName"));
				obj.setVmcName(jsonObject.getString("vmcName"));
				lstRequests.add(obj);
			}
		} catch (Exception e) {
			Log.e("convertJSONArrayIntoList", e.toString());
		}
		return lstRequests;
	}

	private class FetchPoIssues extends
			AsyncTask<String, Void, List<IssueModel>> {

		@Override
		protected void onPostExecute(List<IssueModel> result) {
			super.onPostExecute(result);
			Log.e("POISSUE list: ", result.size() + "");
			AddPOAdapter adaptor = new AddPOAdapter(AddPOActivity.this,
					R.layout.po_add_screen_row_layout, result);
			lvIssues.setAdapter(adaptor);
		}

		@Override
		protected List<IssueModel> doInBackground(String... params) {
			String url = ROOT_URL + "po/category/" + Long.parseLong(params[0])
					+ "/unitType/" + Long.parseLong(params[1]);
			Log.e("request values: ", "C: " + params[0] + "Unit: " + params[1]);
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			String response = new AndroidHttpRequestSender().getRequest(url,
					params1);
			List<IssueModel> issueList = convertJSONArrayIntoList(response);
			return issueList;
		}
	}

}
