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
			System.out.println("1.입금|2.출금|3.잔고|4.입출금리스트|5.로그인|6.회원가입|7.종료");
		}else {
			System.out.println("1.입금|2.출금|3.잔고|4.입출금리스트|5.로그아웃|6.회원가입|7.종료");
		}
		
		System.out.println("------------------------------------------------------------");
		System.out.print("메뉴선택> ");
		//숫자만 받는지 확인
		String input = sc.nextLine();
		int menu = matchNum(input);
		
		int inputValue;
		
		switch( menu ) {	
			case 1:
				if( Login.isLoginState() ) {
					System.out.print("입금하실 금액을 입력해주세요> ");
					input = sc.nextLine();
					inputValue = matchNum(input);
					if(inputValue != -999) {
						user.setAccount(user.getAccount()+inputValue);
						//입출금 내역을 저장하는 용도
						//accountList.listWrite(1, inputValue);
						updateText(user.getId(), inputValue,1);
						System.out.println("입금되었습니다. 잔고는 "+user.getAccount()+"입니다.");
					}
				}else{
					System.out.println("로그인 후 사용 가능합니다.");	
				}
				break;
				
			case 2:
				if(Login.isLoginState()) {
					System.out.print("출금하실 금액을 입력해주세요> ");
					input = sc.nextLine();
					inputValue = matchNum(input);
					if(inputValue != -999) {
						if((user.getAccount()-inputValue)>=0) {
							user.setAccount( user.getAccount() - inputValue );
							//입출금용도
							//accountList.listWrite( 2, inputValue );
							
							updateText(user.getId(), inputValue,2);
						}else {
							System.out.println("잔액이 부족합니다.");
						}
					}
				}else{
					System.out.println( "로그인 후 사용 가능합니다." );	
				}
				break;
				
			case 3:
				if(Login.isLoginState()) {
					System.out.println(user.getName()+"의 잔고는 "+user.getAccount());
				}else{
					System.out.println("로그인 후 사용 가능합니다.");	
				}
					
				break;
				
			case 4:
				if(Login.isLoginState()) {
					System.out.println(user.getName()+"님("+user.getId()+")의 입출금 리스트");
					System.out.println("----------------------------");
					accountList.listShow();
					
				}else{
					System.out.println("로그인 후 사용 가능합니다.");	
				}
				
				break;
				
			case 5:
				//로그인이 되어있으면
				if(!(Login.isLoginState())) {
					user = login.LoginSytem();
				}else{
					Login.setLoginState(false);
	 				System.out.println("로그아웃되었습니다.");
				}
				break;
				
			case 6:
				System.out.println("---회원가입");	
				register.addUser();
				break;
			case 7:
				setMode(menu);
				System.out.println("종료되었습니다.");
				break;
			default:
				System.out.println("선택할 메뉴의 숫자를 다시 입력해주세요");
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
