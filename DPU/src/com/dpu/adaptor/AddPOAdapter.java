package com.dpu.adaptor;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dpu.activities.R;
import com.dpu.bean.IssueModel;

@SuppressLint("ViewHolder")
public class AddPOAdapter extends ArrayAdapter<IssueModel> {

	Context ctx;
	int resId;
	List<IssueModel> lstIssues;
	LayoutInflater inflater = null;

	public AddPOAdapter(Context ctx, int resId, List<IssueModel> lstIssues) {
		super(ctx, resId, lstIssues);
		this.ctx = ctx;
		this.resId = resId;
		this.lstIssues = lstIssues;
		inflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = inflater.inflate(resId, parent, false);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txtIssueTitle);
		TextView txtCategory = (TextView) rowView
				.findViewById(R.id.txtCategoryAddPO);
		TextView txtStatus = (TextView) rowView
				.findViewById(R.id.txtStatusAddPO);
		TextView unitType = (TextView) rowView
				.findViewById(R.id.txtUnitTypeAddPO);
		TextView vmc = (TextView) rowView.findViewById(R.id.txtVMCAddPO);
		IssueModel obj = lstIssues.get(position);
		txtTitle.setText(obj.getTitle() + "");
		txtCategory.setText(obj.getCategoryName() + "");
		txtStatus.setText(obj.getStatusName() + "");
		unitType.setText(obj.getUnitTypeName() + "");
		vmc.setText(obj.getVmcName() + "");
		return rowView;
	}
}
