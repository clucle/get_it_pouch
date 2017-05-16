package kr.edcan.getitpouch.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.getitpouch.R;
import kr.edcan.getitpouch.databinding.FragmentRankingBinding;
import kr.edcan.getitpouch.databinding.RankingContentBinding;
import kr.edcan.getitpouch.handler.EventHandler;
import kr.edcan.getitpouch.models.Cosmetic;

/**
 * Created by Junseok Oh on 2017-05-14.
 */

public class RankingFragment {
    private Context context;
    private FragmentRankingBinding fragmentRankingBinding;
    private RecyclerView rankingRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<String> dataList = new ArrayList<>();
    private ArrayList<Cosmetic> cosmeticList = new ArrayList<>();
    private EventHandler eventHandler;
    public RankingFragment(Context context, FragmentRankingBinding fragmentRankingBinding) {
        this.context = context;
        this.fragmentRankingBinding = fragmentRankingBinding;

        setDefault();
    }

    private void setData() {
        Collections.addAll(dataList,
                "스킨케어", "클렌징", "메이크업", "색조메이크업", "립", "선케어", "기능성화장품", "남성용화장품");
    }
    private void setDefault() {

        eventHandler = new EventHandler(context);
        gridLayoutManager = new GridLayoutManager(context, 2);
        rankingRecyclerView = fragmentRankingBinding.recyclerView;
        rankingRecyclerView.setLayoutManager(gridLayoutManager);
        setData();
        LastAdapter.with(dataList, BR.content)
                .map(String.class, new ItemType<RankingContentBinding>(R.layout.ranking_content) {
                    @Override
                    public void onBind(@NotNull ViewHolder<RankingContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().setEventHandler(eventHandler);
                    }
                })
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        return R.layout.ranking_content;
                    }
                })
                .into(rankingRecyclerView);
    }

}
