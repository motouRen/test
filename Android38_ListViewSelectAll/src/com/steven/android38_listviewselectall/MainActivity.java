package com.steven.android38_listviewselectall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.steven.android38_listviewselectall.MyAdapter.ViewHolder;

public class MainActivity extends Activity {
	private Context mContext = this;
	private ListView listView_main;
	private List<Map<String, Object>> totalList = new ArrayList<Map<String, Object>>();
	private MyAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initData();

		initView();
	}

	private void initData() {
		String[] arrUsernames = getResources().getStringArray(
				R.array.arrUsernames);
		for (int i = 0; i < arrUsernames.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", arrUsernames[i]);
			map.put("status", false);
			totalList.add(map);
		}
	}

	private void initView() {
		listView_main = (ListView) findViewById(R.id.listView_main);
		adapter = new MyAdapter(this, totalList);
		listView_main.setAdapter(adapter);

		listView_main.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ViewHolder viewHolder = (ViewHolder) view.getTag();
				// 让当前checkbox的勾选项反选
				viewHolder.checkBox_item_status.toggle();
				totalList.get(position).put("status",
						viewHolder.checkBox_item_status.isChecked());
			}
		});
	}

	public void clickButton(View view) {
		switch (view.getId()) {
		case R.id.button_selectall:
			for (int i = 0; i < totalList.size(); i++) {
				totalList.get(i).put("status", true);
			}
			adapter.reloadListView();
			break;
		case R.id.button_reverse:
			for (int i = 0; i < totalList.size(); i++) {
				totalList.get(i).put("status",
						!((Boolean) totalList.get(i).get("status")));
			}
			adapter.reloadListView();
			break;
		case R.id.button_selectnone:
			for (int i = 0; i < totalList.size(); i++) {
				totalList.get(i).put("status", false);
			}
			adapter.reloadListView();
			break;
		case R.id.button_submit:
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < totalList.size(); i++) {
				if ((Boolean) totalList.get(i).get("status")) {
					list.add(i);
				}
			}
			Toast.makeText(mContext, "勾选了:" + list, 1).show();
			break;
		case R.id.button_delete:
			List<Map<String, Object>> list_delete = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < totalList.size(); i++) {
				if ((Boolean) totalList.get(i).get("status")) {
					list_delete.add(totalList.get(i));
				}
			}
			adapter.removeItems(list_delete);
			break;
		}
	}
}
