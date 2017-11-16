package com.dpu.adaptor;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dpu.activities.R;
import com.dpu.bean.IssueModel;

public class IssueAdapter extends ArrayAdapter<IssueModel> {

	Context ctx;
	int resId;
	List<IssueModel> lstIssues;
	LayoutInflater inflater = null;

	public IssueAdapter(Context ctx, int resId, List<IssueModel> lstIssues) {
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
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txtTitleIssue);
		TextView txtVMC = (TextView) rowView.findViewById(R.id.txtVMC);
		TextView txtType = (TextView) rowView.findViewById(R.id.txtType);
		TextView txtUnitNo = (TextView) rowView
				.findViewById(R.id.txtUnitNoIssue);
		TextView txtReportedBy = (TextView) rowView
				.findViewById(R.id.txtReportedBy);
		TextView txtStatus = (TextView) rowView
				.findViewById(R.id.txtStatusIssue);

		IssueModel obj = lstIssues.get(position);
		txtTitle.setText(obj.getTitle() + "");
		txtVMC.setText(obj.getVmcName() + "");
		txtType.setText(obj.getUnitTypeName() + "");
		txtUnitNo.setText(obj.getUnitNo() + "");
		txtReportedBy.setText(obj.getReportedByName() + "");
		txtStatus.setText(obj.getStatusName() + "");
		return rowView;
	}
}
