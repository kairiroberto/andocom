package mappcomercial.roberto.com.mcom.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Cliente implements Parcelable {

    private int id;
    private String nome;//1
    private String cpf;//2
    private String rg;//3
    private String dataNascimento;//4
    private String telefone;//5
    private String email;//6
    private String logradouro;//7
    private String numero;//8
    private String bairro;//9
    private String cidade;//10

    public Cliente(int id, String nome, String cpf, String rg, String dataNascimento, String telefone, String email, String logradouro, String numero, String bairro, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    protected Cliente(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        cpf = in.readString();
        rg = in.readString();
        dataNascimento = in.readString();
        telefone = in.readString();
        email = in.readString();
        logradouro = in.readString();
        numero = in.readString();
        bairro = in.readString();
        cidade = in.readString();
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public Cliente() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(nome);
        buffer.append(" - Telefone: " + telefone);
        return buffer.toString();
        //return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);//1
        dest.writeString(cpf);//2
        dest.writeString(rg);//3
        dest.writeString(dataNascimento);//4
        dest.writeString(telefone);//5
        dest.writeString(email);//6
        dest.writeString(logradouro);//7
        dest.writeString(numero);//8
        dest.writeString(bairro);//9
        dest.writeString(cidade);//10
    }
}
