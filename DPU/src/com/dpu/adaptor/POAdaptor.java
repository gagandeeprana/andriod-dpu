package com.dpu.adaptor;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dpu.activities.R;
import com.dpu.bean.PurchaseOrderModel;

public class POAdaptor extends ArrayAdapter<PurchaseOrderModel> {

	Context ctx;
	int resId;
	List<PurchaseOrderModel> lstPOs;
	LayoutInflater inflater = null;

	public POAdaptor(Context ctx, int resId, List<PurchaseOrderModel> lstPOs) {
		super(ctx, resId, lstPOs);
		this.ctx = ctx;
		this.resId = resId;
		this.lstPOs = lstPOs;
		inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = inflater.inflate(resId, parent, false);
		TextView txtPONo = (TextView) rowView.findViewById(R.id.txtPONo);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txtTitle);
		TextView txtCategory = (TextView) rowView.findViewById(R.id.txtCategory);
		TextView txtStatus = (TextView) rowView.findViewById(R.id.txtStatus);
		TextView unitType = (TextView) rowView.findViewById(R.id.unitType);
		TextView vendorName = (TextView) rowView.findViewById(R.id.vendorName);
//		TextView txtContact = (TextView) rowView.findViewById(R.id.txtContact);
//		txtName.setText(lstTrucks.get(position).getName());
		PurchaseOrderModel obj = lstPOs.get(position);
		txtPONo.setText(obj.getPoNo()+"");
		txtTitle.setText(obj.getTitle()+"");
		txtCategory.setText(obj.getCategoryName()+"");
		txtStatus.setText(obj.getStatusName()+"");
		unitType.setText(obj.getUnitTypeName()+"");
		vendorName.setText(obj.getVendorName()+"");
		return rowView;
	}
}
