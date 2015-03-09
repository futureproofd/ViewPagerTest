package to.marcus.viewpagertest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus on 27/11/14.
 * MsgFragment
 * newInstance needs to receive only the latest sublist
 *  otherwise onCreateView will overpopulate
 */
public class MsgFragment extends Fragment{

    private static final String TAG = "MsgFragment";
    private static final String MESSAGE_KEY = "messages";
    private ArrayList<Msg> messageList = new ArrayList<Msg>();
    private List<TextView> textViewList = new ArrayList<TextView>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            messageList = (ArrayList)args.getSerializable(MESSAGE_KEY);
        }
        Log.i(TAG, "onCreate " + messageList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.msg_fragment_layout, container, false);
        textViewList.clear();
        //add the 3 textviews for each different case  (determined by getItemViewType)
        textViewList.add((TextView) v.findViewById(R.id.text_1));
        textViewList.add((TextView) v.findViewById(R.id.text_2));
        textViewList.add((TextView) v.findViewById(R.id.text_3));
        Log.i(TAG, "updating text views");
        updateTextViews();
        return v;
    }

    public void addMessage(Msg message){
        Log.i(TAG, "adding message to list: " + message);
        messageList.add(message);
        updateTextViews();
    }

    private void updateTextViews(){
        for (int i = 0; i < messageList.size(); i++){
            Log.i(TAG, "Populating textViews: " + i);
            Msg msg = messageList.get(i);
            String body = msg.getBody();
            Log.i(TAG, "msg body: " + body);
            textViewList.get(i).setText(body);
        }
    }

    //Bundle args into new fragment on activity launch
    public static MsgFragment newInstance(ArrayList<Msg> msgList){
        MsgFragment fragment = new MsgFragment();
        Bundle args = new Bundle();
        args.putSerializable(MESSAGE_KEY, msgList);
        fragment.setArguments(args);
        return fragment;
    }
}