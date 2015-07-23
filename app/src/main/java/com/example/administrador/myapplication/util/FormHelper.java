package com.example.administrador.myapplication.util;

import android.content.Context;
import android.widget.EditText;

import com.example.administrador.myapplication.R;

public final class FormHelper {

    private FormHelper() {
        super();
    }


    public static boolean riquiredValidate(Context context, EditText... editTexts) {
        boolean validado = Boolean.TRUE;
        for (EditText editText : editTexts) {
            String value = editText.getText() == null ? null : editText.getText().toString();
            if (value == null || value.trim().isEmpty()) {
                String erroMensagem = context.getString(R.string.campo_obrigatorio);
                editText.setError(erroMensagem);
                validado = Boolean.FALSE;
            }
        }
        return validado;
    }
}
