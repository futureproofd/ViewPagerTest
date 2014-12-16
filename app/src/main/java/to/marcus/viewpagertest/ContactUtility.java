package to.marcus.viewpagertest;

import android.content.Context;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by marcus on 26/11/14.
 */
public class ContactUtility {

    private ArrayList<Contact> mContacts;
    private static ContactUtility sContactStorage;
    private Context mAppContext;

    //constructor to get fake contact records
    private ContactUtility(Context appContext){
        mAppContext = appContext;
        mContacts = new ArrayList<Contact>();
        for (int i = 0; i < 50; i++){
            Contact c = new Contact();
            c.setName("Contact #" + i);
            c.setLastMsg("Bla bla" + i);
            mContacts.add(c);
        }
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

    public Contact getContact(UUID id){
        for (Contact c: mContacts){
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    //for our actionbar
    public void addContact(Contact c){
        mContacts.add(c);
    }

}

