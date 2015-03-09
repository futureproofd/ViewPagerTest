package to.marcus.viewpagertest;

import android.content.Context;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by marcus on 10/12/14.
 * Msg Objects
 */
public class Msg implements Serializable {

    private Long mId;
    private String mAddress;
    private String mBody;
    private Date mDate;
    private String mReceiver;
    private boolean mIsRead;
    private boolean mIsSeen;
    private Context mAppContext;
    private ArrayList<Msg> messageList;
    public String mThreadId;

    public Msg(){
      messageList = new ArrayList<Msg>();
    }

    public Msg(Msg firstMessage){
    }

    public void setId(Long id){mId = id;}

    public void setReceiver(String receiver){mReceiver = receiver;}

    public void setAddress(String address){mAddress = address;}

    public void setBody(String body){mBody = body;}

    public Date setDate(Long date){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        mDate = c.getTime();
        return mDate;
    }

    public void setIsRead(boolean b){mIsRead = b;}

    public void setIsSeen(boolean b){mIsSeen = b;}

    public String getBody(){return mBody;}

    public String getThreadId(){return mThreadId;}

    public void setThreadId(String threadid){mThreadId = threadid;}
}
