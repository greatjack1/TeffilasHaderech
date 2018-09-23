package com.wyre.teffilashaderech.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceLab {
    private Context mContext;
    private static PreferenceLab mLab;
    private final String SHOWEDDIALOGKEY = "showedidalogkey";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private PreferenceLab(Context context){
        mContext = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = prefs.edit();
    }
    public static PreferenceLab getPreferenceLab(Context context){
        if(mLab==null){
         mLab= new PreferenceLab(context);
        }
        return mLab;
    }

    public void setShowedWelcomeDialog(boolean showed) {
        editor.putBoolean(SHOWEDDIALOGKEY, showed);
        editor.commit();
    }

    public boolean getShowedWelcomeDialog() {
        return prefs.getBoolean(SHOWEDDIALOGKEY, false);
    }


}
