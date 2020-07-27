package tubes.ikb.mytubesbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tubes.ikb.mytubesbaru.helper.DataHelper;

public class InputDataActivity extends AppCompatActivity {

    EditText etTitle, etNote;
    Button btnSave;
    final DataHelper SQLite = new DataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        etTitle = findViewById(R.id.input_judul);
        etNote = findViewById(R.id.input_isi);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String note = etNote.getText().toString();

                if (TextUtils.isEmpty(title)){
                    etTitle.setError("This field cannot be empty");
                } else if (TextUtils.isEmpty(note)){
                    etNote.setError("This field cannot be empty");
                }else {
                    SQLite.insert(etTitle.getText().toString(), etNote.getText().toString());
                    startActivity(new Intent(InputDataActivity.this, MainActivity.class));
                }

            }
        });
    }
}
