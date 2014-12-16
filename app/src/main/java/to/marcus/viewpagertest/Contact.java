package to.marcus.viewpagertest;

/**
 * Created by marcus on 26/11/14.
 */
import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class Contact {

    private UUID mId;
    private String mName;
    private String mLastMsg;
    private Context mContext;

    public Contact(){
        //get a new UUID (realistically this would be the ID of the ongoing conversation record)
        mId = UUID.randomUUID();
    }

    //override object class array adapter default conversion
    @Override
    public String toString(){
        return mName;
    }

    public UUID getId(){
        return mId;
    }

    public String getName(){
        return mName;
    }

    public String getLastMsg(){
        return mLastMsg;
    }

    public void setLastMsg(String msg){
        mLastMsg = msg;
    }

    public void setName(String name){
        mName = name;
    }

}
