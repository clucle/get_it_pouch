package kr.edcan.getitpouch.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.toolbox.NetworkImageView;
import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kr.edcan.getitpouch.Constant;
import kr.edcan.getitpouch.Data;
import kr.edcan.getitpouch.R;
import kr.edcan.getitpouch.activity.CameraActivity;
import kr.edcan.getitpouch.databinding.FragmentPouchBinding;
import kr.edcan.getitpouch.databinding.PouchContentBinding;
import kr.edcan.getitpouch.dialog.AddCosmeticDialog;
import kr.edcan.getitpouch.handler.EventHandler;
import kr.edcan.getitpouch.models.Cosmetic;
import kr.edcan.getitpouch.models.Costemics;
import kr.edcan.getitpouch.net.res.Common;
import kr.edcan.getitpouch.utils.ImageSingleton;
import kr.edcan.getitpouch.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junseok Oh on 2017-05-14.
 */

public class PouchFragment implements Data.DataChangeListener {
    private AppCompatActivity context;
    private RecyclerView pouchRecyclerView;
    private FragmentPouchBinding fragmentPouchBinding;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<Cosmetic> dataList = new ArrayList<>();
    private EventHandler eventHandler;
    private FloatingActionButton fab;
    Cosmetic cosmetic;

    private View rootView;

    private Button cancel, ok;
    private NetworkImageView image;
    private TextView brand, product;

    private String barcode;

    public PouchFragment(AppCompatActivity context, FragmentPouchBinding fragmentPouchBinding) {
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
                    setDefault();
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


    }

    private void setData() {
        dataList.add(new Cosmetic());
        dataList.add(new Cosmetic());
        dataList.add(new Cosmetic());
        dataList.add(new Cosmetic());
        dataList.add(new Cosmetic());
        dataList.add(new Cosmetic());
        dataList.add(new Cosmetic());
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
        //setData();
        LastAdapter.with(dataList, BR.content)
                .map(Cosmetic.class, new ItemType<PouchContentBinding>(R.layout.pouch_content) {
                    @Override
                    public void onBind(@NotNull ViewHolder<PouchContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().setEventHandler(eventHandler);
                        viewHolder.getBinding().image.setImageUrl(dataList.get(viewHolder.getPosition()).image_url,
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
//        AddCosmeticDialog dialog = new AddCosmeticDialog();
//        Bundle b = new Bundle();
//        b.putString(Constant.ADD_COSMETIC_DIALOG_BARCODE, barcode);
//        dialog.setArguments(b);
//        dialog.show(context.getSupportFragmentManager(), "cosmetic");


        rootView = context.getLayoutInflater().inflate(R.layout.dialog_barcode_popup, null);

        cancel = (Button) rootView.findViewById(R.id.negative);
        ok = (Button) rootView.findViewById(R.id.positive);
        image = (NetworkImageView) rootView.findViewById(R.id.dialog_prod_image);
        brand = (TextView) rootView.findViewById(R.id.dialog_brand_name);
        product = (TextView) rootView.findViewById(R.id.dialog_prod_name);

        NetworkHelper.getNetworkInstance().scanBarcode(barcode)
                .enqueue(new Callback<Cosmetic>() {
                    @Override
                    public void onResponse(Call<Cosmetic> call, Response<Cosmetic> response) {
                        if(response.isSuccessful() && response.body() != null) {
                            cosmetic = response.body();
                            brand.setText(cosmetic.brand_name);
                            product.setText(cosmetic.name);
                            image.setImageUrl(cosmetic.image_url, ImageSingleton.getInstance(context).getImageLoader());
                            new MaterialDialog.Builder(context)
                                    .customView(rootView, false)
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            NetworkHelper.getNetworkInstance().addCosmetic("10612", cosmetic.product_id)
                                                    .enqueue(new Callback<Common>() {
                                                        @Override
                                                        public void onResponse(Call<Common> call, Response<Common> response) {
                                                            if(response.isSuccessful() && response.body().status.equals("true")) {

                                                            } else {
                                                                onFailure(call, new Throwable(""));
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Common> call, Throwable t) {
                                                            Log.e("asdf", t.getLocalizedMessage());
                                                            Toast.makeText(context, context.getString(R.string.network_fail),
                                                                    Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    })
                                    .positiveText("확인")
                                    .negativeText("취소")
                                    .show();
                        } else {
                            onFailure(call, new Throwable(""));
                        }
                    }

                    @Override
                    public void onFailure(Call<Cosmetic> call, Throwable t) {
                        Log.e("asdf", t.getLocalizedMessage());
                        Toast.makeText(context, context.getResources().getString(R.string.network_fail),
                                Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    public void onDataChange() {
        barcode = Data.getInstance().barcodeData;
        popupBarcodeDialog();
    }
}
