package principal;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JOptionPane;

import com.opencsv.CSVReader;

public class ArquivoCSV {
	
	private LinkedHashMap<String, ArrayList> dados;

	
	public void lerArquivoCSV(File arquivoCSV) {
		try {
			int inicioLinha = localizaInicioDadosCSV(arquivoCSV);
			CSVReader reader = new CSVReader(new FileReader(arquivoCSV), ',', '\"', inicioLinha);
			dados = new LinkedHashMap<String, ArrayList>();
			int countAmostra = 0;
			String ultimaAmostra = null;
			String [] nextData;
			nextData = reader.readNext();
			
			while ((nextData = reader.readNext()) != null) {
				ArrayList<Double> dadosTemp = new ArrayList<>();
				dadosTemp.add(Double.parseDouble(nextData[4]));
				dadosTemp.add(Double.parseDouble(nextData[5]));
				dados.put(nextData[0], dadosTemp);
			    countAmostra++;
			    ultimaAmostra = nextData[0];
			}
			  if(countAmostra < 144){
				  JOptionPane.showMessageDialog(null, "Falta registro do arquivo: "+arquivoCSV.getName()+"\nÚltima aquisição em: "+ultimaAmostra);
			  }
			  reader.close();
		} catch (NullPointerException | IOException e) {
			// TODO: handle exception
		}
		
	}
	
	
	private int localizaInicioDadosCSV(File arquivoCSV) {
		int count = 0;
		int numLinha = 0;
		try {
			CSVReader reader = new CSVReader(new FileReader(arquivoCSV), ',');
			String[] nextData;
			while ((nextData = reader.readNext()) != null) {
				count++;
				if (nextData[0].trim().replace("\"", "").equals("Time")) {
					numLinha = count - 1;
					reader.close();
				}
			}

		} catch (NullPointerException | IOException e) {
			
		}
		return numLinha;
	}
	
	public LinkedHashMap<String, ArrayList> uneMaps(LinkedHashMap<String, ArrayList> map1, 
			LinkedHashMap<String, ArrayList> map2){
		
//		for(tempo : map1.)
		
		return map2;
		
		
	}


	public final LinkedHashMap<String, ArrayList> getDados() {
		return dados;
	}
	
	

}
