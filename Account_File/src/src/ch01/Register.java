package src.ch01;

import java.util.ArrayList;
import java.util.Scanner;

public class Register extends FileStream {
	ArrayList<User> userList = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	
	public void addUser() {
		User user = new User();
		System.out.print("이름을 입력하세요> ");
		user.setName(sc.nextLine());
		System.out.print("아이디를 입력하세요> ");
		String inputId = sc.nextLine();
		
		if(searchUserId(inputId)) {	
			user.setId(inputId);
			System.out.print("비밀번호를 입력하세요> ");
			user.setPassword(sc.nextLine());
			userList.add(user);
			System.out.println(user.getName()+"의 회원가입이 완료 되었습니다.");
			writeText(user.getId(),user.getPassword(), user.getAccount(),user.getName());
		}
		
	}
	//아이디 중복체크
	public boolean searchUserId(String userId) {
		boolean exist = true;
		//왜 userId만 출력이 되지 않는가?
		for(User user : userList) {
			if(user.getId().equals(userId)) {
				System.out.println("이미 존재하는 아이디입니다.");
				exist = false;
			}
		}
		return exist;
	}
}
