package com.gabriel.unovago.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gabriel.unovago.database.Database;
import com.gabriel.unovago.model.Cliente;

public class ClienteDao {
    private Database db;

    public ClienteDao(Database db) {
        this.db = db;
    }

    public void login(Cliente c){
        Cliente cliente = verificaLogin();
        if(cliente.getCreatedAt() != null){
            logout(cliente.getId());
            login(c);
        }else{
            SQLiteDatabase conection = db.getWritableDatabase();

            ContentValues dados = new ContentValues();

            dados.put("createdAt", c.getCreatedAt());
            dados.put("updatedAt", c.getUpdatedAt());
            dados.put("id", c.getId());
            dados.put("nome", c.getNome());
            dados.put("email", c.getEmail());

            conection.insert("cliente_autenticado", null, dados );
            conection.close();
        }
    }

    public Cliente verificaLogin(){
        String sql = "SELECT * FROM cliente_autenticado LIMIT 1;";
        SQLiteDatabase connection = db.getReadableDatabase();
        Cursor c = connection.rawQuery(sql, null);

        Cliente cliente = new Cliente();
        while (c.moveToNext()) {
            cliente.setCreatedAt(c.getLong(c.getColumnIndex("createdAt")));
            cliente.setUpdatedAt(c.getLong(c.getColumnIndex("updatedAt")));
            cliente.setId(c.getInt(c.getColumnIndex("id")));
            cliente.setNome(c.getString(c.getColumnIndex("nome")));
            cliente.setEmail(c.getString(c.getColumnIndex("email")));
        }
        c.close();
        connection.close();
        return cliente;
    }

    public void logout(int id){
        SQLiteDatabase connection = db.getWritableDatabase();

        String [] params = {String.valueOf(id)};
        connection.delete("cliente_autenticado", "id = ?", params);
        connection.close();
    }
}
