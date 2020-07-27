package tubes.ikb.mytubesbaru.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteModel implements Parcelable {
    int id;
    String title, note;
    String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected NoteModel(Parcel in){
        id = in.readInt();
        title=in.readString();
        date = in.readString();
        note=in.readString();
    }

    public NoteModel(){

    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(note);
    }

    public static final Creator<NoteModel> CREATOR = new Creator<NoteModel>() {
        @Override
        public NoteModel createFromParcel(Parcel parcel) {
            return new NoteModel(parcel);
        }

        @Override
        public NoteModel[] newArray(int i) {
            return new NoteModel[i];
        }
    };
}
