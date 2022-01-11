package src.ch01;

import java.util.Scanner;

public class Menu extends FileStream {
	
	private int mode;
	private int account;
	Scanner sc = new Scanner(System.in);
	User user = new User();
	Register register = new Register();
	Login login = new Login();
	AccountList  accountList = new AccountList();
	
	public void SelectMenu() {
		System.out.println("------------------------------------------------------------");
		if(!(Login.isLoginState())) {
			System.out.println("1.�Ա�|2.���|3.�ܰ�|4.����ݸ���Ʈ|5.�α���|6.ȸ������|7.����");
		}else {
			System.out.println("1.�Ա�|2.���|3.�ܰ�|4.����ݸ���Ʈ|5.�α׾ƿ�|6.ȸ������|7.����");
		}
		
		System.out.println("------------------------------------------------------------");
		System.out.print("�޴�����> ");
		//���ڸ� �޴��� Ȯ��
		String input = sc.nextLine();
		int menu = matchNum(input);
		
		int inputValue;
		
		switch( menu ) {	
			case 1:
				if( Login.isLoginState() ) {
					System.out.print("�Ա��Ͻ� �ݾ��� �Է����ּ���> ");
					input = sc.nextLine();
					inputValue = matchNum(input);
					if(inputValue != -999) {
						user.setAccount(user.getAccount()+inputValue);
						//����� ������ �����ϴ� �뵵
						//accountList.listWrite(1, inputValue);
						updateText(user.getId(), inputValue,1);
						System.out.println("�ԱݵǾ����ϴ�. �ܰ�� "+user.getAccount()+"�Դϴ�.");
					}
				}else{
					System.out.println("�α��� �� ��� �����մϴ�.");	
				}
				break;
				
			case 2:
				if(Login.isLoginState()) {
					System.out.print("����Ͻ� �ݾ��� �Է����ּ���> ");
					input = sc.nextLine();
					inputValue = matchNum(input);
					if(inputValue != -999) {
						if((user.getAccount()-inputValue)>=0) {
							user.setAccount( user.getAccount() - inputValue );
							//����ݿ뵵
							//accountList.listWrite( 2, inputValue );
							
							updateText(user.getId(), inputValue,2);
						}else {
							System.out.println("�ܾ��� �����մϴ�.");
						}
					}
				}else{
					System.out.println( "�α��� �� ��� �����մϴ�." );	
				}
				break;
				
			case 3:
				if(Login.isLoginState()) {
					System.out.println(user.getName()+"�� �ܰ�� "+user.getAccount());
				}else{
					System.out.println("�α��� �� ��� �����մϴ�.");	
				}
					
				break;
				
			case 4:
				if(Login.isLoginState()) {
					System.out.println(user.getName()+"��("+user.getId()+")�� ����� ����Ʈ");
					System.out.println("----------------------------");
					accountList.listShow();
					
				}else{
					System.out.println("�α��� �� ��� �����մϴ�.");	
				}
				
				break;
				
			case 5:
				//�α����� �Ǿ�������
				if(!(Login.isLoginState())) {
					user = login.LoginSytem();
				}else{
					Login.setLoginState(false);
	 				System.out.println("�α׾ƿ��Ǿ����ϴ�.");
				}
				break;
				
			case 6:
				System.out.println("---ȸ������");	
				register.addUser();
				break;
			case 7:
				setMode(menu);
				System.out.println("����Ǿ����ϴ�.");
				break;
			default:
				System.out.println("������ �޴��� ���ڸ� �ٽ� �Է����ּ���");
				break;
		}
	}

	public int subtractionAccount(int inputValue) {
		this.account -= inputValue;
		return this.account;
	}

	public int addAccount(int inputValue) {
		this.account += inputValue;
		return this.account;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}



	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	
}
