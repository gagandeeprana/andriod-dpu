//package com.dpu.activities;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.lucasr.twowayview.TwoWayView;
//
//import android.annotation.SuppressLint;
//import android.app.Fragment;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.dpu.adaptor.MainAdapter;
//import com.dpu.bean.QuesAnsBean;
//
//
//@SuppressLint("ShowToast") public class ScrollActivity extends Fragment {
//
//	View rootView = null;
//	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		rootView = inflater.inflate(R.layout.scroll_activity, container, false);
//		
//		List<QuesAnsBean> lst = new ArrayList<QuesAnsBean>();
//		lst.add(new QuesAnsBean("Trailer 1","Programming Language used to create applications at various levels"));
//		lst.add(new QuesAnsBean("Trailer 2","Stores Object in unique and random order."));
//		lst.add(new QuesAnsBean("Trailer 3","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 4","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 5","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 6","Programming Language used to create applications at various levels"));
//		lst.add(new QuesAnsBean("Trailer 7","Stores Object in unique and random order."));
//		lst.add(new QuesAnsBean("Trailer 8","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 9","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 10","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 11","Programming Language used to create applications at various levels"));
//		lst.add(new QuesAnsBean("Trailer 12","Stores Object in unique and random order."));
//		lst.add(new QuesAnsBean("Trailer 13","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 14","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 15","Stores duplicate Object in order of insertion"));
//		
//		List<QuesAnsBean> lst2 = new ArrayList<QuesAnsBean>();
//		lst2.add(new QuesAnsBean("Truck 1","Programming Language used to create applications at various levels"));
//		lst2.add(new QuesAnsBean("Truck 2","Stores Object in unique and random order."));
//		lst2.add(new QuesAnsBean("Truck 3","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 4","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 5","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 6","Programming Language used to create applications at various levels"));
//		lst2.add(new QuesAnsBean("Truck 7","Stores Object in unique and random order."));
//		lst2.add(new QuesAnsBean("Truck 8","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 9","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 10","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 11","Programming Language used to create applications at various levels"));
//		lst2.add(new QuesAnsBean("Truck 12","Stores Object in unique and random order."));
//		lst2.add(new QuesAnsBean("Truck 13","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 14","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 15","Stores duplicate Object in order of insertion"));
//
//		
//		
//		
//		
//		//		ArrayAdapter<QuesAnsBean> aItems = new ArrayAdapter(this, R.layout.row_layout, lst);
////		TwoWayView lvTest = (TwoWayView) findViewById(R.id.lvItems);
////		lvTest.setAdapter(aItems);
//		MainAdapter adapter = new MainAdapter(getActivity(), R.layout.row_layout, lst);
//		TwoWayView lvTest = (TwoWayView) rootView.findViewById(R.id.lvItems);
//		lvTest.setAdapter(adapter);
//
//		
//		MainAdapter adapter2 = new MainAdapter(getActivity(), R.layout.row_layout, lst2);
//		TwoWayView lvTest2 = (TwoWayView) rootView.findViewById(R.id.lvItems2);
//		lvTest2.setAdapter(adapter2);
//		
//		MainAdapter adapter3 = new MainAdapter(getActivity(), R.layout.row_layout, lst2);
//		TwoWayView lvTest3 = (TwoWayView) rootView.findViewById(R.id.lvItems3);
//		lvTest3.setAdapter(adapter3);
//		
//		
//		return rootView;
//	}
//	
//	/*@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.scroll_activity);
//		List<QuesAnsBean> lst = new ArrayList<QuesAnsBean>();
//		lst.add(new QuesAnsBean("Trailer 1","Programming Language used to create applications at various levels"));
//		lst.add(new QuesAnsBean("Trailer 2","Stores Object in unique and random order."));
//		lst.add(new QuesAnsBean("Trailer 3","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 4","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 5","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 6","Programming Language used to create applications at various levels"));
//		lst.add(new QuesAnsBean("Trailer 7","Stores Object in unique and random order."));
//		lst.add(new QuesAnsBean("Trailer 8","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 9","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 10","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 11","Programming Language used to create applications at various levels"));
//		lst.add(new QuesAnsBean("Trailer 12","Stores Object in unique and random order."));
//		lst.add(new QuesAnsBean("Trailer 13","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 14","Stores duplicate Object in order of insertion"));
//		lst.add(new QuesAnsBean("Trailer 15","Stores duplicate Object in order of insertion"));
//		
//		List<QuesAnsBean> lst2 = new ArrayList<QuesAnsBean>();
//		lst2.add(new QuesAnsBean("Truck 1","Programming Language used to create applications at various levels"));
//		lst2.add(new QuesAnsBean("Truck 2","Stores Object in unique and random order."));
//		lst2.add(new QuesAnsBean("Truck 3","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 4","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 5","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 6","Programming Language used to create applications at various levels"));
//		lst2.add(new QuesAnsBean("Truck 7","Stores Object in unique and random order."));
//		lst2.add(new QuesAnsBean("Truck 8","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 9","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 10","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 11","Programming Language used to create applications at various levels"));
//		lst2.add(new QuesAnsBean("Truck 12","Stores Object in unique and random order."));
//		lst2.add(new QuesAnsBean("Truck 13","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 14","Stores duplicate Object in order of insertion"));
//		lst2.add(new QuesAnsBean("Truck 15","Stores duplicate Object in order of insertion"));
//
//		
//		
//		
//		
//		//		ArrayAdapter<QuesAnsBean> aItems = new ArrayAdapter(this, R.layout.row_layout, lst);
////		TwoWayView lvTest = (TwoWayView) findViewById(R.id.lvItems);
////		lvTest.setAdapter(aItems);
//		MainAdapter adapter = new MainAdapter(this, R.layout.row_layout, lst);
//		TwoWayView lvTest = (TwoWayView) findViewById(R.id.lvItems);
//		lvTest.setAdapter(adapter);
//
//		
//		MainAdapter adapter2 = new MainAdapter(this, R.layout.row_layout, lst2);
//		TwoWayView lvTest2 = (TwoWayView) findViewById(R.id.lvItems2);
//		lvTest2.setAdapter(adapter2);
//		
//		MainAdapter adapter3 = new MainAdapter(this, R.layout.row_layout, lst2);
//		TwoWayView lvTest3 = (TwoWayView) findViewById(R.id.lvItems3);
//		lvTest3.setAdapter(adapter3);
//
//		try {
////			new RetrofitHelper(this).loadJSON();
//		} catch (Exception e) {
////			txt.setText(""+e);
//			Toast.makeText(this, "Try..........  " + e, 10000).show();
//		}
//	}*/
//
//	/*@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		return true;
//	}*/
//
//}
