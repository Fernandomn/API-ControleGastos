package models;


import DataBase.Database;
import android.database.Cursor;

public class Historico {
	private static String data;
	private static String operacao;
	private static float valor;
	private static int codigoPlanilha;
	
	public  Historico(int codigoPlanilha,String operacao, float valor, String data){
		this.operacao = operacao;
		this.valor = valor;
		this.codigoPlanilha = codigoPlanilha;
		this.data = data;
	}
	public Historico(Cursor c){
		c.moveToFirst();
		data = c.getString(c.getColumnIndex("his_data"));
		operacao = c.getString(c.getColumnIndex("his_operacao"));
		codigoPlanilha = c.getInt(c.getColumnIndex("his_codigo_pla"));
		valor = c.getFloat(c.getColumnIndex("his_valor"));
	}
	public static String getData() {
		return data;
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

	public void setNome(String operacao) {
		this.operacao = operacao;
	}

	public static String getOperacao() {
		return operacao;
	}

	public static void insert(){
		if(findByNome(getOperacao())== null){
			String query = "insert into Historico(his_operacao,his_codigo_pla,his_valor,his_data) values ('"+getOperacao()+"',"+getCodigoPlanilha()+","+getValor()+","+getData()+")";
			Database.run(query);
		}
	}
	public static Historico findByCodigo(String data){
		Historico toReturn;

		try{
			toReturn = new Historico(Database.get("select * from Historico where his_data = '"+ data+"' and his_codigo_pla = "+ getCodigoPlanilha()));
		}
		catch(Exception e){
			toReturn = null;
		}
			
		return toReturn;
	}
	
	public static Historico findByNome(String operacao){
		Historico toReturn;
		
		try{
			toReturn = new Historico(Database.get("select * from Historico where his_operacao = '"+ operacao +"' and his_codigo_pla = "+ getCodigoPlanilha()));
		}
		catch(Exception e){
			toReturn = null;
		}
			
		return toReturn;
	}
	public static void create(){
		String query = "create table if not exists Historico (his_codigo_pla int , his_operacao varchar, his_data varchar, his_valor float)";
		Database.run(query);
	}

}
