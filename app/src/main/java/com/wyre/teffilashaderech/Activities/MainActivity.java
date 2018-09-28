package com.wyre.teffilashaderech.Activities;

import android.support.v4.app.Fragment;
import android.view.Menu;

import com.wyre.teffilashaderech.Fragments.TeffilahFragment;
import com.wyre.teffilashaderech.R;

public class MainActivity extends AbstractFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TeffilahFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.audio_toolbar_menu, menu);
        return true;
    }
}
