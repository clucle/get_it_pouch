package kr.edcan.getitpouch.fragment;

import android.content.Context;

import kr.edcan.getitpouch.databinding.FragmentSettingsBinding;

/**
 * Created by Junseok Oh on 2017-05-14.
 */

public class SettingsFragment {
    private Context context;
    private FragmentSettingsBinding fragmentSettingsBinding;

    public SettingsFragment(Context context, FragmentSettingsBinding fragmentSettingsBinding) {
        this.context = context;
        this.fragmentSettingsBinding = fragmentSettingsBinding;
    }
}
