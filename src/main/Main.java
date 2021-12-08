package main;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] rags) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer, Long> listDateOreImpostati = new HashMap<Integer, Long>();
		int start;
		int countTimer = 0;
		
		do {
			System.out.println("===Imposta la data e ora di invio email===");
			System.out.println("===DATA===");
			System.out.println("Giorno:");
			int giorno = sc.nextInt();
			System.out.println("Mese:");
			int mese = sc.nextInt();
			System.out.println("Anno:");
			int anno = sc.nextInt();
			
			System.out.println("===Ora===:");
			System.out.println("Ore:");
			int ore = sc.nextInt();
			System.out.println("Minuti:");
			int minuti = sc.nextInt();
			System.out.println("Secondi:");
			int secondi = sc.nextInt();
			
			Calendar dataOraImpostata = Calendar.getInstance();
			dataOraImpostata.set(Calendar.DATE, giorno);
			dataOraImpostata.set(Calendar.MONTH, mese - 1);
			dataOraImpostata.set(Calendar.YEAR, anno);
			
			dataOraImpostata.set(Calendar.HOUR_OF_DAY, ore);
			dataOraImpostata.set(Calendar.MINUTE, minuti);
			dataOraImpostata.set(Calendar.SECOND, secondi);
			
			countTimer++;
			listDateOreImpostati.put(countTimer, dataOraImpostata.getTimeInMillis());
			System.out.println("===Premi [0] per inviare Timer===");
			System.out.println("===Premi [1] per aggingere un altro timer===");
			
			start = sc.nextInt();
			
		} while(start != 0);
		sc.close();
		
		Set<Integer> keys = listDateOreImpostati.keySet();
		
		for(int key : keys) {
			long tempoAttuale = Calendar.getInstance().getTimeInMillis();
			long tempoImpostato = listDateOreImpostati.get(key);
			
			if(tempoImpostato < tempoAttuale) {
				System.err.println("Timer numero: " + key + " scaduto");
			} else {
				System.out.println("Timer nnumero: " + key);
				do {
					tempoAttuale = Calendar.getInstance().getTimeInMillis();
					stampa(tempoImpostato, tempoAttuale);
				} while(!(tempoImpostato <= tempoAttuale));
				
				Calendar inviato = Calendar.getInstance();
				inviato.setTimeInMillis(tempoAttuale);
				System.out.println("===Email inviata: " + inviato.getTime());
			}
		}
	}
	
	private static void stampa(long v1, long v2) {
		int sec = (int) ((v1 / 1000) - (v2 / 1000));
		try {
			TimeUnit.SECONDS.sleep(1);
			if((sec > 0)) {
				System.out.println("Invio tra: " + sec);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}