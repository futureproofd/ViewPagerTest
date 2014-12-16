package to.marcus.viewpagertest;

import android.support.v4.app.Fragment;

public class MainListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new MainListFragment();
    }
}
