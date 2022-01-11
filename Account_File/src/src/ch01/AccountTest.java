package src.ch01;

public class AccountTest {
	public static void main(String[] args) {
		Menu menu = new Menu();
		
		while(menu.getMode() != 8) {
			menu.SelectMenu();
		}
		
	}
}
