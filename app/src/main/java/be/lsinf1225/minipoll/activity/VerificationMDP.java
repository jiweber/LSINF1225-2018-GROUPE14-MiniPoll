package be.lsinf1225.minipoll.activity;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import be.lsinf1225.minipoll.R;

/**
 * Created by guill on 06-05-18.
 */

public class VerificationMDP extends Dialog {
    TextView title;
    TextView ennonce;
    EditText et_password;
    Button validate;
    Button cancel;


    public VerificationMDP(Activity activity)
    {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.popup_template);
        title = findViewById(R.id.verif_title);
        ennonce = findViewById(R.id.textView9);
        et_password = findViewById(R.id.editText);
        validate = findViewById(R.id.button10);
        cancel = findViewById(R.id.button11);

    }


    public void build()
    {
        show();
    }

    public EditText getEt_password() {
        return et_password;
    }

    public Button getValidate() {
        return validate;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setEnnonce(String ennonce) {
        this.ennonce.setText(ennonce);
    }

    public Button getCancel() {
        return cancel;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getEnnonce() {
        return ennonce;
    }

    public void setEnnonce(TextView ennonce) {
        this.ennonce = ennonce;
    }

    public void setEt_password(EditText et_password) {
        this.et_password = et_password;
    }

    public void setValidate(Button validate) {
        this.validate = validate;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }
}
