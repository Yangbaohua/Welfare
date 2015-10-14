package com.example.mysocketclient;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.mysocketclient.LoginActivity.MessageReceiver;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ChooseChatFragment extends Fragment implements OnClickListener,OnItemClickListener{
	
	private EditText editText;
	private Button buttonAdd;
	private Button buttonGroup;
	
	private ListView listView;
	private ArrayList<String> arrayList = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_choose_chat, container, false);
		editText = (EditText)view.findViewById(R.id.editText);
		buttonAdd = (Button)view.findViewById(R.id.buttonAdd);
		buttonGroup = (Button)view.findViewById(R.id.buttonGroup);
		listView = (ListView)view.findViewById(R.id.listview);
		
		buttonAdd.setOnClickListener(this);
		buttonGroup.setOnClickListener(this);
		listView.setOnItemClickListener(this);
		
		adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_textview, arrayList);
		listView.setAdapter(adapter);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.buttonAdd)
		{
			if(editText.getText().toString() != null)
			{
				arrayList.add(editText.getText().toString());
				adapter.notifyDataSetChanged();//֪ͨListView�������ѷ����ı�
				listView.setSelection(listView.getCount() - 1);//����һ����Ϣʱ��ListView��ʾѡ�����һ��
				
				editText.setText("");
			}		
		}
		else if(v.getId() == R.id.buttonGroup)
		{		
			toChatFragment("Group");
		}	
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) 
	{
		toChatFragment(arrayList.get(position));
	}
	
	private void toChatFragment(String tag)
	{
		FragmentManager fm = getFragmentManager();
		FragmentTransaction tx = fm.beginTransaction(); 
		tx.hide(this);
		//��ֻ֤��ʼ��һ��
		//����ChatFragment��Ϊһ��Fragment����һ��tag������Fragment֮����л�
		//ͬʱChatFragment��Ϊһ�����촰�ڣ�Ҳ��һ��tag����ʾ��˭�����죬��tagΪ11����ʾ��11������
		//����ChatFragment�ڽ�����Ϣʱֻ���ܸ��Լ�tag��ȵģ��Ϳ��Խ���Ӧ����Ϣ��ʾ�ڶ�Ӧ�Ĵ���
		if(fm.findFragmentByTag(tag) == null)
		{
			tx.add(R.id.content, new ChatFragment(tag), tag);
		}
		else 
		{  
	        tx.show(fm.findFragmentByTag(tag));
		}        
        tx.addToBackStack(null);
        tx.commit();
	}
}
