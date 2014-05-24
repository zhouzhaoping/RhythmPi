package zzp.rhythmPi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zzp.rhythmPi.R;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AboutActivity extends ListActivity {

	private SimpleAdapter adapter; 
	private ImageView button_back;
	private ListView listview;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_activity);
        adapter = new SimpleAdapter(this, getData(), R.layout.list_row_contact, 
        		new String[]{"person_name","person_id","person_introduction","person_profile"},
        		new int[]{R.id.person_name,R.id.person_id,R.id.person_introduction,R.id.person_profile});  
		setListAdapter(adapter);
		findViews();
		setListensers();
	}
	
	
	private void findViews()
	{		
		button_back = (ImageView) findViewById(R.id.go_back_button);
		listview = getListView(); 
	}
	private void setListensers() 
	{
		button_back.setOnClickListener(goBack);
		ItemOnLongClick();
	 }
	
	private Button.OnClickListener goBack = new Button.OnClickListener()
	{
		public void onClick(View v)
		{
			 AboutActivity.this.finish();
		}
	}; 	
	
	
    private void ItemOnLongClick() {
    	listview.setOnCreateContextMenuListener(new OnCreateContextMenuListener()
    	{
    		@Override
    		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("��������ô��");	
                menu.add(0, 0, 0, "ϲ��");
                menu.add(0, 1, 0, "����");
    		}
    	});
    }
    /*	
    @Override
    public boolean onContextItemSelected(MenuItem item) {

            AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

            int id = (int) info.id;// �����info.id��Ӧ�ľ������ݿ���_id��ֵ
            switch (item.getItemId()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
            }
            return super.onContextItemSelected(item);
    }
    */
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("person_name", "������");
		map.put("person_id", "1100012***");
		map.put("person_introduction", "��Ŀ�����ϴ�֪�����������ˣ��ſ���������");
		map.put("person_profile", R.drawable.member1);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("person_name", "�Ÿ���");
		map.put("person_id", "1100012***");
		map.put("person_introduction", "UI��ƣ���վ������");
		map.put("person_profile", R.drawable.member2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("person_name", "����ƽ");
		map.put("person_id", "1100012779");
		map.put("person_introduction", "�����������У���׿�������ſ�������ͯ");
		map.put("person_profile", R.drawable.member3);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("person_name", "������");
		map.put("person_id", "1100012***");
		map.put("person_introduction", "Ӳ��֧�֣��ſ��������");
		map.put("person_profile", R.drawable.member4);
		list.add(map);
		
		return list;
	}	
}
