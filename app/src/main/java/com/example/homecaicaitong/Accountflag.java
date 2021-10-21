package com.example.homecaicaitong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homecaicaitong.dao.FlagDAO;
import com.example.homecaicaitong.model.Tb_flag;

public class Accountflag extends AppCompatActivity {
    private EditText txtFlag;
    private Button btnflagSaveButton;// ????Button???????
    private Button btnflagCancelButton;// ????Button???????

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountflag);

        txtFlag = (EditText) findViewById(R.id.txtFlag);// ???????????
        btnflagSaveButton = (Button) findViewById(R.id.btnflagSave);// ??????水?
        btnflagCancelButton = (Button) findViewById(R.id.btnflagCancel);// ?????????
        btnflagSaveButton.setOnClickListener(new View.OnClickListener() {// ????水????ü??????
            @Override
            public void onClick(View arg0) {
                String strFlag = txtFlag.getText().toString();// ?????????????
                if (!strFlag.isEmpty()) {// ?ж???????????
                    FlagDAO flagDAO = new FlagDAO(Accountflag.this);// ????FlagDAO????
                    Tb_flag tb_flag = new Tb_flag(flagDAO.getMaxId() + 1, strFlag);// ????Tb_flag????
                    flagDAO.add(tb_flag);// ????????
                    // ??????????
                    Toast.makeText(Accountflag.this, "??????????????????????", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Accountflag.this, "??????????", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnflagCancelButton.setOnClickListener(new View.OnClickListener() {// ??????????ü??????
            @Override
            public void onClick(View arg0) {
                txtFlag.setText("");// ??????????
            }
        });
    }
}