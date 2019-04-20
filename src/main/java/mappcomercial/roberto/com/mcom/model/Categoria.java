package mappcomercial.roberto.com.mcom.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Categoria implements Parcelable {

    private int idcategoria;
    private String nome;

    public Categoria() {

    }

    protected Categoria(Parcel in) {
        idcategoria = in.readInt();
        nome = in.readString();
    }

    public static final Creator<Categoria> CREATOR = new Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(nome);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return idcategoria == categoria.idcategoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcategoria);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idcategoria);
        dest.writeString(nome);
    }
}
