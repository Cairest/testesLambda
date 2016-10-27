package principal;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;

public class FuncaoDias {
	
	private DateTime dataInicial;
	private DateTime dataFinal;
	private List<DateTime> periodo;
	static private File arquivoFlukeCSV;
	private String pastaRaiz;
	static private LinkedList<String> listaExceptions = new LinkedList<>();
	
	public final LinkedList<String> getListaExceptions() {
		return listaExceptions;
	}

	public FuncaoDias(String pastaRaiz) {
		super();
		this.pastaRaiz = pastaRaiz;
	}

	public List<DateTime> selecionaPeriodo(DateTime inicio, DateTime fim) {
		this.periodo = new ArrayList<>();
		while (inicio.isBefore(fim) || inicio.isEqual(fim)) {
			periodo.add(inicio);
			inicio = inicio.plusDays(1);
		}
		return periodo;
	}
	
	public File arquivoTensaoFlukeCSV(DateTime data, int i) {
		String mes = retornaComZero(data.getMonthOfYear());
		String dia = retornaComZero(data.getDayOfMonth());
	//	return new File("C:/Teste JAVA/QEE/Month "+mes+"/Day "+dia+"/L"+i+"-N(V) Harmonics.csv").getAbsoluteFile();
		return buscaTensaoFlukeCSV(mes, dia, i);
	}
	
	public File arquivoCorrenteFlukeCSV(DateTime data, int i) {
		String mes = retornaComZero(data.getMonthOfYear());
		String dia = retornaComZero(data.getDayOfMonth());
	//	return new File("C:/Teste JAVA/QEE/Month "+mes+"/Day "+dia+"/L"+i+"-N(V) Harmonics.csv").getAbsoluteFile();
		return buscaCorrenteFlukeCSV(mes, dia, i);
	}

	public final List<DateTime> getPeriodo() {
		return periodo;
	}
	
	private String retornaComZero(int i){
		if(i <= 9)
			return "0"+i;
		else
			return String.valueOf(i);
	}
	
	public File buscaTensaoFlukeCSV(String mes, String dia, int i) {
		
		File dir = new File(this.pastaRaiz+"\\Month "+mes+"\\Day "+dia);
		File arquivoFlukeCSV = null;
		String[] extensions = new String[] { "csv" };
		try {
			List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
			for(File file : files){
				if(file.getName().equals("L"+i+"-N(V) Harmonics.csv")){
					arquivoFlukeCSV = file;
				}
			}
		} catch (IllegalArgumentException e) {
		//	JOptionPane.showMessageDialog(null, "Pasta Inexistente: "+this.pastaRaiz+"\\Month "+mes+"\\Day "+dia);
			listaExceptions.add("Pasta Inexistente: "+this.pastaRaiz+"\\Month "+mes+"\\Day "+dia+" - "+e);
		}
		return arquivoFlukeCSV;
	}

	
	public File buscaCorrenteFlukeCSV(String mes, String dia, int i) {
		
		File dir = new File(this.pastaRaiz+"\\Month "+mes+"\\Day "+dia);
		File arquivoFlukeCSV = null;
		String[] extensions = new String[] { "csv" };
		try {
			List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
			for(File file : files){
				if(file.getName().equals("L"+i+"(A) Harmonics.csv")){
					arquivoFlukeCSV = file;
				}
			}
		} catch (IllegalArgumentException e) {
		//	JOptionPane.showMessageDialog(null, "Pasta Inexistente: "+this.pastaRaiz+"\\Month "+mes+"\\Day "+dia);
			listaExceptions.add("Pasta Inexistente: "+this.pastaRaiz+"\\Month "+mes+"\\Day "+dia+" - "+e);
		}
		return arquivoFlukeCSV;
	}
	
	
	
	

}
