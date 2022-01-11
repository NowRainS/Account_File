package src.ch01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStream {
	
	private final String path = "C:\\Lee\\workspace\\account.txt";
	private final String path2 = "C:\\Lee\\workspace\\account2.txt";
	
	public void writeText(String id, String password, int account, String name) {
		try {
			File file = new File(path);
			//변경할 라인의 문자열
			String line = "";
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			if(file.isFile() && file.canWrite()) {
				line = id+"/"+password+"#"+name+"*"+account;
				bw.write(line);
				bw.newLine();
				bw.close();
			}
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public void updateText(String id, int account, int mode) {
		String total = "";
		String line = "";
		File preFile = new File(path);
		File afterFile = new File(path2);
		try {
			BufferedReader br = new BufferedReader(new FileReader(preFile));
			BufferedWriter bw = new BufferedWriter(new FileWriter(afterFile));
			//한줄 씩 받아오고
			while((line = br.readLine()) != null) {
				//아이디 추출
				String str = splitString(line,'/');
				
				if(str.equals(id)) {
					//account 값을 가져온다
					int preAccount = Integer.parseInt(splitString(line,'n'));
					
					//잔고전까지 추출하고 삭제
					int index = line.indexOf('*');
					line = line.substring(0,index+1);
					//mode=1일때 입금
					if(mode ==1) {
						preAccount += account;
						line = line + preAccount;
					}
					//mode=2일때 출금
					else if(mode ==2) {
						preAccount = preAccount-account;

						System.out.println("출금되었습니다. 잔고는 "+preAccount+"입니다");
						line = line + preAccount;
					}
					
				}
				total += (line+"\r\n");
				bw.flush();
			}
			
			if(preFile.isFile() && preFile.canWrite()) {
				bw.write(total);
				br.close();
				bw.close();
			}
		}catch(Exception e) {
			
		}
		
		if(preFile.exists()) {
		}
		//삭제하려는 파일이나 디텍토리가 존재하지않으면 false를반환
		if(!preFile.delete()) {
			System.out.println("삭제실패");
		}
		
		afterFile.renameTo(preFile);
		
		
	}
	
	
	public int readText(String id, String password){
		int state =0;
		try {
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line ="";
			while((line = br.readLine())!=null){
				String saveId = splitString(line, '/');
				String savePassword = splitString(line,'#');
				
				if(!(id.equals(saveId))) {
					state=1;
				}else if((!(password.equals(savePassword))&& id.equals(saveId))){
						state=2;
				}
			}
			br.close();
		}
		catch(FileNotFoundException e){
			
		}catch(IOException e) {
			System.out.println(e);
		}
		return state;
	}
	
	public String readAccount(String id, int mode) {
		//mode = 1 이면 잔고를, 2이면 이름을 반환
		String str = "";
		try {
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line ="";
			while((line = br.readLine())!=null){
				if(mode ==1) {
					str = splitString(line, 'n');
				}else if(mode == 2) {
					str = splitString(line,'*');
				}
				 
			}
			br.close();
		}catch(FileNotFoundException e){
			
		}catch(IOException e) {
			System.out.println(e);
		}
		
		return str;
		
	}
	
	//구분기호로 문자열을 자르고 반환한다.
	public String splitString(String str, char x) {
		int index = str.indexOf(x);
		String result ="";
		if(x == '/') {
			//아이디를 잘라오는것
			result = str.substring(0, index);
		}else if(x == '#') {
			//비밀번호를 잘라오는것
			int index2 = str.indexOf('/');
			result = str.substring(index2+1, index);
		}else if(x == '*')
		{	//이름ㅇ추출
			int index2 = str.indexOf('#');
			result = str.substring(index2+1, index);
		}else if(x == 'n') {
			//잔고 추출
			index = str.indexOf('*');
			result = str.substring(index+1, str.length());
		}
		
		return result;
	}
}
