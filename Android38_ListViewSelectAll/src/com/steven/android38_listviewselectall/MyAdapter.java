package com.steven.android38_listviewselectall;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, Object>> list = null;
	private LayoutInflater inflater = null;

	public MyAdapter(Context context, List<Map<String, Object>> list) {
		this.context = context;
		this.list = list;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_listview_main, parent,
					false);
			mHolder = new ViewHolder();

			mHolder.textView_item_username = (TextView) convertView
					.findViewById(R.id.textView_item_username);
			mHolder.checkBox_item_status = (CheckBox) convertView
					.findViewById(R.id.checkBox_item_status);

			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.textView_item_username.setText(list.get(position)
				.get("username").toString());
		mHolder.checkBox_item_status.setChecked((Boolean) list.get(position)
				.get("status"));

		mHolder.textView_item_username
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(context,
								list.get(position).get("username") + "", 0)
								.show();
					}
				});
		return convertView;
	}

	class ViewHolder {
		TextView textView_item_username;
		CheckBox checkBox_item_status;
	}

	// 重新加载Listview
	public void reloadListView() {
		notifyDataSetChanged();
	}

	// 添加一条数据
	public void addItem(int position, Map<String, Object> data) {
		list.add(position, data);
		notifyDataSetChanged();
	}

	// 追加一批数据
	public void addItems(Collection<? extends Map<String, Object>> data,
			boolean isClear) {
		if (isClear) {
			list.clear();
		}
		list.addAll(data);
		notifyDataSetChanged();
	}

	// 删除一条数据
	public void removeItem(int position) {
		list.remove(position);
		notifyDataSetChanged();
	}

	// 删除一条数据
	public void removeItem(Map<String, Object> data) {
		list.remove(data);
		notifyDataSetChanged();
	}

	// 删除一批数据
	public void removeItems(Collection<? extends Map<String, Object>> data) {
		list.removeAll(data);
		notifyDataSetChanged();
	}

}
