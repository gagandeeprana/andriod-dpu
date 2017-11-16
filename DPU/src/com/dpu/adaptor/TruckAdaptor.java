//package com.dpu.adaptor;
//
//import java.util.List;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import com.dpu.activities.R;
//import com.dpu.bean.TruckBean;
//
//public class TruckAdaptor extends ArrayAdapter<TruckBean> {
//
//	Context ctx;
//	int resId;
//	List<TruckBean> lstTrucks;
//	LayoutInflater inflater = null;
//
//	public TruckAdaptor(Context ctx, int resId, List<TruckBean> lstTrucks) {
//		super(ctx, resId, lstTrucks);
//		this.ctx = ctx;
//		this.resId = resId;
//		this.lstTrucks = lstTrucks;
//		inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		View rowView = inflater.inflate(resId, parent, false);
//		TextView txtName = (TextView) rowView.findViewById(R.id.txtName);
//		TextView txtContact = (TextView) rowView.findViewById(R.id.txtContact);
////		txtName.setText(lstTrucks.get(position).getName());
////		txtContact.setText(lstTrucks.get(position).getContact());
//		return rowView;
//	}
//}
