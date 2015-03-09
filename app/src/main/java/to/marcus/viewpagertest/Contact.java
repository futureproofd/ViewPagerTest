package to.marcus.viewpagertest;

/**
 * Created by marcus on 26/11/14.
 */

public class Contact {

    private String mId;
    private String mName;
    private String mLastMsg;
    private String mThreadId;

    public Contact(){}

    //override object class array adapter default conversion
    @Override
    public String toString(){
        return mName;
    }

    public String getId(){
        return mId;
    }

    public String getThreadId(){return mThreadId;}

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

    public void setId(String id){mId = id;}

    public void setThreadId(String id){mThreadId = id;}

}
