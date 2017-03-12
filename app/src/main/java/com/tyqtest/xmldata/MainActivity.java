package com.tyqtest.xmldata;

import android.content.ContentResolver;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import  android.util.Log;
import java.util.ArrayList;
import android.database.Cursor;
public class MainActivity extends AppCompatActivity {
public  String YQY=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPhoneContacts();
        Log.e(YQY,mContacts.size()+"");
        initList();
    }

    private ArrayList<ContactEntity> mContacts = new ArrayList<ContactEntity>();
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER
            , ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
    private static final int PHONES_NUMBER_INDEX = 1;
    private static final int PHONES_CONTACT_ID_INDEX = 3;

    private void getPhoneContacts() {
        ContentResolver resolver = getContentResolver();
        try {
            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
            if (phoneCursor != null) {
                while (phoneCursor.moveToNext()) {
                    String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                    if (TextUtils.isEmpty(phoneNumber)) continue;
                    String contactName = phoneCursor.getString(PHONES_CONTACT_ID_INDEX);
                    ContactEntity mContact = new ContactEntity(contactName, phoneNumber);
                    mContacts.add(mContact);

                }
                phoneCursor.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initList() {
        ListView lv = (ListView) findViewById(R.id.listview1);
        lv.setAdapter(new MyAdapter());


    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mContacts != null && mContacts.size() > 0) {
                return mContacts.size();
            }

            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mContacts != null && mContacts.size() > 0) {
                return mContacts.get(position);
            }

            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.number = (TextView) convertView.findViewById(R.id.number);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ContactEntity contact = mContacts.get(position);
            holder.name.setText(contact.getName());
            holder.number.setText(contact.getNumber());

            return convertView;
        }

        class ViewHolder {
            TextView name;
            TextView number;
        }
    }
}
