package kr.edcan.getitpouch.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.getitpouch.R;
import kr.edcan.getitpouch.databinding.ContentSettingsBinding;
import kr.edcan.getitpouch.databinding.FragmentSettingsBinding;

/**
 * Created by Junseok Oh on 2017-05-14.
 */

public class SettingsFragment {
    private Context context;
    private FragmentSettingsBinding fragmentSettingsBinding;
    private RecyclerView settingsRecyclerView;
    private ArrayList<String> dataList = new ArrayList<>();

    public SettingsFragment(Context context, FragmentSettingsBinding fragmentSettingsBinding) {
        this.context = context;
        this.fragmentSettingsBinding = fragmentSettingsBinding;
        setDefault();
    }

    private void setDefault() {
        settingsRecyclerView = fragmentSettingsBinding.settingsRecyclerView;
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        setData();
        LastAdapter.with(dataList, BR.content)
                .map(String.class, new ItemType<ContentSettingsBinding>(R.layout.content_settings))
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        return R.layout.content_settings;
                    }
                })
                .into(settingsRecyclerView);
    }

    private void setData() {
        Collections.addAll(dataList,
                "계정설정", "푸시알림", "공지사항", "이용약관", "개인정보 취급방침");
    }
}
