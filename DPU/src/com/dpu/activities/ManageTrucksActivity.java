//package com.dpu.activities;
//
//import java.util.List;
//
//import android.app.Fragment;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.ContextMenu;
//import android.view.ContextMenu.ContextMenuInfo;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemLongClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//
//import com.dpu.adaptor.TruckAdaptor;
//import com.dpu.bean.TruckBean;
//import com.dpu.services.ContactServices;
//
//public class ManageTrucksActivity extends Fragment implements
//		OnClickListener, OnItemLongClickListener {
//
//	Button btnAdd;
//	EditText etSearch;
//	ListView lvContacts;
//	int index = 0;
//	View rootView = null;
//	List<TruckBean> lstContacts;
//	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		rootView = inflater.inflate(R.layout.manage_truck_activity, container, false);
//		initialize();
//		registerForContextMenu(lvContacts);
//		lvContacts.setOnItemLongClickListener(this);
//		TextWatcher watcher = new TextWatcher() {
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before,
//					int count) {
////				setAdapter();
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void afterTextChanged(Editable s) {
//				// TODO Auto-generated method stub
//
//			}
//		};
////		etSearch.addTextChangedListener(watcher);
//		return rootView;
//	}
//
//	private void initialize() {
////		btnAdd = (Button) findViewById(R.id.btnAdd);
//		etSearch = (EditText) rootView.findViewById(R.id.editText);
//		lvContacts = (ListView) rootView.findViewById(R.id.listView);
//		lstContacts = ContactServices.searchContact("");
////		btnAdd.setOnClickListener(this);
//
//		setAdapter();
//	}
//
//	private void setAdapter() {
//		String text = etSearch.getText().toString();
//		lstContacts = ContactServices.searchContact(text);
//		TruckAdaptor adapter = new TruckAdaptor(rootView.getContext(), R.layout.truck_row_layout, lstContacts);
//		lvContacts.setAdapter(adapter);
//	}
//
////	@Override
////	protected void onStart() {
////		super.onStart();
//////		lstContacts = ContactServices.getAllContacts();
////		setAdapter();
////	}
//
//	@Override
//	public void onClick(View v) {
////		startActivity(new Intent(this, AddContactActivity.class));
//	}
//
//	@Override
//	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
//			long arg3) {
//		arg0.showContextMenu();
//		index = arg2;
//		return true;
//	}
//
//	@Override
//	public void onCreateContextMenu(ContextMenu menu, View v,
//			ContextMenuInfo menuInfo) {
//		super.onCreateContextMenu(menu, v, menuInfo);
//		menu.setHeaderTitle("Choose");
//		menu.add("Update");
//		menu.add("Delete");
//	}
//
//	@Override
//	public boolean onContextItemSelected(MenuItem item) {
//		/*if (item.getTitle().toString().equals("Update")) {
//			Intent i = new Intent(this, UpdateContactActivity.class);
//			Bundle b = new Bundle();
//			ContactBean obj = new ContactBean(lstContacts.get(index).getId(),
//					lstContacts.get(index).getName(), lstContacts.get(index)
//							.getContact());
//			b.putSerializable("obj", obj);
//			i.putExtras(b);
//			startActivity(i);
//		} else if (item.getTitle().toString().equals("Delete")) {
//			int id = lstContacts.get(index).getId();
//			ContactServices.delContact(id);
//			setAdapter();
//		}*/
//		return super.onContextItemSelected(item);
//	}
//
//}
