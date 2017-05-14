package kr.edcan.getitpouch.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import kr.edcan.getitpouch.R;
import kr.edcan.getitpouch.models.Costemic;
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


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        rootView = inflater.inflate(LAYOUT_RESOURCE, null);

        cancel = (Button) rootView.findViewById(R.id.negative);
        ok = (Button) rootView.findViewById(R.id.positive);


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
                NetworkHelper.getNetworkInstance().scanBarcode(barcode)
                        .enqueue(new Callback<Costemic>() {
                            @Override
                            public void onResponse(Call<Costemic> call, Response<Costemic> response) {
                                if(response.isSuccessful() && response.body() != null) {

                                } else {
                                    onFailure(call, new Throwable(""));
                                }
                            }

                            @Override
                            public void onFailure(Call<Costemic> call, Throwable t) {
                                Toast.makeText(getActivity(), getContext().getString(R.string.network_fail),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }
}
