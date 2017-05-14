package kr.edcan.getitpouch.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kr.edcan.getitpouch.R;
import kr.edcan.getitpouch.databinding.ActivityRankingBinding;
import kr.edcan.getitpouch.databinding.RankingListContentBinding;
import kr.edcan.getitpouch.models.Cosmetic;

public class RankingActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ActivityRankingBinding binding;
    private ArrayList<Cosmetic> listData = new ArrayList<>();
    private int sortType = 0; // 0 인기순위 1 재구매율
    private int ageType = 0; // 0 선택ㄴㄴ 1 10대 2 20대 3 30대 4 40대+
    private int timeType = 0; // 0 선택ㄴㄴ 1 3개월 2 6개월

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefault();
    }

    public void setDefault() {
        binding = (ActivityRankingBinding) baseBinding;
        recyclerView = binding.rankingRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.repurchase.setOnClickListener(this);
        binding.byage.setOnClickListener(this);
        binding.bytime.setOnClickListener(this);
        setData();
        setToolbarTitle("랭킹");
        LastAdapter.with(listData, BR._all)
                .map(Cosmetic.class, new ItemType<RankingListContentBinding>(R.layout.ranking_list_content) {
                    @Override
                    public void onBind(@NotNull ViewHolder<RankingListContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().position.setText(viewHolder.getPosition() + "");
                    }
                })
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        return R.layout.ranking_list_content;
                    }
                })
                .into(recyclerView);
    }

    private void setData() {
        listData.add(new Cosmetic());
        listData.add(new Cosmetic());
        listData.add(new Cosmetic());
        listData.add(new Cosmetic());
        listData.add(new Cosmetic());
        listData.add(new Cosmetic());
        listData.add(new Cosmetic());
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_ranking;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repurchase:
                new MaterialDialog.Builder(this)
                        .title("정렬기준")
                        .items(new String[]{"인기순위", "재구매율"})
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                switch (position) {
                                    case 0:
                                        // 인기순위
                                        sortType = 0;
                                        binding.repurchase.setText("인기순위");
                                        break;
                                    case 1:
                                        sortType = 1;
                                        binding.repurchase.setText("재구매율");
                                        break;
                                }
                            }
                        })
                        .show();
                break;
            case R.id.byage:
                new MaterialDialog.Builder(this)
                        .title("연령대 선택")
                        .items(new String[]{"전체", "10대", "20대", "30대", "40대+"})
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                switch (position) {
                                    case 0:
                                        // 전체
                                        ageType = 0;
                                        binding.byage.setText("연령대");
                                        binding.byage.setFullMode(false);
                                        break;
                                    case 1:
                                        ageType = 1;
                                        binding.byage.setText("10대");
                                        binding.byage.setFullMode(true);
                                        break;
                                    case 2:
                                        ageType = 2;
                                        binding.byage.setText("20대");
                                        binding.byage.setFullMode(true);
                                        break;
                                    case 3:
                                        ageType = 3;
                                        binding.byage.setText("30대");
                                        binding.byage.setFullMode(true);
                                        break;
                                    case 4:
                                        ageType = 4;
                                        binding.byage.setText("40대+");
                                        binding.byage.setFullMode(true);
                                        break;
                                }
                            }
                        })
                        .show();
                break;
            case R.id.bytime:
                new MaterialDialog.Builder(this)
                        .title("기간별 선택")
                        .items(new String[]{"전체", "3개월", "6개월"})
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                switch (position) {
                                    case 0:
                                        // 전체
                                        timeType = 0;
                                        binding.bytime.setText("기간별");
                                        binding.bytime.setFullMode(false);
                                        break;
                                    case 1:
                                        timeType = 1;
                                        binding.bytime.setText("3개월");
                                        binding.bytime.setFullMode(true);
                                        break;
                                    case 2:
                                        ageType = 2;
                                        binding.bytime.setText("6개월");
                                        binding.bytime.setFullMode(true);
                                        break;
                                }
                            }
                        })
                        .show();
                break;

        }
    }
}
