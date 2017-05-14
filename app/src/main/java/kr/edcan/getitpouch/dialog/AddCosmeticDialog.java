package kr.edcan.getitpouch.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import kr.edcan.getitpouch.Constant;
import kr.edcan.getitpouch.R;
import kr.edcan.getitpouch.models.Cosmetic;
import kr.edcan.getitpouch.net.res.Common;
import kr.edcan.getitpouch.utils.ImageSingleton;
import kr.edcan.getitpouch.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaewookahn on 14/05/2017.
 */

public class AddCosmeticDialog extends DialogFragment implements View.OnClickListener {


    private static final int LAYOUT_RESOURCE = R.layout.dialog_barcode_popup;


    private View rootView;

    private Button cancel, ok;
    private NetworkImageView image;
    private TextView brand, product;

    private String barcode;

    Cosmetic cosmetic;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barcode = getArguments().getString(Constant.ADD_COSMETIC_DIALOG_BARCODE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        rootView = inflater.inflate(LAYOUT_RESOURCE, null);

        cancel = (Button) rootView.findViewById(R.id.negative);
        ok = (Button) rootView.findViewById(R.id.positive);
        image = (NetworkImageView) rootView.findViewById(R.id.dialog_prod_image);
        brand = (TextView) rootView.findViewById(R.id.dialog_brand_name);
        product = (TextView) rootView.findViewById(R.id.dialog_prod_name);
        brand.setText(cosmetic.brand_name);
        product.setText(cosmetic.name);
        image.setImageUrl(cosmetic.image_url, ImageSingleton.getInstance(getContext()).getImageLoader());

        NetworkHelper.getNetworkInstance().scanBarcode(barcode)
                .enqueue(new Callback<Cosmetic>() {
                    @Override
                    public void onResponse(Call<Cosmetic> call, Response<Cosmetic> response) {
                        if(response.isSuccessful() && response.body() != null) {
                            cosmetic = response.body();
                        } else {
                            onFailure(call, new Throwable(""));
                        }
                    }

                    @Override
                    public void onFailure(Call<Cosmetic> call, Throwable t) {
                        Toast.makeText(getActivity(), getContext().getString(R.string.network_fail),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                .setView(rootView);
        return dialog.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.negative:
                dismiss();
                break;
            case R.id.positive:
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
                                Toast.makeText(getContext(), getContext().getString(R.string.network_fail),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }

}
