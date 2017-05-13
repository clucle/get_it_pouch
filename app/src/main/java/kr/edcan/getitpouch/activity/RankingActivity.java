package kr.edcan.getitpouch.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import java.util.ArrayList;

import kr.edcan.getitpouch.R;
import kr.edcan.getitpouch.databinding.ActivityRankingBinding;
import kr.edcan.getitpouch.models.Costemic;

public class RankingActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ActivityRankingBinding binding;
    private ArrayList<Costemic> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefault();
    }

    public void setDefault() {
        binding = (ActivityRankingBinding) baseBinding;
        binding.rankingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setData();
        LastAdapter.with(listData, BR._all)
                .map(Costemic.class, new ItemType<ViewDataBinding>(R.layout.ranking_list_content_top))
                .into(recyclerView);
    }

    private void setData() {
        listData.add(new Costemic());
        listData.add(new Costemic());
        listData.add(new Costemic());
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_ranking;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
