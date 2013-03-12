package models;

import java.util.ArrayList;
import java.util.List;

import DataBase.Database;
import android.app.AlertDialog;
import android.database.Cursor;

public class Operacoes {
	private static int codigo;
	private static String descricao;
	private static float valor;
	private static int codigoPlanilha;
	private static int tipoOperacao;
	
	public  Operacoes(int codigoPlanilha,String descricao, float valor, int tipoOperacao){
		this.descricao = descricao;
		this.valor = valor;
		this.codigoPlanilha = codigoPlanilha;
		this.tipoOperacao = tipoOperacao;
	}
	public Operacoes(Cursor c){
		c.moveToFirst();
		codigo = c.getInt(c.getColumnIndex("ope_codigo"));
		descricao = c.getString(c.getColumnIndex("ope_descricao"));
		codigoPlanilha = c.getInt(c.getColumnIndex("ope_codigo_pla"));
		valor = c.getFloat(c.getColumnIndex("ope_valor"));
		tipoOperacao = c.getInt(c.getColumnIndex("ope_codigo_top"));
	}
	public static int getCodigo() {
		return codigo;
	}
	public static int getTipoOperacao() {
		return tipoOperacao;
	}
	public static int getCodigoPlanilha() {
		return codigoPlanilha;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}

	public static float getValor() {
		return valor;
	}

	public void setNome(String descricao) {
		this.descricao = descricao;
	}

	public static String getDescricao() {
		return descricao;
	}

	public static boolean insert(int codigoPlanilha,String descricao, float valor, int tipoOperacao){
		if (findByNome(descricao)==null){
			String query = "insert into Operacoes(ope_descricao,ope_codigo_pla,ope_valor, ope_codigo_top) values ('"+descricao+"',"+codigoPlanilha+","+valor+","+tipoOperacao+")";
			Database.run(query);
			return true;
		}
		return false;
	}
	public static void delete(int codigo){
		
		String query = "delete from Operacoes where ope_codigo = "+codigo+"";
		Database.run(query);
	
	}
	public static Operacoes findByCodigo(int codigo){
		Operacoes toReturn;

		try{
			toReturn = new Operacoes(Database.get("select * from Operacoes where ope_codigo = "+ getCodigo()));
		}
		catch(Exception e){
			toReturn = null;
		}
			
		return toReturn;
	}
	
	public static Operacoes findByNome(String descricao){
		Operacoes toReturn;
		
		try{
			toReturn = new Operacoes(Database.get("select * from Operacoes where ope_descricao = '"+ descricao +"'"));
		}
		catch(Exception e){
			toReturn = null;
		}
			
		return toReturn;
	}
	public static Operacoes findByTipoOperacao(int tipoOperacao){
		Operacoes toReturn;
		
		try{
			toReturn = new Operacoes(Database.get("select * from Operacoes where ope_codigo_top = "+ tipoOperacao ));
		}
		catch(Exception e){
			toReturn = null;
		}
			
		return toReturn;
	}
	public static List<Operacoes> findByPlanilha(int planilha){
		List<Operacoes> toReturn = new ArrayList<Operacoes>();  
		Cursor listOperacoes =  Database.get("select * from Operacoes where ope_codigo_top = "+ planilha );
		
		try{
			
			while(listOperacoes.moveToNext()){  
				   Operacoes ope = new Operacoes(listOperacoes);  	  
				   toReturn.add(ope); 
				   
				}
			
		}finally{
			toReturn = null;
			listOperacoes.close();
		}
		
		return toReturn;
	}
	public static void create(){
		String query = "create table if not exists Operacoes (ope_codigo int identify(1,1) primary key, ope_descricao varchar, ope_codigo_pla int, ope_valor float, ope_codigo_top int)";
		Database.run(query);
	}
}
