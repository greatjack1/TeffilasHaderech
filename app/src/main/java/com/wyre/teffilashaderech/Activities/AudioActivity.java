package com.wyre.teffilashaderech.Activities;

import android.support.v4.app.Fragment;
import com.wyre.teffilashaderech.Fragments.AudioFragment;

public class AudioActivity extends AbstractFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new AudioFragment();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
