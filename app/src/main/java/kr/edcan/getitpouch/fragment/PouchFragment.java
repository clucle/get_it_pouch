package kr.edcan.getitpouch.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kr.edcan.getitpouch.Data;
import kr.edcan.getitpouch.R;
import kr.edcan.getitpouch.activity.CameraActivity;
import kr.edcan.getitpouch.databinding.FragmentPouchBinding;
import kr.edcan.getitpouch.databinding.PouchContentBinding;
import kr.edcan.getitpouch.handler.EventHandler;
import kr.edcan.getitpouch.models.Costemic;
import kr.edcan.getitpouch.models.Costemics;
import kr.edcan.getitpouch.utils.ImageSingleton;
import kr.edcan.getitpouch.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junseok Oh on 2017-05-14.
 */

public class PouchFragment implements Data.DataChangeListener {
    private Context context;
    private RecyclerView pouchRecyclerView;
    private FragmentPouchBinding fragmentPouchBinding;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<Costemic> dataList = new ArrayList<>();
    private EventHandler eventHandler;
    private FloatingActionButton fab;

    private String barcode;

    public PouchFragment(Context context, FragmentPouchBinding fragmentPouchBinding) {
        this.context = context;
        this.fragmentPouchBinding = fragmentPouchBinding;

        NetworkHelper.getNetworkInstance().getCosmetics
                ("10612")
                .enqueue(new Callback<Costemics>() {
            @Override
            public void onResponse(Call<Costemics> call, Response<Costemics> response) {
                //response.code //response.body //
                if (response.code() == 200 && response.body().item != null) {
                    dataList = response.body().item;
                    // pass
                } else {
                    onFailure(call, new Throwable(""));
                }
            }

            @Override
            public void onFailure(Call<Costemics> call, Throwable t) {
                Log.e("[Server]", t.getLocalizedMessage());
            }
        });

        setDefault();
    }

    private void setData() {
        dataList.add(new Costemic());
        dataList.add(new Costemic());
        dataList.add(new Costemic());
        dataList.add(new Costemic());
        dataList.add(new Costemic());
        dataList.add(new Costemic());
        dataList.add(new Costemic());
        onClickAddCosmetic();
    }

    public void onClickAddCosmetic() {

        new MaterialDialog.Builder(context)
                .title("등록")
                .content("adsf")
                .positiveText("예")
                .negativeText("아니오")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                }).show();
    }

    private void setDefault() {

        eventHandler = new EventHandler(context);
        gridLayoutManager = new GridLayoutManager(context, 2);
        pouchRecyclerView = fragmentPouchBinding.pouchRecyclerView;
        pouchRecyclerView.setLayoutManager(gridLayoutManager);
        fab = fragmentPouchBinding.addCosmeticFab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CameraActivity.class));
            }
        });
        setData();
        LastAdapter.with(dataList, BR.content)
                .map(Costemic.class, new ItemType<PouchContentBinding>(R.layout.pouch_content) {
                    @Override
                    public void onBind(@NotNull ViewHolder<PouchContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().setEventHandler(eventHandler);
                        viewHolder.getBinding().image.setImageUrl
                                (dataList.get(viewHolder.getPosition()).imageUrl,
                                        ImageSingleton.getInstance(context).getImageLoader());
                    }
                })
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        return R.layout.pouch_content;
                    }
                })
                .into(pouchRecyclerView);

        //  set data class listener
        Data.getInstance().listener = this;
    }

    public void popupBarcodeDialog() {
    }

    @Override
    public void onDataChange() {
        barcode = Data.getInstance().barcodeData;
        popupBarcodeDialog();
    }
}
