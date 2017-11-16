package com.dpu.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {

	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	String mTitle = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.activity_main);
		// ActionBar actionBar = getActionBar();
		// actionBar.setBackgroundDrawable(new
		// ColorDrawable(Color.parseColor("#ffffff")));
		// actionBar.setStackedBackgroundDrawable(new
		// ColorDrawable(Color.parseColor("#ffffff")));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.drawer_list_item, getResources()
						.getStringArray(R.array.list));
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				String[] title = getResources().getStringArray(R.array.list);
				/**
				 * uncomment this and try..
				 */
				// getActionBar().setTitle(title[arg2]);
				displayView(arg2);
			}

		});

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				/**
				 * uncomment this and try...
				 */
				// getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				/**
				 * uncomment this and try...
				 */
				// getActionBar().setTitle("Demo");
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		mDrawerList.setAdapter(adapter);
		/**
		 * uncomment this and try...
		 */
		// getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	private void displayView(int position) {
		Fragment fragment = null;
		String[] title = getResources().getStringArray(R.array.list);
		mTitle = title[position];
		switch (position) {
		case 0:
			fragment = new POActivity();
			break;
		case 1:
			fragment = new IssueActivity();
			break;
		case 2:
			// fragment = new FragmentOneActivity();
			break;
		default:
			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			mDrawerLayout.closeDrawer(mDrawerList);

		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// @Override
	// public boolean onPrepareOptionsMenu(Menu menu) {
	// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	// return super.onPrepareOptionsMenu(menu);
	// }

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

}
