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
        barcode = getArguments().getString(Constant.ADD_COSMETIC_DIALOG_BARCODE);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.negative:
                dismiss();
                break;
            case R.id.positive:

                break;
        }
    }

}
