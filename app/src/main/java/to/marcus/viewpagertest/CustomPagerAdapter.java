package to.marcus.viewpagertest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Created by marcus on 10/12/14.
 * addMessage method needs fixing - need to split into smaller arrays for passing on to the fragment
 * getItem method needs a newInstance of only the latest sublist
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    SparseArray<Fragment> fragmentSparseArray = new SparseArray<Fragment>();
    private ArrayList<Msg> mMessageList = new ArrayList<Msg>();
    private static final String TAG = "CustomPagerAdapter";

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.i(TAG, "Instantiating adapter");
    }

    @Override
    public Fragment getItem(int position) {
        Log.i(TAG, "Creating new Fragment " + position);
        return MsgFragment.newInstance(mMessageList);
    }

    @Override
    public int getCount() {
        return mMessageList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentSparseArray.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fragmentSparseArray.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getFragmentAtPosition(int position) {
        return fragmentSparseArray.get(position);
    }

    public void addMessages(Msg msg) {
        Log.i(TAG, "adding message to list");
        mMessageList.add(msg);
    }
}