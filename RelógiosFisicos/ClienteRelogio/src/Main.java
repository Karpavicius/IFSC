import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		try {
			Socket sckt = new Socket("localhost", 6666);
						
			//fomato de data simples para conversão
			SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
			//formatando a data
			String data = formato.format(new Date());
			Date sdf;
			//formatando a string data em date
			sdf = formato.parse(data);
			System.out.println(sdf);
			//adicionando um atraso aleatório em até 1 minuto
			sdf.setSeconds(sdf.getSeconds()-new Random().nextInt(60));
			
			//salva a hora em string no formato HH:mm:ss
			String hora = sdf.getHours()+":"+sdf.getMinutes()+":"+sdf.getSeconds();
			
			//envia a hora para o server, t0
			PrintWriter pw = new PrintWriter(sckt.getOutputStream());
			pw.println(hora);
			pw.flush();
			Thread.currentThread().sleep(6000); //dorme 1 segundo a mais que o server pra evitar problema
			//salva em um buffer
			BufferedReader br = new BufferedReader(new InputStreamReader(sckt.getInputStream()));
			
			//formata o t3
			String data3 = formato.format(new Date());
			//separa as horas recebidas do server
			String horaRecebida[] = br.readLine().split("/");
			System.out.println("t0 " + horaRecebida[2]);//t0 hora enviada
			System.out.println("t1 " + horaRecebida[0]);//t1 hora que o server recebeu
			System.out.println("t2 " + horaRecebida[1]);//t2 hora que o server enviou
			
			System.out.println(data3);
			sckt.close();
			//podem ser substituidos para horas manuais (hard code)
			//formatando as strings em Date
			Date t0 = formato.parse(horaRecebida[2]);
			Date t1 = formato.parse(horaRecebida[0]);
			Date t2 = formato.parse(horaRecebida[1]);
			Date t3 = formato.parse(data3);
			
			//fazendo o cálculo
			long dif = (((t1.getTime()-t0.getTime())+(t2.getTime()-t3.getTime()))/2);
			
			Date tempDif = new Date(dif);
			System.out.println("diferença de tempo: " + 0+":"+tempDif.getMinutes()+":"+tempDif.getSeconds());
			
				
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
