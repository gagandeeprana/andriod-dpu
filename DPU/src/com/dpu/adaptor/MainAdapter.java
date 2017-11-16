package com.dpu.adaptor;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dpu.activities.R;
import com.dpu.bean.QuesAnsBean;

public class MainAdapter extends ArrayAdapter<QuesAnsBean> {

	Context ctx;
	List<QuesAnsBean> lst;
	int textViewResourceId;

	public MainAdapter(Context context, int textViewResourceId,
			List<QuesAnsBean> lst) {
		super(context, textViewResourceId, lst);
		this.ctx = context;
		this.lst = lst;
		this.textViewResourceId = textViewResourceId;
	}

	@SuppressWarnings("unused")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = LayoutInflater.from(getContext()).inflate(textViewResourceId, parent, false);

		LinearLayout txtQues = (LinearLayout) rowView.findViewById(R.id.ll1);
		TextView txtCategory = (TextView) rowView.findViewById(R.id.text1);
		TextView txtQuestionCount = (TextView) rowView.findViewById(R.id.text2);
		ImageView img = (ImageView)rowView.findViewById(R.id.img1);
		
		txtCategory.setText(lst.get(position).getQuestion());
		return rowView;

	}

}