    package be.lsinf1225.minipoll.activity;

    import android.app.Activity;
    import android.content.Intent;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.graphics.Typeface;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

    import be.lsinf1225.minipoll.MiniPoll;
    import be.lsinf1225.minipoll.MySQLiteHelper;
    import be.lsinf1225.minipoll.R;

    public class Inscription extends AppCompatActivity {

        TextView title;
        TextView mail_2p;
        TextView mdp_2p;
        TextView remdp_2p;
        EditText mail_insc;
        EditText mdp_insc;
        EditText re_mdp_insc;
        Button insc;
        public static Activity inscription;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inscription);
            title = findViewById(R.id.creation);
            setFontTxt(title);
            mail_2p = findViewById(R.id.maildeuxpoints);
            setFontTxt(mail_2p);
            mdp_2p = findViewById(R.id.mdpdeuxpoints);
            setFontTxt(mdp_2p);
            remdp_2p = findViewById(R.id.remdp);
            setFontTxt(remdp_2p);
            mail_insc = findViewById(R.id.mail_insc);
            setFontEdTxt(mail_insc);
            mdp_insc = findViewById(R.id.mdp_insc);
            setFontEdTxt(mdp_insc);
            re_mdp_insc = findViewById(R.id.mdp_re_insc);
            setFontEdTxt(re_mdp_insc);
            insc = findViewById(R.id.sinscrire);
            setFontBut(insc);


            insc.setOnClickListener(insciption);

            inscription = this;
        }


        View.OnClickListener insciption = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail_inscText = mail_insc.getText().toString();
                String mdp_inscText = mdp_insc.getText().toString();
                String re_mdp_inscText = re_mdp_insc.getText().toString();
                if (mail_inscText.equals("")|mdp_inscText.equals("")|re_mdp_inscText.equals(""))
                {
                    MiniPoll.notifyShort(R.string.veuillezremplir);
                }
                else
                {
                    SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
                    Boolean find = false;
                    String SQL = "SELECT Mail FROM Utilisateur";
                    Cursor c = db.rawQuery(SQL, null);
                    c.moveToFirst();
                    while((!c.isAfterLast())&&(!find))
                    {
                        if(c.getString(0).equals(mail_inscText))
                        {
                            find = true;
                        }
                        c.moveToNext();
                    }
                    c.close();
                    if(find)
                    {
                        MiniPoll.notifyShort(R.string.maildejautilise);
                    }
                    else {
                        if (mdp_inscText.equals(re_mdp_inscText))
                        {
                            Intent prof = new Intent(getApplicationContext(),CreationProfil.class);
                            prof.putExtra("Mail",mail_inscText);
                            prof.putExtra("Mdp",mdp_inscText);
                            startActivity(prof);
                        }
                        else
                        {
                            MiniPoll.notifyShort(R.string.motsdepassesdiff);
                        }
                    }
                }
            }
        };



        public void setFontTxt(TextView textView) {
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", textView + " not found", e);
            }
        }

        public void setFontBut(Button button) {
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
                button.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", button + " not found", e);
            }
        }

        public void setFontEdTxt(EditText editText) {
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
                editText.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", editText + " not found", e);
            }
        }
    }
