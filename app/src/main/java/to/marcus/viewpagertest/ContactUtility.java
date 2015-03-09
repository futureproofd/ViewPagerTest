package to.marcus.viewpagertest;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by marcus on 26/11/14.
 */
public class ContactUtility {

    private ArrayList<Contact> mContacts;
    private static ContactUtility sContactStorage;
    private Context mAppContext;

    //constructor to get contacts based on SMS conversations
    private ContactUtility(Context appContext){
        mAppContext = appContext;
        mContacts = MsgDaoUtility.getAllSmsContacts(appContext);
    }

    //get method to only return one instance from the constructor
    public static ContactUtility get(Context c){
        if (sContactStorage == null){
            sContactStorage = new ContactUtility(c.getApplicationContext());
        }
        return sContactStorage;
    }

    //work with our arraylist of contacts
    public ArrayList<Contact> getContacts(){
        return mContacts;
    }
/*
    public Contact getContact(long id){
        for (Contact c: mContacts){
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
    */

    //for our actionbar
    public void addContact(Contact c){
        mContacts.add(c);
    }
}

