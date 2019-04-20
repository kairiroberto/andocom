package mappcomercial.roberto.com.mcom.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Fornecedor implements Parcelable {

    private int idfornecedor;
    private String nome;

    public Fornecedor(Parcel in) {
        idfornecedor = in.readInt();
        nome = in.readString();
    }

    public static final Creator<Fornecedor> CREATOR = new Creator<Fornecedor>() {
        @Override
        public Fornecedor createFromParcel(Parcel in) {
            return new Fornecedor(in);
        }

        @Override
        public Fornecedor[] newArray(int size) {
            return new Fornecedor[size];
        }
    };

    public Fornecedor() {
    }

    public int getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(int idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(nome);
        return buffer.toString();
        //return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return idfornecedor == that.idfornecedor;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idfornecedor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idfornecedor);
        dest.writeString(nome);
    }
}
