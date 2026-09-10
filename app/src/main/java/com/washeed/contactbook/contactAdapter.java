package com.washeed.contactbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.washeed.Util.Contact;

import java.util.ArrayList;

public class contactAdapter extends BaseAdapter {
    private final ArrayList<Contact> contactList;
    private final LayoutInflater inflater;
    public contactAdapter(Context context, ArrayList<Contact> contacts) {
        this.contactList = contacts;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return contactList.size();
    }
    @Override
    public Contact getItem(int position) {
        return contactList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contact_list_item, parent, false);
        }
        TextView name = convertView.findViewById(R.id.textViewName);
        TextView phone = convertView.findViewById(R.id.textViewNumber);
        Contact contact = contactList.get(position);
        name.setText(contact.getName());
        phone.setText(contact.getNumber());
        return convertView;
    }
}
