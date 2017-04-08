package principal.android.utp.proyecto;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Editar_Nota extends DialogFragment {
    Button btn_actualizar;
    EditText nota_update;
    TextView lblbimestre;
    onSubmitListener mListener;
    String text = "";

    public void show(FragmentManager fragmentManager, String s) {
    }

    interface onSubmitListener {
        void setOnSubmitListener(String arg);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.modal_nota);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        btn_actualizar = (Button) dialog.findViewById(R.id.btn_actualizar);
        nota_update = (EditText) dialog.findViewById(R.id.nota_update);
        nota_update.setText(text);
        btn_actualizar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.setOnSubmitListener(nota_update.getText().toString());
                dismiss();
            }
        });
        return dialog;
    }
}