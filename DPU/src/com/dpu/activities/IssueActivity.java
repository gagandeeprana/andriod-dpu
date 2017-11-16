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
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.dpu.adaptor.IssueAdapter;
import com.dpu.bean.CategoryReq;
import com.dpu.bean.DriverReq;
import com.dpu.bean.IssueModel;
import com.dpu.bean.TypeResponse;
import com.dpu.bean.VehicleMaintainanceCategoryModel;
import com.dpu.services.util.AndroidHttpRequestSender;
import com.dpu.services.util.Config;

public class IssueActivity extends Fragment implements OnClickListener, OnItemLongClickListener {

	ListView lvIssues;

	final String ROOT_URL = Config.url;
	Button btnAddIssue;
	View rootView = null;
	int index = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.issue_screen, container, false);

		lvIssues = (ListView) rootView.findViewById(R.id.allIssuesListView);
		new IssueCall().execute();
		initialize();
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		if(AddEditIssueActivity.result != null && AddEditIssueActivity.result.size() > 0) {
			lstIssues = AddEditIssueActivity.result;
			IssueAdapter adaptor = new IssueAdapter(getActivity(), R.layout.issue_row_layout, AddEditIssueActivity.result);
			lvIssues.setAdapter(adaptor);
		}
	}
	
	private void initialize() {
		btnAddIssue = (Button) rootView.findViewById(R.id.btnAddIssue);
		btnAddIssue.setOnClickListener(this);
		registerForContextMenu(lvIssues);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Options");
		menu.add("Edit");
		menu.add("Delete");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if(item.getTitle().equals("Edit")) {
			IssueModel issueModel = lstIssues.get(index);
			new EditIssueCall().execute(String.valueOf(issueModel.getId()));
		}
		return true;
	}

	public List<IssueModel> getAllIssues() {
		List<IssueModel> lstIssues = null;
		try {
			String url = ROOT_URL + "issue";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			String response = new AndroidHttpRequestSender().getRequest(url,
					params);
			Log.e("REQ RES ", response);
			lstIssues = convertJSONArrayIntoList(response);
		} catch (Exception e) {
			Log.e("getAllContacts()", e.toString());
		}
		return lstIssues;
	}
	
	public IssueModel getIssueById(Long issueId) {
		IssueModel issue = null;
		try {
			String url = ROOT_URL + "issue/" +issueId;
			Log.e("edit issue url : ", url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			String response = new AndroidHttpRequestSender().getRequest(url,
					params);
			Log.e("REQ RES Issue ka ", response);
			issue = convertJSONArrayIntoObject(response);
		} catch (Exception e) {
			Log.e("IssueActivity: getIssueById(): ", e.toString());
		}
		return issue;
	}

	public List<IssueModel> convertJSONArrayIntoList(String resp) {
		List<IssueModel> lstRequests = new ArrayList<IssueModel>();
		try {
			JSONArray jsonArray = new JSONArray(resp);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				IssueModel obj = new IssueModel();
				obj.setId(jsonObject.getLong("id"));
				obj.setTitle(jsonObject.getString("title"));
				obj.setVmcName(jsonObject.getString("vmcName"));
				obj.setUnitTypeName(jsonObject.getString("unitTypeName"));
				obj.setUnitNo(jsonObject.getString("unitNo"));
				obj.setReportedByName(jsonObject.getString("reportedByName"));
				obj.setStatusName(jsonObject.getString("statusName"));
				lstRequests.add(obj);
			}
		} catch (Exception e) {
			Log.e("convertJSONArrayIntoList", e.toString());
		}
		return lstRequests;
	}
	
	public IssueModel convertJSONArrayIntoObject(String resp) {
		IssueModel obj = null;
		try {
			JSONObject jsonO = new JSONObject(resp);
			obj = new IssueModel();
			List<VehicleMaintainanceCategoryModel> vmcList = new ArrayList<VehicleMaintainanceCategoryModel>();
			JSONArray jsonArray = jsonO.getJSONArray("vmcList");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject js2 = jsonArray.getJSONObject(i);
				VehicleMaintainanceCategoryModel vehicleMaintainanceCategoryModel = new VehicleMaintainanceCategoryModel();
				vehicleMaintainanceCategoryModel.setId(js2.getLong("id"));
				vehicleMaintainanceCategoryModel.setName(js2.getString("name"));
				vmcList.add(vehicleMaintainanceCategoryModel);
			}
			obj.setVmcList(vmcList);

			List<TypeResponse> statusList = new ArrayList<TypeResponse>();
			JSONArray jsonArray1 = jsonO.getJSONArray("statusList");
			for (int i = 0; i < jsonArray1.length(); i++) {
				JSONObject js2 = jsonArray1.getJSONObject(i);
				TypeResponse typeResponse = new TypeResponse();
				typeResponse.setTypeId(js2.getLong("typeId"));
				typeResponse.setTypeName(js2.getString("typeName"));
				statusList.add(typeResponse);
			}
			obj.setStatusList(statusList);

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
			obj.setUnitTypeList(unitTypeList);

			List<DriverReq> driverList = new ArrayList<DriverReq>();
			JSONArray jsonArray4 = jsonO.getJSONArray("reportedByList");
			for (int i = 0; i < jsonArray4.length(); i++) {
				JSONObject js2 = jsonArray4.getJSONObject(i);
				DriverReq driverReq = new DriverReq();
				driverReq.setDriverId(js2.getLong("driverId"));
				driverReq.setFullName(js2.getString("fullName"));
				driverList.add(driverReq);
			}
			obj.setReportedByList(driverList);
			obj.setTitle(jsonO.getString("title"));
			obj.setVmcId(jsonO.getLong("vmcId"));
			obj.setUnitTypeId(jsonO.getLong("unitTypeId"));
			obj.setCategoryId(jsonO.getLong("categoryId"));
			obj.setUnitNo(jsonO.getString("unitNo"));
			obj.setReportedById(jsonO.getLong("reportedById"));
			obj.setStatusId(jsonO.getLong("statusId"));
			obj.setId(jsonO.getLong("id"));
//			obj.getCategoryId()

		} catch (Exception e) {
			Log.e("IssueActivity: convertJSONArrayIntoObject(): ",
					e.toString());
		}
		return obj;
	}

	
	List<IssueModel> lstIssues= null;
	
	private class IssueCall extends AsyncTask<String, Void, List<IssueModel>> {

		@Override
		protected void onPostExecute(List<IssueModel> result) {
			super.onPostExecute(result);
			IssueAdapter adaptor = new IssueAdapter(getActivity(),
					R.layout.issue_row_layout, result);
			lvIssues.setAdapter(adaptor);
		}

		@Override
		protected List<IssueModel> doInBackground(String... params) {
			lstIssues = getAllIssues();
			return lstIssues;

		}
	}
	
	public static IssueModel editIssueModel = null;
	
	private class EditIssueCall extends AsyncTask<String, Void, IssueModel> {

		@Override
		protected void onPostExecute(IssueModel result) {
			super.onPostExecute(result);
			startActivity(new Intent(getActivity(), AddEditIssueActivity.class));
			editIssueModel = result;
			AddEditIssueActivity.addEditFlag = 1;
			AddEditIssueActivity.issueId = editIssueModel.getId();
		}

		@Override
		protected IssueModel doInBackground(String... params) {
			Log.e("issue id to fetch data:  ", Long.parseLong(params[0]) + "  KK");
			IssueModel issue = getIssueById(Long.parseLong(params[0]));
			return issue;

		}
	}

	@Override
	public void onClick(View v) {
		if (v == btnAddIssue) {
			startActivity(new Intent(getActivity(), AddEditIssueActivity.class));
		}

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		index = position;
		parent.showContextMenu();
		return true;
	}

}