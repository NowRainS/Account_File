package src.ch01;

import java.util.ArrayList;
import java.util.Scanner;

public class Register extends FileStream {
	ArrayList<User> userList = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	
	public void addUser() {
		User user = new User();
		System.out.print("�̸��� �Է��ϼ���> ");
		user.setName(sc.nextLine());
		System.out.print("���̵� �Է��ϼ���> ");
		String inputId = sc.nextLine();
		
		if(searchUserId(inputId)) {	
			user.setId(inputId);
			System.out.print("��й�ȣ�� �Է��ϼ���> ");
			user.setPassword(sc.nextLine());
			userList.add(user);
			System.out.println(user.getName()+"�� ȸ�������� �Ϸ� �Ǿ����ϴ�.");
			writeText(user.getId(),user.getPassword(), user.getAccount(),user.getName());
		}
		
	}
	//���̵� �ߺ�üũ
	public boolean searchUserId(String userId) {
		boolean exist = true;
		//�� userId�� ����� ���� �ʴ°�?
		for(User user : userList) {
			if(user.getId().equals(userId)) {
				System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
				exist = false;
			}
		}
		return exist;
	}
}
