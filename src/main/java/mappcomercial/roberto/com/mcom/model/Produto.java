package mappcomercial.roberto.com.mcom.model;

import android.app.Application;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import mappcomercial.roberto.com.mcom.MainActivity;
import mappcomercial.roberto.com.mcom.db.CategoriaDB;

import java.util.Objects;

public class Produto implements Parcelable {

    private int idproduto;
    private String nome;
    private int categoria;
    private String unidade;
    private Double valorVenda;
    private Double estoque;

    public Produto () {
    }

    protected Produto(Parcel in) {
        idproduto = in.readInt();
        nome = in.readString();
        categoria = in.readInt();
        unidade = in.readString();
        valorVenda = in.readDouble();
        estoque = in.readDouble();
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getEstoque() {
        return estoque;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        try {
            Context context = MainActivity.context;
            CategoriaDB db = new CategoriaDB(context);
            sb.append(nome + "\t - Cat: " + db.getOne(categoria) + "\nV. Venda: R$ " + valorVenda + "\t - Estoque: " + estoque);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return super.toString();
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return idproduto == produto.idproduto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduto);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idproduto);
        dest.writeString(nome);
        dest.writeInt(categoria);
        dest.writeString(unidade);
        dest.writeDouble(valorVenda);
        dest.writeDouble(estoque);
    }

}
