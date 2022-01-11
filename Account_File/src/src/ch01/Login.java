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
		System.out.print("���̵� �Է��ϼ���> ");
		userId = sc.nextLine();
		System.out.print("��й�ȣ�� �Է��ϼ���> ");
		userPassword = sc.nextLine();
		
		//���̵� ��й�ȣ ��ġȮ��
		if(readText(userId,userPassword) == 0) {	
			//�α��� ����Ȯ��
			loginState = true;
			setUserAccount(Integer.parseInt(readAccount(userId, 1)));
			setUserName(readAccount(userId,2));
			returnUser.setId(userId);
			returnUser.setPassword(userPassword);
			returnUser.setName(userName);
			returnUser.setAccount(userAccount);
			
			
			System.out.println(getUserName()+"�� �ܰ�� "+getUserAccount());
			
		}else if(readText(userId,userPassword) == 1) {
			System.out.println("���̵� �������� �ʽ��ϴ�.");
		}else if(readText(userId,userPassword) == 2) {
			System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
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
