package mappcomercial.roberto.com.mcom.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Compra implements Parcelable {

    private int idcompra;
    private String nota;
    private String data;
    private String fornecedor;

    public Compra() {
    }

    protected Compra(Parcel in) {
        idcompra = in.readInt();
        nota = in.readString();
        data = in.readString();
        fornecedor = in.readString();
    }

    public static final Creator<Compra> CREATOR = new Creator<Compra>() {
        @Override
        public Compra createFromParcel(Parcel in) {
            return new Compra(in);
        }

        @Override
        public Compra[] newArray(int size) {
            return new Compra[size];
        }
    };

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(data);
        return sb.toString();
        //return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return Objects.equals(idcompra, compra.idcompra);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idcompra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idcompra);
        dest.writeString(nota);
        dest.writeString(data);
        dest.writeString(fornecedor);
    }
}
