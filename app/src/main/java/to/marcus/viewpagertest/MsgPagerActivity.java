package to.marcus.viewpagertest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by marcus on 07/12/14.
 */
public class MsgPagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private CustomPagerAdapter mAdapter;
    private static final String TAG = "MsgPagerActivity";

    @Override
    public void onCreate(Bundle savedInstanceState){
        Log.i(TAG, "1) Creating Pager Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "2) Clicked for new SMS");
                onSMSReceived("Test SMS");
            }
        });
    }

    public void onSMSReceived(String message) {
        //get fake SMS array
        int currentCount = mAdapter.getLastMessages().getMessageCount();
        if(currentCount == 0 || currentCount == 3){
            //create new object
            Log.i(TAG, "3) create new message:");
            Messages messages = new Messages(message);
            mAdapter.addMessages(messages);
            //tell the adapter data has changed
            mAdapter.notifyDataSetChanged();
            //scroll to the last page!
            mViewPager.setCurrentItem(mAdapter.getCount(), true);
        }else{
            Log.i(TAG, "message > 0 but less than 3");
            //scroll to the last page
            mViewPager.setCurrentItem(mAdapter.getCount(), true);
            //update the current fragment with the last message
            Log.i(TAG, "get previous fragment at position and add a message");

            MsgFragment currentFragment = (MsgFragment)mAdapter.getFragmentAtPosition(
                    mAdapter.getCount() - 1);
            currentFragment.addMessage(message);
        }
    }
}