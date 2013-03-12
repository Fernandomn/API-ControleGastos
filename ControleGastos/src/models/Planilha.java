package models;

import DataBase.Database;
import android.database.Cursor;



public class Planilha {
	private static int codigo;
	private static int tipo;
	private static String nome;
	private static float meta;

	
	public  Planilha(int tipo,String nome, float meta){
		this.nome = nome;
		this.meta = meta;
		this.tipo = tipo;
		
	}
	public Planilha(Cursor c){
		c.moveToFirst();
		codigo = c.getInt(c.getColumnIndex("pla_codigo"));
		nome = c.getString(c.getColumnIndex("pla_codigo"));
		tipo = c.getInt(c.getColumnIndex("pla_codigo_tpl"));
		meta = c.getFloat(c.getColumnIndex("pla_meta"));
	}
	public static int getCodigo() {
		return codigo;
	}
	public static int getTipo() {
		return tipo;
	}
	public void setMeta(float meta) {
		this.meta = meta;
	}

	public static float getMeta() {
		return meta;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static String getNome() {
		return nome;
	}

	public static void insert(){
		if(findByNome(getNome())== null){
			String query = "insert into Planilha(pla_nome,pla_codigo_tpl,pla_meta) values ('"+getNome()+"',"+getTipo()+","+getMeta()+")";
			Database.run(query);
		}
	}
	public static void delete(int codigo){
		
		String query = "delete from Planilha where pla_codigo = "+codigo+"";
		Database.run(query);
	
	}
	public static Planilha findByCodigo(int codigo){
		Planilha toReturn;

		try{
			toReturn = new Planilha(Database.get("select * from Planilha where pla_codigo = "+ getCodigo()));
		}
		catch(Exception e){
			toReturn = null;
		}
			
		return toReturn;
	}
	
	public static Planilha findByNome(String nome){
		Planilha toReturn;
		
		try{
			toReturn = new Planilha(Database.get("select * from Planilha where pla_nome = '"+ nome +"'"));
		}
		catch(Exception e){
			toReturn = null;
		}
			
		return toReturn;
	}
	public static void create(){
		String query = "create table if not exists Planilha (pla_codigo int identify(1,1) primary key, pla_nome varchar, pla_codigo_tpl int, pla_meta float)";
		Database.run(query);
	}
}
