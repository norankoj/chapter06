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
import java.net.Socket;
import java.util.Scanner;

public class Client { //다른 pc에서 실행되는 여러개의 pc에 배포되어 실행되는!

	public static void main(String[] args) throws IOException {
		
		
		Socket socket = new Socket();
		System.out.println("<클라이언트 시작>");
		System.out.println("=======================");
		
		System.out.println("[서버에 연결을 요청합니다.]");
		socket.connect(new InetSocketAddress("192.168.1.24", 10001));
		System.out.println("[서버에 연결되었습니다.]");
		//메세지 보내기용 스트림 
		OutputStream os =socket.getOutputStream();
		Writer osw = new OutputStreamWriter(os,"UTF-8");
		BufferedWriter bw = new BufferedWriter(osw); //붙임 
		
		
		
		
		
		//메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		Reader isr = new InputStreamReader(is,"UTF-8"); //UTF-8 은 이진법을 한글로 바꿔줘요 
		BufferedReader br = new BufferedReader(isr);
		
		//키보드 입력 
		Scanner sc = new Scanner(System.in);
		
		while(true) {
		String str =sc.nextLine();
		
		if("/q".equals(str)) {
			System.out.println("[접속종료되었습니다.]");
			break;
		}
		bw.write(str);
		bw.newLine();
		bw.flush();
		String reMsg=br.readLine();
		System.out.println("server:["+reMsg+"]");
		
		}
//		
//		//메세지 보내기 
//		String str = "테스트 입니다.";
		
		
	

		br.close();
		bw.close();
		
		
		bw.close();
		System.out.println("========================");
		System.out.println("<클라이언트 종료>");
		socket.close();

	}

}
