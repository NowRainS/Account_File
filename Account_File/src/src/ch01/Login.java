package src.ch01;

import java.util.*;
public class Login extends FileStream{
	
	private static boolean loginState = false; 
	Scanner sc = new Scanner(System.in);
	private String userId;
	private String userPassword;
	private int userAccount;
	private String userName;
	

	
	public User LoginSytem(){
		User returnUser = new User();
		System.out.print("아이디를 입력하세요> ");
		userId = sc.nextLine();
		System.out.print("비밀번호를 입력하세요> ");
		userPassword = sc.nextLine();
		
		//아이디 비밀번호 일치확인
		if(readText(userId,userPassword) == 0) {	
			//로그인 상태확인
			loginState = true;
			setUserAccount(Integer.parseInt(readAccount(userId, 1)));
			setUserName(readAccount(userId,2));
			returnUser.setId(userId);
			returnUser.setPassword(userPassword);
			returnUser.setName(userName);
			returnUser.setAccount(userAccount);
			
			
			System.out.println(getUserName()+"의 잔고는 "+getUserAccount());
			
		}else if(readText(userId,userPassword) == 1) {
			System.out.println("아이디가 존재하지 않습니다.");
		}else if(readText(userId,userPassword) == 2) {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}
		
		return returnUser;
	}
	
	
		
	


	public String getUserName() {
		return userName;
	}






	public void setUserName(String userName) {
		this.userName = userName;
	}






	public int getUserAccount() {
		return userAccount;
	}






	public void setUserAccount(int userAccount) {
		this.userAccount = userAccount;
	}






	public static boolean isLoginState() {
		return loginState;
	}





	public static void setLoginState(boolean loginState) {
		Login.loginState = loginState;
	}





	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
