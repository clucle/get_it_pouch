package kr.edcan.getitpouch.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.toolbox.ImageLoader;
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
import kr.edcan.getitpouch.models.Cosmetic;
import kr.edcan.getitpouch.models.Event;
import kr.edcan.getitpouch.utils.ImageSingleton;

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
                new Event("에뛰드 하우스", "~05.14", "sale1", "http://www.etude.co.kr/event.do?method=view_new&bltnCntSeq=1741&bltnCntClCd=ET01&bbsCd=ET&pageNum=1&isOnGoing=Y"),
                new Event("", "", "", ""),
                new Event("", "", "", "")
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
                    public void onBind(@NotNull final ViewHolder<SaleContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().cardview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dataList.get(viewHolder.getAdapterPosition()).getLink()));
                                Log.d("AAA", "onClick: ");
                                context.startActivity(intent);
                            }
                        });
                        viewHolder.getBinding().setEventHandler(eventHandler);
                        Event itemCosmetic = dataList.get(viewHolder.getPosition());
                        viewHolder.getBinding().title.setText(itemCosmetic.getTitle());
                        viewHolder.getBinding().content.setText(itemCosmetic.getDate());
                        if (itemCosmetic.getImageUrl().equals("sale1"))
                            viewHolder.getBinding().image.setImageResource(R.drawable.img_sale1);
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
