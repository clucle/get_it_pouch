package kr.edcan.getitpouch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import kr.edcan.getitpouch.R;
import kr.edcan.getitpouch.databinding.ActivityRankingBinding;
import kr.edcan.getitpouch.databinding.RankingListContentBinding;
import kr.edcan.getitpouch.models.Cosmetic;
import kr.edcan.getitpouch.models.Cosmetics;
import kr.edcan.getitpouch.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ActivityRankingBinding binding;
    private List<Cosmetic> listData = new ArrayList<>();
    private String sortType = "popularity";
    private String ageType = "all";
    private String timeType = "all";
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        category = intent.getStringExtra("ranking");
        setDefault();
    }

    public void setDefault() {
        binding = (ActivityRankingBinding) baseBinding;
        recyclerView = binding.rankingRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.repurchase.setOnClickListener(this);
        binding.byage.setOnClickListener(this);
        binding.bytime.setOnClickListener(this);
        //setData();
        listData.clear();

        setToolbarTitle("랭킹");
    }

    @Override
    protected void onResume() {
        super.onResume();

        NetworkHelper.getNetworkInstance().getRank(sortType, ageType, timeType, category)
                .enqueue(new Callback<Cosmetics>() {
                    @Override
                    public void onResponse(Call<Cosmetics> call, Response<Cosmetics> response) {
                        //response.code //response.body //
                        if (response.code() == 200 && response.body() != null) {
                            listData = response.body().item;
                            LastAdapter.with(listData, BR.content)
                                    .map(Cosmetic.class, new ItemType<RankingListContentBinding>(R.layout.ranking_list_content) {
                                        @Override
                                        public void onBind(@NotNull ViewHolder<RankingListContentBinding> viewHolder) {
                                            super.onBind(viewHolder);
                                            viewHolder.getBinding().position.setText((viewHolder.getAdapterPosition() + 1) + "");
                                            viewHolder.getBinding().rankingName.setText(listData.get(viewHolder.getAdapterPosition()).name);
                                            viewHolder.getBinding().rankingBrandName.setText(listData.get(viewHolder.getAdapterPosition()).brand_name);
                                            viewHolder.getBinding().rankingPrice.setText(listData.get(viewHolder.getAdapterPosition()).price);
                                        }
                                    })
                                    .handler(new LayoutHandler() {
                                        @Override
                                        public int getItemLayout(@NotNull Object o, int i) {
                                            return R.layout.ranking_list_content;
                                        }
                                    })
                                    .into(recyclerView);
                        } else {
                            onFailure(call, new Throwable(""));
                        }
                    }

                    @Override
                    public void onFailure(Call<Cosmetics> call, Throwable t) {
                        Log.e("[Server]", t.getLocalizedMessage());
                    }
                });



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
                                        sortType = "popularity";
                                        binding.repurchase.setText("인기순위");
                                        break;
                                    case 1:
                                        sortType = "re_purchase";
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
                                        ageType = "all";
                                        binding.byage.setText("연령대");
                                        binding.byage.setFullMode(false);
                                        break;
                                    case 1:
                                        ageType = "10";
                                        binding.byage.setText("10대");
                                        binding.byage.setFullMode(true);
                                        break;
                                    case 2:
                                        ageType = "20";
                                        binding.byage.setText("20대");
                                        binding.byage.setFullMode(true);
                                        break;
                                    case 3:
                                        ageType = "30";
                                        binding.byage.setText("30대");
                                        binding.byage.setFullMode(true);
                                        break;
                                    case 4:
                                        ageType = "40+";
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
                                        timeType = "all";
                                        binding.bytime.setText("기간별");
                                        binding.bytime.setFullMode(false);
                                        break;
                                    case 1:
                                        timeType = "3";
                                        binding.bytime.setText("3개월");
                                        binding.bytime.setFullMode(true);
                                        break;
                                    case 2:
                                        ageType = "6";
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
