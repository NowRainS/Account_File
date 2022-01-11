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
			//������ ������ ���ڿ�
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
			//���� �� �޾ƿ���
			while((line = br.readLine()) != null) {
				//���̵� ����
				String str = splitString(line,'/');
				
				if(str.equals(id)) {
					//account ���� �����´�
					int preAccount = Integer.parseInt(splitString(line,'n'));
					
					//�ܰ������� �����ϰ� ����
					int index = line.indexOf('*');
					line = line.substring(0,index+1);
					//mode=1�϶� �Ա�
					if(mode ==1) {
						preAccount += account;
						line = line + preAccount;
					}
					//mode=2�϶� ���
					else if(mode ==2) {
						preAccount = preAccount-account;

						System.out.println("��ݵǾ����ϴ�. �ܰ�� "+preAccount+"�Դϴ�");
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
		//�����Ϸ��� �����̳� �����丮�� �������������� false����ȯ
		if(!preFile.delete()) {
			System.out.println("��������");
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
		//mode = 1 �̸� �ܰ�, 2�̸� �̸��� ��ȯ
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
	
	//���б�ȣ�� ���ڿ��� �ڸ��� ��ȯ�Ѵ�.
	public String splitString(String str, char x) {
		int index = str.indexOf(x);
		String result ="";
		if(x == '/') {
			//���̵� �߶���°�
			result = str.substring(0, index);
		}else if(x == '#') {
			//��й�ȣ�� �߶���°�
			int index2 = str.indexOf('/');
			result = str.substring(index2+1, index);
		}else if(x == '*')
		{	//�̸�������
			int index2 = str.indexOf('#');
			result = str.substring(index2+1, index);
		}else if(x == 'n') {
			//�ܰ� ����
			index = str.indexOf('*');
			result = str.substring(index+1, str.length());
		}
		
		return result;
	}
}
