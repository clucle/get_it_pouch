package kr.edcan.getitpouch.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.Menu;

import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import kr.edcan.getitpouch.R;
import kr.edcan.getitpouch.databinding.ActivityMainBinding;
import kr.edcan.getitpouch.databinding.FragmentPouchBinding;
import kr.edcan.getitpouch.databinding.FragmentRankingBinding;
import kr.edcan.getitpouch.databinding.FragmentSaleBinding;
import kr.edcan.getitpouch.databinding.FragmentSettingsBinding;
import kr.edcan.getitpouch.fragment.PouchFragment;
import kr.edcan.getitpouch.fragment.RankingFragment;
import kr.edcan.getitpouch.fragment.SaleFragment;
import kr.edcan.getitpouch.fragment.SettingsFragment;
import kr.edcan.getitpouch.views.AliveFragmentView;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    AliveFragmentView aliveFragmentView;
    ArrayList<ViewDataBinding> fragmentBinding;

    PouchFragment pouchFragment;
    RankingFragment rankingFragment;
    SaleFragment saleFragment;
    SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setDefault() {
        initializeLayout();
        initializeBottomBar();
    }

    private void initializeLayout() {
        binding = (ActivityMainBinding) baseBinding;
        aliveFragmentView = binding.mainFragmentContainer;
        fragmentBinding = aliveFragmentView.addPage(R.layout.fragment_pouch,
                R.layout.fragment_ranking,
                R.layout.fragment_sale,
                R.layout.fragment_settings);
        pouchFragment = new PouchFragment(this, (FragmentPouchBinding) fragmentBinding.get(0));
        rankingFragment = new RankingFragment(this, (FragmentRankingBinding) fragmentBinding.get(1));
        saleFragment = new SaleFragment(this, (FragmentSaleBinding) fragmentBinding.get(2));
        settingsFragment = new SettingsFragment(this, (FragmentSettingsBinding) fragmentBinding.get(3));
        disableToggle();
        aliveFragmentView.switchPage(0);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setToolbarTitle("");
        getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.ic_getitpouch_mainpage));

    }

    void initializeBottomBar() {
        binding.mainBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (menu != null) {
                    menu.clear();
                    switch (tabId) {
                        case R.id.main_pouch:
                            aliveFragmentView.switchPage(0);
//                            setToolbarTitle(getResources().getString(R.string.pouch));
                            getSupportActionBar().setDisplayUseLogoEnabled(true);
                            setToolbarTitle("");
                            getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.ic_getitpouch_mainpage));
                            getMenuInflater().inflate(R.menu.menu_newsfeed, menu);
                            break;
                        case R.id.main_ranking:
                            aliveFragmentView.switchPage(1);
                            getSupportActionBar().setDisplayUseLogoEnabled(false);
                            getSupportActionBar().setIcon(null);
                            setToolbarTitle(getResources().getString(R.string.ranking));
                            break;
                        case R.id.main_sale:
                            aliveFragmentView.switchPage(2);
                            getSupportActionBar().setDisplayUseLogoEnabled(false);
                            getSupportActionBar().setIcon(null);
                            setToolbarTitle(getResources().getString(R.string.sale));
                            break;
                        case R.id.main_settings:
                            aliveFragmentView.switchPage(3);
                            getSupportActionBar().setDisplayUseLogoEnabled(false);
                            getSupportActionBar().setIcon(null);
                            setToolbarTitle(getResources().getString(R.string.settings));
                            break;
                    }
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newsfeed, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }
}
