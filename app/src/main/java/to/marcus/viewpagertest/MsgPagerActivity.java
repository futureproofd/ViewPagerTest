package to.marcus.viewpagertest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus on 07/12/14.
 * MsgPagerActivity
 * Need to change the onClickListener to listen for new incoming messages - just for testing
 * Need to put populateMsgViews into an AsyncTask
 */
public class MsgPagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private CustomPagerAdapter mAdapter;
    private static final String TAG = "MsgPagerActivity";
    public static final String EXTRA_THREAD_ID = "to.marcus.viewpagertest.thread_id";

    @Override
    public void onCreate(Bundle savedInstanceState){
        final String threadId = getIntent().getStringExtra(EXTRA_THREAD_ID);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        //Initialize DAO
        final MsgDaoUtility daoMsg = new MsgDaoUtility(getApplicationContext());
        //changed to final to access within inner-class
        final ArrayList<Msg> mMsgConvoList = daoMsg.getSMSConversation(threadId);
        daoMsg.setSmsConversationArray(mMsgConvoList);
        final List msgPartitions = daoMsg.partitionArray(mMsgConvoList, 3);
        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "2) Clicked for new SMS");
                onSMSReceived(msgPartitions);
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {Log.i(TAG, "Changed Page/Fragment " + position);}
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    public void populateMsgViews(List msgPartitions){
        for(int i = msgPartitions.size() - 1; i >= 0; i--){
            //Build each sublist:
            List<Msg> msgSublist = (List<Msg>)msgPartitions.get(i);
            int count = msgSublist.size() - 1;
            //initially a count of 2
            while(count >= 0){
                if (count == 2 || count == 0){
                    mAdapter.addMessages(msgSublist.get(count));
                    mAdapter.notifyDataSetChanged();
                    mViewPager.setCurrentItem(mAdapter.getCount(), true);
                }else{
                    mViewPager.setCurrentItem(mAdapter.getCount(), true);
                    MsgFragment currentFragment = (MsgFragment)mAdapter.getFragmentAtPosition(
                            mAdapter.getCount() - 1);
                    currentFragment.addMessage(msgSublist.get(count));
                }
                count--;
            }
        }
    }

    public void onSMSReceived(List list) {
        populateMsgViews(list);
    }
}