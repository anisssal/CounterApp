package com.example.z.counter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by z on 23/11/17.
 */

public class LoginInputValidation {

    Context context;
    Activity activity;

    public LoginInputValidation(Context context) {
        this.context = context;
        this.activity = (Activity) context;
    }


    public boolean inputTextFilled(EditText editText, String pesan){
        String val = editText.getText().toString().trim();;
        if (TextUtils.isEmpty(val)){
            editText.setError(pesan);
            hideKeyboardFrom(editText);
            return false;
        }else {
            editText.setError(null);
            return true;
        }
    }

    public boolean inputPassMacthes(EditText pass1 , EditText pass2){
        String val = pass1.getText().toString().trim();
        String val2 = pass2.getText().toString().trim();
        if (!val.contentEquals(val2)){
            hideKeyboardFrom(pass2);
            pass2.setError("Password tidak sama");
            return false;
        }else {
            pass2.setError(null);
            return true;
        }
    }



    public void hideKeyboardFrom(EditText editText){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    };



    public String setEditTextToString(EditText editText){
        String value = editText.getText().toString().trim();
        return value;
    }
    public void hideKeyboardFrom2(EditText editText){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    };
}
