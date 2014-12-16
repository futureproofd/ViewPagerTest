package to.marcus.viewpagertest;

import java.util.ArrayList;

/**
 * Created by marcus on 10/12/14.
 * Fake SMS arrayList of messages - populated on test button click
 */
public class Messages {

    private ArrayList<String> messageList;

    public Messages(){
        messageList = new ArrayList<String>();
    }

    public Messages(String firstMessage){
        this.messageList = new ArrayList<String>();
        this.messageList.add(firstMessage);
    }

    public ArrayList<String> getMessageList(){
        return messageList;
    }

    public int getMessageCount(){
        return messageList.size();
    }


}
