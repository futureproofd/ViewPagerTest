package to.marcus.viewpagertest;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus on 29/01/15.
 * Query SMS Content provider
 */
public class MsgDaoUtility implements IMsgDao{

    private static final String TAG = "MsgDaoUtility";
    private static final Uri SMS_URI = Uri.parse("content://sms");
    private static final Uri SMS_CONVO_URI = Uri.parse("content://mms-sms/conversations");
    private static final String COLUMNS[] = new String[]{"_id","person", "address", "body", "date","read", "type","seen"};
    public ArrayList<Msg> mMsgArrayList;
    private Context context;

    //default constructor
    public MsgDaoUtility(Context context){
        this.context = context;
    }

    //get contacts with SMS for main list
    static public ArrayList<Contact> getAllSmsContacts(Context context) {
        ArrayList<Contact> sms = new ArrayList<Contact>();
        final String[] projection = new String[]{"*"};
        Cursor cursor = context.getContentResolver().query
                (SMS_CONVO_URI
                        , projection
                        , null
                        , null
                        , "date desc"
                );

        while (cursor.moveToNext()) {
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
            String threadId = cursor.getString(cursor.getColumnIndex("thread_id"));
            //String read = cursor.getString(cursor.getColumnIndexOrThrow("read"));
            //extract contact name
            Uri nameUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI
                    , Uri.encode(address));
            Cursor cursor1 = context.getContentResolver().query
                    (nameUri
                            , new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME
                                    , ContactsContract.PhoneLookup._ID
                            }
                            , ContactsContract.PhoneLookup.NUMBER + "='" + address + "'"
                            , null
                            , null
                    );
            if (cursor1.getCount() > 0) {
                cursor1.moveToFirst();
                Contact mContact = new Contact();
                mContact.setName(cursor1.getString(cursor1.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)));
                mContact.setId(cursor1.getString(cursor1.getColumnIndex(ContactsContract.PhoneLookup._ID)));
                mContact.setLastMsg(body);
                mContact.setThreadId(threadId);
                sms.add(mContact);
            } else {
                cursor1.close();
            }
        }
        cursor.close();
        return sms;
    }

    @Override
    public int getConvoSize() {
        return mMsgArrayList.size();
    }

    @Override
    public void addMsg(Msg msg){
        mMsgArrayList.add(msg);
    }

    @Override
    public Msg getLastMsg() {
        if (mMsgArrayList.size() > 0){
            //get first element (latest message)
            return mMsgArrayList.get(mMsgArrayList.size() - 1);
        }else{
            Log.i(TAG, "creating first message object");
            return new Msg();
        }
    }

    @Override
    public ArrayList<Msg> getSmsConversationArray(){return mMsgArrayList;}

    @Override
    public void setSmsConversationArray(ArrayList<Msg> msgArrayList){mMsgArrayList = msgArrayList;}

    @Override
    public ArrayList<Msg> getSMSConversation(String where) {
        ArrayList<Msg> msgArrayList = new ArrayList<Msg>();
        Cursor c = context.getContentResolver().query
                (SMS_URI
                , COLUMNS
                , "thread_id = " +where
                , null
                , null
        );
        try {
            while (c.moveToNext()) {
                Msg mMsg = new Msg();
                mMsg.setId(c.getLong(c.getColumnIndex("_id")));
                mMsg.setAddress(c.getString(c.getColumnIndex("address")));
                mMsg.setBody(c.getString(c.getColumnIndex("body")));
                mMsg.setDate(c.getLong(c.getColumnIndex("date"))); //need to convert this in setDate method
                int read = c.getInt(c.getColumnIndex("read"));
                if (read == 1) {
                    mMsg.setIsRead(true);
                } else {
                    mMsg.setIsRead(false);
                }
                int seen = c.getInt(c.getColumnIndex("seen"));
                if (seen == 1) {
                    mMsg.setIsSeen(true);
                } else {
                    mMsg.setIsSeen(false);
                }
                //added this to get the thread id
                mMsg.setThreadId(where);
                msgArrayList.add(mMsg);
            }
        } catch (Exception e) {
            Log.i(TAG, "error occurred fetching SMSes: " + e.getMessage());
        } finally {
            c.close();
            return msgArrayList;
        }

    }

    //add a method to split the arraylist
    public List<List<Msg>> partitionArray(ArrayList<Msg> msgArrayList, int partitionSize){
        return Lists.partition(msgArrayList, partitionSize);
    }

}