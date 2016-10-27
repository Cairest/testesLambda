package testes;


import java.io.File;

import org.junit.Before;
import org.junit.Test;

import principal.ArquivoCSV;



public class TesteArquivoCSV {
	
	ArquivoCSV arquivoCSV;
	
	@Before
	public void inicializa(){
		arquivoCSV = new ArquivoCSV();
	}

	@Test
	public void imprimeTensaoFundamental() {
		arquivoCSV.lerArquivoCSV(new File("C:\\Users\\1539917\\Documents\\QEE\\L1-N(V) Harmonics.csv"));
		System.out.println(arquivoCSV.getDados());
	}
	
	@Test
	public void imprimeCorrenteFundamental() {
		arquivoCSV.lerArquivoCSV(new File("C:\\Users\\1539917\\Documents\\QEE\\L1(A) Harmonics.csv"));
		System.out.println(arquivoCSV.getDados());
	}

}
