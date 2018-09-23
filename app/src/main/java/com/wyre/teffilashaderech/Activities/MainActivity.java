package com.wyre.teffilashaderech.Activities;

import android.support.v4.app.Fragment;

import com.wyre.teffilashaderech.Fragments.TeffilahFragment;

public class MainActivity extends AbstractFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TeffilahFragment();
    }


}
