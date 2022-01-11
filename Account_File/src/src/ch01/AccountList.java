package src.ch01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccountList {
	private String context;
	private ArrayList<String> accountList = new ArrayList<>();

	
	
	public void listWrite(int mode, int inputValue) {
		//입금
		if(mode==1) {
			setContext(getDay()+" "+inputValue+" "+"입금");
			accountList.add(getContext());
		}else if(mode==2){
			setContext(getDay()+" "+inputValue+" "+"출금");
			accountList.add(getContext());
		}
	}
	public void listShow() {
		if(accountList.size()!=0) {
			for(int i=0; i<accountList.size(); i++) {
				System.out.println(accountList.get(i));
			}
		}else {
			System.out.println("내역이 없습니다.");
		}
		
	}
	
	
	public String getDay() {
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
		Date date = new Date();
		return format.format(date);
	}
	
	
	
	public String getContext() {
		return context;
	}


	public void setContext(String context) {
		this.context = context;
	}


	public ArrayList<String> getAccoutList() {
		return accountList;
	}

	public void setAccoutList(ArrayList<String> accoutList) {
		this.accountList = accoutList;
	}

	
}
