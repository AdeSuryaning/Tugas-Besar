package tubes.ikb.mytubesbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tubes.ikb.mytubesbaru.helper.DataHelper;
import tubes.ikb.mytubesbaru.model.NoteModel;

public class UpdateDataActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "extra_data";

    EditText updTitle, updNote;
    Button btnEdit;
    final DataHelper SQLite = new DataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        updTitle = findViewById(R.id.update_judul);
        updNote = findViewById(R.id.update_isi);
        btnEdit = findViewById(R.id.btn_update);

        final NoteModel updateModel = getIntent().getParcelableExtra(EXTRA_DATA);
        updTitle.setText(updateModel.getTitle());
        updNote.setText(updateModel.getNote());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = updTitle.getText().toString();
                String note = updNote.getText().toString();

                if (TextUtils.isEmpty(title)){
                    updTitle.setError("This field cannot be empty");
                } else if (TextUtils.isEmpty(note)){
                    updNote.setError("This field cannot be empty");
                }else {
                    SQLite.update(updateModel.getId(),updTitle.getText().toString(),updNote.getText().toString());
                    startActivity(new Intent(UpdateDataActivity.this, MainActivity.class));
                }

            }
        });
    }
}
