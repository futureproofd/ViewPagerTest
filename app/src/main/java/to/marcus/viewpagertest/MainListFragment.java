package to.marcus.viewpagertest;

/**
 * Created by marcus on 20/11/14.
 */
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainListFragment extends ListFragment {

    private ArrayList<Contact> mContacts;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setup data-set
        mContacts = ContactUtility.get(getActivity()).getContacts();
        MsgAdapter adapter = new MsgAdapter(mContacts);
        setListAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MsgAdapter)getListAdapter()).notifyDataSetChanged();
    }

    //our custom adapter for custom item list
    private class MsgAdapter extends ArrayAdapter<Contact>{

        public MsgAdapter(ArrayList<Contact> contacts){
            super(getActivity(), 0, contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //if no view
            if (convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_msg_group, null);
            }

            //get current contact position
            Contact c = getItem(position);
            //set custom items
            TextView contactTextView = (TextView)convertView.findViewById(R.id.contact_list_item_name);
            contactTextView.setText(c.getName());

            TextView msgTextView = (TextView)convertView.findViewById(R.id.contact_list_item_lastmsg);
            msgTextView.setText(c.getLastMsg());
            return convertView;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Contact c = ((MsgAdapter)getListAdapter()).getItem(position);

        //Start conversation Activity
        Intent i = new Intent(getActivity(), MsgPagerActivity.class);
        i.putExtra(MsgFragment.EXTRA_CONVO_ID, c.getId());
        startActivity(i);
    }

}