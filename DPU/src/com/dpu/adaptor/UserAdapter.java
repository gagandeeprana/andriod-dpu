package com.dpu.adaptor;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dpu.activities.R;
import com.dpu.bean.UserBean;

public class UserAdapter extends ArrayAdapter<UserBean> {

	Context ctx;
	List<UserBean> lst;
	int textViewResourceId;

	public UserAdapter(Context context, int textViewResourceId,
			List<UserBean> lst) {
		super(context, textViewResourceId, lst);
		this.ctx = context;
		this.lst = lst;
		this.textViewResourceId = textViewResourceId;
	}

	@SuppressWarnings("unused")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = LayoutInflater.from(getContext()).inflate(
				textViewResourceId, parent, false);

		LinearLayout txtQues = (LinearLayout) rowView.findViewById(R.id.ll1);
		TextView txtCategory = (TextView) rowView.findViewById(R.id.text1);
		TextView txtQuestionCount = (TextView) rowView.findViewById(R.id.text2);
		ImageView img = (ImageView) rowView.findViewById(R.id.img1);

		txtCategory.setText(lst.get(position).getUsername());
		return rowView;

	}

}