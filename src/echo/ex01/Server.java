package echo.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException{

		ServerSocket serverSocket = new ServerSocket ();
		serverSocket.bind(new InetSocketAddress("192.168.1.24",10001)); //bind

		
		System.out.println("<서버시작>");
		System.out.println("===========================");
		System.out.println("[연결을 기다리고 있습니다.]");
		
		Socket socket = serverSocket.accept();//클라이언트에게 연결이오면 소켓 뱉어내서 소켓으로 연결 ! 
		System.out.println("[클라이언트가 연결 되었습니다]");
		
		//메세지 받기
		InputStream is =socket.getInputStream();
		Reader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		Writer osw = new OutputStreamWriter(os,"UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		String msg;
		while(true) {
			msg=br.readLine();
			if(msg==null) {
				System.out.println("클라이언트 접속 종료");
				break;
			}
			System.out.println("받은 메세지: "+msg);
			
//			Scanner sc = new Scanner(System.in);
//			String str =sc.nextLine();
			
			bw.write(msg);
			bw.newLine();
			bw.flush();//버퍼가 메세지가 적으면 안보내는데 그래서 적어도 그냥 보내~ 라고 강제 보내기 
		}
		
		
		System.out.println("===========================");
		System.out.println("<서버종료>");
		
		serverSocket.close();
	}

}
