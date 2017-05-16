package kr.edcan.getitpouch.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    ImageView logo;
    TextView page_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setDefault() {
        initializeLayout();
        logo = (ImageView) findViewById(R.id.logo);
        page_title = (TextView) findViewById(R.id.page_title);
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
        setToolbarTitle("");

    }

    void initializeBottomBar() {
        binding.mainBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.main_pouch:
                        if (logo.getVisibility() != View.VISIBLE) {
                            logo.setVisibility(View.VISIBLE);
                        }
                        if (!page_title.getText().equals("")) {
                            page_title.setText("");
                        }
                        aliveFragmentView.switchPage(0);
                        break;
                    case R.id.main_ranking:
                        if (logo.getVisibility() != View.INVISIBLE) {
                            logo.setVisibility(View.INVISIBLE);
                        }
                        if (!page_title.getText().equals(getString(R.string.ranking))) {
                            page_title.setText(R.string.ranking);
                        }
                        aliveFragmentView.switchPage(1);
                        break;
                    case R.id.main_sale:
                        if (logo.getVisibility() != View.INVISIBLE) {
                            logo.setVisibility(View.INVISIBLE);
                        }
                        if (!page_title.getText().equals(getString(R.string.sale))) {
                            page_title.setText(R.string.sale);
                        }
                        aliveFragmentView.switchPage(2);
                        break;
                    case R.id.main_settings:
                        if (logo.getVisibility() != View.INVISIBLE) {
                            logo.setVisibility(View.INVISIBLE);
                        }
                        if (!page_title.getText().equals(getString(R.string.settings))) {
                            page_title.setText(R.string.settings);
                        }
                        aliveFragmentView.switchPage(3);
                        break;
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
