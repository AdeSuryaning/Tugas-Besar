package tubes.ikb.mytubesbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import tubes.ikb.mytubesbaru.helper.DataHelper;
import tubes.ikb.mytubesbaru.model.NoteModel;

public class DetailDataActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "extra_data";
    TextView deTitle, deDate, deNote;
    final DataHelper SQLite = new DataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        final NoteModel updateModel = getIntent().getParcelableExtra(EXTRA_DATA);

        deTitle = findViewById(R.id.detail_judul);
        deDate = findViewById(R.id.detail_tanggal);
        deNote = findViewById(R.id.detail_note);

        deTitle.setText(updateModel.getTitle());
        deDate.setText(updateModel.getDate());
        deNote.setText(updateModel.getNote());

    }
}
