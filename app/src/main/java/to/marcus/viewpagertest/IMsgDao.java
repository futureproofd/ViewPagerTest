package to.marcus.viewpagertest;

import android.content.Context;
import java.util.ArrayList;

/**
 * Created by marcus on 10/02/15.
 */
public interface IMsgDao {

    public ArrayList<Msg> getSMSConversation(String where);
    public void setSmsConversationArray(ArrayList<Msg> msgList);
    public ArrayList<Msg> getSmsConversationArray();
    public int getConvoSize();
    public Msg getLastMsg();
    public void addMsg(Msg msg);
}
