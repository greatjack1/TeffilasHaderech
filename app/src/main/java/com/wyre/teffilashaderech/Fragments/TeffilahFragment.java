package com.wyre.teffilashaderech.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.vorlonsoft.android.rate.AppRate;
import com.wyre.teffilashaderech.Activities.AudioActivity;
import com.wyre.teffilashaderech.Dialogs.FlexibleDialogFragment;
import com.wyre.teffilashaderech.R;
import com.wyre.teffilashaderech.Utilities.PreferenceLab;
import com.wyre.teffilashaderech.Views.ZoomTextView;

public class TeffilahFragment extends Fragment  {
    private Toolbar mToolbar;
    private ZoomTextView mTeffilaView;
    private DrawerLayout mDrawerLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.teffilah_fragment, container, false);
        setHasOptionsMenu(true);
        mTeffilaView = v.findViewById(R.id.teffilah_view);
        mDrawerLayout = v.findViewById(R.id.drawer_layout);
        mTeffilaView.setMovementMethod(new ScrollingMovementMethod());
        //set the support action bar in the fragment
        AppCompatActivity act = (AppCompatActivity) getActivity();
        act.setSupportActionBar((Toolbar) v.findViewById(R.id.toolbar));
        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ActionBar actionbar = act.getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        //set the click listener for the navigation draw items
        final NavigationView navigationView = v.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mDrawerLayout.closeDrawers();
                        switch (menuItem.getItemId()) {
                            case R.id.about_app_menu_item:
                                // Use the flexible dialog fragment to show the dialog
                                FlexibleDialogFragment.ShowDialog(getActivity().getSupportFragmentManager(),"About",getString(R.string.about_text));
                                break;
                            case R.id.hebrew_version:
                            mTeffilaView.setText(R.string.hebrew_text);
                                break;
                            case R.id.english_version:
                                mTeffilaView.setText(R.string.english_text);
                                break;
                            case R.id.transliterated_version:
                                mTeffilaView.setText(R.string.transliterated_text);
                                break;
                            case R.id.say_along_audio:
                                Intent intent = new Intent(getContext(), AudioActivity.class);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
        return v;
    }

    @Override
    public void onResume() {
        setRetainInstance(true);
        super.onResume();
        //show a dialog to the user encouraging them to rate the app
        AppRate.with(getActivity())
                .setInstallDays((byte) 0)
                .setLaunchTimes((byte) 2)
                .setMessage(getString(R.string.rate_dialog_message_custom))
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(getActivity());
        //show a welcome dialog with a guide first time the user opens the application
        if(PreferenceLab.getPreferenceLab(getContext().getApplicationContext()).getShowedWelcomeDialog()==false) {
            FlexibleDialogFragment.ShowDialog(getActivity().getSupportFragmentManager(), "Welcome", getString(R.string.welcome_text));
        PreferenceLab.getPreferenceLab(getContext().getApplicationContext()).setShowedWelcomeDialog(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //this is to handle the home menu item so that the drawer opens when it is clicked
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.audio_menu_button:
                Intent intent = new Intent(getContext(), AudioActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
