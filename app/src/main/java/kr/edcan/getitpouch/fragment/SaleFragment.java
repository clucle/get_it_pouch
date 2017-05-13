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
import kr.edcan.getitpouch.databinding.FragmentSaleBinding;
import kr.edcan.getitpouch.databinding.FragmentSettingsBinding;
import kr.edcan.getitpouch.databinding.RankingContentBinding;
import kr.edcan.getitpouch.databinding.SaleContentBinding;
import kr.edcan.getitpouch.handler.EventHandler;
import kr.edcan.getitpouch.models.Event;

/**
 * Created by Junseok Oh on 2017-05-14.
 */

public class SaleFragment {
    private Context context;
    private FragmentSaleBinding fragmentSaleBinding;
    private RecyclerView saleRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<Event> dataList = new ArrayList<>();
    private EventHandler eventHandler;

    public SaleFragment(Context context, FragmentSaleBinding fragmentSaleBinding) {
        this.context = context;
        this.fragmentSaleBinding = fragmentSaleBinding;
        setDefault();
    }

    private void setData() {
        Collections.addAll(dataList,
                new Event(),
                new Event(),
                new Event()
        );
    }

    private void setDefault() {

        eventHandler = new EventHandler(context);
        gridLayoutManager = new GridLayoutManager(context, 2);
        saleRecyclerView = fragmentSaleBinding.recyclerView;
        saleRecyclerView.setLayoutManager(gridLayoutManager);
        setData();
        LastAdapter.with(dataList, BR.content)
                .map(Event.class, new ItemType<SaleContentBinding>(R.layout.sale_content) {
                    @Override
                    public void onBind(@NotNull ViewHolder<SaleContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().setEventHandler(eventHandler);
                    }
                })
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        return R.layout.sale_content;
                    }
                })
                .into(saleRecyclerView);
    }
}
