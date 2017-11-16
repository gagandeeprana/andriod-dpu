package com.dpu.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dpu.bean.CategoryReq;
import com.dpu.bean.DriverReq;
import com.dpu.bean.IssueModel;
import com.dpu.bean.TypeResponse;
import com.dpu.bean.VehicleMaintainanceCategoryModel;
import com.dpu.services.util.AndroidHttpRequestSender;
import com.dpu.services.util.Config;

public class AddEditIssueActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {

	final String ROOT_URL = Config.url;
	Spinner ddlVMC, ddlUnitType, ddlCategory, ddlReportedBy, ddlStatus,
			ddlUnitNo;
	EditText etTitle, etDescription;
	Button btnSaveIssue;
	public static int addEditFlag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.issue_add_screen);
		initialize();
		new OpenAddCall().execute();
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (addEditFlag == 1 && IssueActivity.editIssueModel != null) {
			IssueModel issue = IssueActivity.editIssueModel;
			new GetUnitNos().execute(issue.getCategoryId() + "",
					issue.getUnitTypeId() + "");
			setValues(issue);
			btnSaveIssue.setText("Update");
		}
	}

	private void setValues(IssueModel issue) {

		etTitle.setText(issue.getTitle());
		vmcList = issue.getVmcList();
		String arr[] = new String[vmcList.size()];
		int vmcToShow = 0;
		for (int i = 0; i < arr.length; i++) {
			if (issue.getVmcId() == vmcList.get(i).getId()) {
				vmcToShow = i;
			}
			arr[i] = vmcList.get(i).getName();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				AddEditIssueActivity.this,
				android.R.layout.simple_spinner_item, arr);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ddlVMC.setAdapter(adapter);
		ddlVMC.setSelection(vmcToShow);

		unitTypeList = issue.getUnitTypeList();
		String arrUnitType[] = new String[unitTypeList.size()];
		int unitTypeToShow = 0;
		for (int i = 0; i < arrUnitType.length; i++) {
			if (issue.getUnitTypeId() == unitTypeList.get(i).getTypeId()) {
				unitTypeToShow = i;
			}
			arrUnitType[i] = unitTypeList.get(i).getTypeName();
		}

		adapter = new ArrayAdapter<String>(AddEditIssueActivity.this,
				android.R.layout.simple_spinner_item, arrUnitType);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ddlUnitType.setAdapter(adapter);
		ddlUnitType.setSelection(unitTypeToShow);

		categoryList = issue.getCategoryList();
		String arrCategory[] = new String[categoryList.size()];
		int categoryToShow = 0;
		for (int i = 0; i < arrCategory.length; i++) {
			if (issue.getCategoryId() == categoryList.get(i).getCategoryId()) {
				categoryToShow = i;
			}
			arrCategory[i] = categoryList.get(i).getName();
		}

		adapter = new ArrayAdapter<String>(AddEditIssueActivity.this,
				android.R.layout.simple_spinner_item, arrCategory);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ddlCategory.setAdapter(adapter);
		ddlCategory.setSelection(categoryToShow);

		unitNos = issue.getUnitNos();
		String arrUnitNo[] = null;
		if (unitNos != null && unitNos.size() > 0) {

			arrUnitNo = new String[unitNos.size()];
			int unitNoToShow = 0;
			for (int i = 0; i < arrUnitNo.length; i++) {
				if (issue.getUnitNo().equals(unitNos.get(i))) {
					unitNoToShow = i;
				}
				arrUnitNo[i] = unitNos.get(i);
			}

			adapter = new ArrayAdapter<String>(AddEditIssueActivity.this,
					android.R.layout.simple_spinner_item, arrUnitNo);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ddlUnitNo.setAdapter(adapter);
			ddlUnitNo.setSelection(unitNoToShow);
		}

		driverList = issue.getReportedByList();
		String arrDriver[] = new String[driverList.size()];
		int driverToShow = 0;
		for (int i = 0; i < arrDriver.length; i++) {
			if (issue.getReportedById().equals(driverList.get(i).getDriverId())) {
				driverToShow = i;
			}
			arrDriver[i] = driverList.get(i).getFullName();
		}

		adapter = new ArrayAdapter<String>(AddEditIssueActivity.this,
				android.R.layout.simple_spinner_item, arrDriver);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ddlReportedBy.setAdapter(adapter);
		ddlReportedBy.setSelection(driverToShow);

		statusList = issue.getStatusList();
		String arrStatus[] = new String[statusList.size()];
		int statusToShow = 0;
		for (int i = 0; i < arrStatus.length; i++) {
			if (issue.getStatusId().equals(statusList.get(i).getTypeName())) {
				statusToShow = i;
			}
			arrStatus[i] = statusList.get(i).getTypeName();
		}

		adapter = new ArrayAdapter<String>(AddEditIssueActivity.this,
				android.R.layout.simple_spinner_item, arrStatus);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ddlStatus.setAdapter(adapter);
		ddlStatus.setSelection(statusToShow);
	}

	private void initialize() {
		ddlVMC = (Spinner) findViewById(R.id.ddlVMCAddIssue);
		ddlUnitType = (Spinner) findViewById(R.id.ddlUnitTypeAddIssue);
		ddlCategory = (Spinner) findViewById(R.id.ddlCategoryAddIssue);
		ddlReportedBy = (Spinner) findViewById(R.id.ddlReportedByAddIssue);
		ddlStatus = (Spinner) findViewById(R.id.ddlStatusAddIssue);
		ddlUnitNo = (Spinner) findViewById(R.id.ddlUnitNoAddIssue);
		etTitle = (EditText) findViewById(R.id.etTitleAddIssue);
		etDescription = (EditText) findViewById(R.id.etDescriptionAddIssue);
		btnSaveIssue = (Button) findViewById(R.id.btnAddEditIssue);
		btnSaveIssue.setOnClickListener(this);
		ddlUnitType.setOnItemSelectedListener(this);
		ddlCategory.setOnItemSelectedListener(this);
	}

	public IssueModel getOpenAdd() {
		IssueModel issueModel = null;
		try {
			String url = ROOT_URL + "issue/openAdd";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			String response = new AndroidHttpRequestSender().getRequest(url,
					params);
			issueModel = convertJSONArrayIntoObject(response);
		} catch (Exception e) {
			Log.e("AddEditIssueActivity: getOpenAdd()", e.toString());
		}
		return issueModel;
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

			/*List<CategoryReq> categoryList = new ArrayList<CategoryReq>();
			JSONArray jsonArray2 = jsonO.getJSONArray("categoryList");
			for (int i = 0; i < jsonArray2.length(); i++) {
				JSONObject js2 = jsonArray2.getJSONObject(i);
				CategoryReq categoryReq = new CategoryReq();
				categoryReq.setCategoryId(js2.getLong("categoryId"));
				categoryReq.setName(js2.getString("name"));
				categoryList.add(categoryReq);
			}
			obj.setCategoryList(categoryList);*/

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

		} catch (Exception e) {
			Log.e("AddEditIssueActivity: convertJSONArrayIntoObject(): ",
					e.toString());
		}
		return obj;
	}

	List<TypeResponse> unitTypeList = null;
	List<CategoryReq> categoryList = null;
	List<VehicleMaintainanceCategoryModel> vmcList = null;
	List<DriverReq> driverList = null;
	List<TypeResponse> statusList = null;

	private class OpenAddCall extends AsyncTask<String, Void, IssueModel> {

		@Override
		protected void onPostExecute(IssueModel result) {
			super.onPostExecute(result);

			vmcList = result.getVmcList();
			String arr[] = new String[vmcList.size()+1];
			arr[0] = "Please Select";
			for (int i = 0; i < arr.length-1; i++) {
				arr[i+1] = vmcList.get(i).getName();
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					AddEditIssueActivity.this,
					android.R.layout.simple_spinner_item, arr);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ddlVMC.setAdapter(adapter);

			unitTypeList = result.getUnitTypeList();

			if (unitTypeList != null && unitTypeList.size() > 0) {
				String arrUnit[] = new String[result.getUnitTypeList().size()+1];
				arrUnit[0] = "Please Select";
				for (int i = 0; i < arrUnit.length-1; i++) {
					arrUnit[i+1] = result.getUnitTypeList().get(i).getTypeName();
				}
				ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
						AddEditIssueActivity.this,
						android.R.layout.simple_spinner_item, arrUnit);
				adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				ddlUnitType.setAdapter(adapter1);
			}

			String arrCategory[] = new String[1];
			arrCategory[0] = "Please Select";

			ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
					AddEditIssueActivity.this,
					android.R.layout.simple_spinner_item, arrCategory);
			adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ddlCategory.setAdapter(adapter2);
			
			String arrUnitNo[] = new String[1];
			arrUnitNo[0] = "Please Select";

			ArrayAdapter<String> adapterUnitNo = new ArrayAdapter<String>(
					AddEditIssueActivity.this,
					android.R.layout.simple_spinner_item, arrUnitNo);
			adapterUnitNo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ddlUnitNo.setAdapter(adapterUnitNo);

			driverList = result.getReportedByList();
			if (driverList != null && driverList.size() > 0) {
				String arrDriver[] = new String[driverList.size()+1];
				arrDriver[0] = "Please Select";
				for (int i = 0; i < arrDriver.length-1; i++) {
					arrDriver[i+1] = driverList.get(i).getFullName();
				}
				ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
						AddEditIssueActivity.this,
						android.R.layout.simple_spinner_item, arrDriver);
				adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				ddlReportedBy.setAdapter(adapter3);
			}

			statusList = result.getStatusList();
			if (statusList != null && statusList.size() > 0) {
				String arrStatus[] = new String[statusList.size()+1];
				arrStatus[0] = "Please Select";
				for (int i = 0; i < arrStatus.length-1; i++) {
					arrStatus[i+1] = statusList.get(i).getTypeName();
				}
				ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(
						AddEditIssueActivity.this,
						android.R.layout.simple_spinner_item, arrStatus);
				adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				ddlStatus.setAdapter(adapter4);
			}
		}

		@Override
		protected IssueModel doInBackground(String... params) {
			IssueModel issueModel = getOpenAdd();
			return issueModel;
		}
	}

	public IssueModel convertJSONArrayIntoList(String resp) {
		IssueModel issueModel = new IssueModel();
		try {
			JSONObject jsonO = new JSONObject(resp);
			unitNos = new ArrayList<String>();
			JSONArray jsonArray = jsonO.getJSONArray("unitNos");
			for (int i = 0; i < jsonArray.length(); i++) {
				String unitNo = jsonArray.get(i).toString();
				unitNos.add(unitNo);
			}
			issueModel.setUnitNos(unitNos);
		} catch (Exception e) {
			Log.e("AddEditIssueActivity: convertJSONArrayIntoList(): ",
					e.toString());
		}
		return issueModel;
	}

	ObjectMapper mapper = new ObjectMapper();

	public static List<IssueModel> result = null;

	public String readResponse(String resp) {
		String msg = null;
		try {
			Log.e("response message: ", resp);
			JSONObject jsonO = new JSONObject(resp);
			if (resp != null && !resp.contains("resultList")) {
				msg = jsonO.get("message").toString();
			} else {
				String list = jsonO.get("resultList").toString();
				result = getListFromResponse(list);
				msg = jsonO.get("message").toString();
			}
		} catch (Exception e) {
			Log.e("AddEditIssueActivity: readResponse(): ", e.toString());
		}
		return msg;
	}

	public List<IssueModel> getListFromResponse(String resp) {
		List<IssueModel> lstRequests = new ArrayList<IssueModel>();
		try {
			JSONArray jsonArray = new JSONArray(resp);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				IssueModel obj = new IssueModel();
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

	List<String> unitNos = null;

	private class GetUnitNos extends AsyncTask<String, Void, IssueModel> {

		@Override
		protected void onPostExecute(IssueModel issueModel) {
			super.onPostExecute(issueModel);
			unitNos = issueModel.getUnitNos();

			if (unitNos != null && unitNos.size() > 0) {
				String arr[] = new String[unitNos.size()];
				for (int i = 0; i < arr.length; i++) {
					arr[i] = unitNos.get(i);
				}
				ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
						AddEditIssueActivity.this,
						android.R.layout.simple_spinner_item, arr);
				adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				ddlUnitNo.setAdapter(adapter3);
			}
		}

		@Override
		protected IssueModel doInBackground(String... params) {
			String url = ROOT_URL + "issue/category/"
					+ Long.parseLong(params[0]) + "/unitType/"
					+ Long.parseLong(params[1]);
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			String response = new AndroidHttpRequestSender().getRequest(url,
					params1);
			IssueModel issueModel = convertJSONArrayIntoList(response);
			return issueModel;
		}
	}

	private class AddIssue extends AsyncTask<String, Void, String> {

		@Override
		protected void onPostExecute(String msg) {
			super.onPostExecute(msg);
			Toast.makeText(AddEditIssueActivity.this, msg, Toast.LENGTH_LONG)
					.show();
			AddEditIssueActivity.this.finish();
		}

		@Override
		protected String doInBackground(String... params) {
			
			String url = ROOT_URL + "issue";
			
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			params1.add(new BasicNameValuePair("title", etTitle.getText().toString()));
			params1.add(new BasicNameValuePair("vmcId", (ddlVMC.getSelectedItemPosition() == 0) ? "0" : String.valueOf(vmcList.get(ddlVMC.getSelectedItemPosition()).getId())));
			params1.add(new BasicNameValuePair("unitTypeId", (ddlUnitType.getSelectedItemPosition() == 0) ? "0" : String.valueOf(unitTypeList.get(ddlUnitType.getSelectedItemPosition()).getTypeId())));
			params1.add(new BasicNameValuePair("categoryId", (ddlCategory.getSelectedItemPosition() == 0) ? "0" : String.valueOf(categoryList.get(ddlCategory.getSelectedItemPosition()).getCategoryId())));
			params1.add(new BasicNameValuePair("unitNo", (ddlUnitNo.getSelectedItemPosition() == 0) ? "0" : String.valueOf(unitNos.get(ddlUnitNo.getSelectedItemPosition()))));
			params1.add(new BasicNameValuePair("reportedById", (ddlReportedBy.getSelectedItemPosition() == 0) ? "0" : String.valueOf(driverList.get(ddlReportedBy.getSelectedItemPosition()).getDriverId())));
			params1.add(new BasicNameValuePair("statusId", (ddlStatus.getSelectedItemPosition() == 0) ? "0" : String.valueOf(statusList.get(ddlStatus.getSelectedItemPosition()).getTypeId())));
			params1.add(new BasicNameValuePair("description", etDescription.getText().toString()));

			String response = new AndroidHttpRequestSender().sendRequest(url, params1);
			String msg = readResponse(response);
			
			return msg;
		}
	}

	private class EditIssue extends AsyncTask<String, Void, String> {

		@Override
		protected void onPostExecute(String msg) {
			super.onPostExecute(msg);
			Toast.makeText(AddEditIssueActivity.this, msg, Toast.LENGTH_LONG)
					.show();
			AddEditIssueActivity.this.finish();
		}

		@Override
		protected String doInBackground(String... params) {
			String url = ROOT_URL + "issue/" + params[0];
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			params1.add(new BasicNameValuePair("title", etTitle.getText()
					.toString()));
			params1.add(new BasicNameValuePair("vmcId", String.valueOf(vmcList
					.get(ddlVMC.getSelectedItemPosition()).getId())));
			params1.add(new BasicNameValuePair("unitTypeId", String
					.valueOf(unitTypeList.get(
							ddlUnitType.getSelectedItemPosition()).getTypeId())));
			params1.add(new BasicNameValuePair("categoryId", String
					.valueOf(categoryList.get(
							ddlCategory.getSelectedItemPosition())
							.getCategoryId())));
			params1.add(new BasicNameValuePair("unitNo", String.valueOf(unitNos
					.get(ddlUnitNo.getSelectedItemPosition()))));
			params1.add(new BasicNameValuePair("reportedById", String
					.valueOf(driverList.get(
							ddlReportedBy.getSelectedItemPosition())
							.getDriverId())));
			params1.add(new BasicNameValuePair("statusId", String
					.valueOf(statusList
							.get(ddlStatus.getSelectedItemPosition())
							.getTypeId())));
			params1.add(new BasicNameValuePair("description", etDescription
					.getText().toString()));

			String response = new AndroidHttpRequestSender().sendUpdateRequest(
					url, params1);
			String msg = readResponse(response);
			return msg;
		}
	}

	public static Long issueId = 0l;

	@Override
	public void onClick(View v) {
		if ((v == btnSaveIssue) && addEditFlag == 0) {
			new AddIssue().execute();
		} else {
			new EditIssue().execute(issueId + "");
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		boolean alreadyChanged = false;
		if (!alreadyChanged && (parent.getId() == R.id.ddlCategoryAddIssue)) {
			Long unitTypeId = unitTypeList.get(
					ddlUnitType.getSelectedItemPosition()).getTypeId();
			Long categoryId = 0l;

/*			Long categoryId = categoryList.get(position).getCategoryId();
*/			
//			new GetUnitNos().execute(categoryId + "", unitTypeId + "");
			alreadyChanged = true;

		} else if (!alreadyChanged
				&& (parent.getId() == R.id.ddlUnitTypeAddIssue)) {

			Long unitTypeId = unitTypeList.get(position).getTypeId();
			Long categoryId = 0l;

/*			Long categoryId = categoryList.get(
					ddlCategory.getSelectedItemPosition()).getCategoryId();*/
//			new GetUnitNos().execute(categoryId + "", unitTypeId + "");
			alreadyChanged = true;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}
}
