package com.sds.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.sds.component.UserBiz;
import com.sds.frame.Biz;
import com.sds.vo.User;

public class App {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		out:
		while(true) {
			Biz<String,User> ubiz = new UserBiz();
			System.out.println("MENU : r, g, q..");
			String cmd = sc.next();
			switch(cmd) {
			case "r":
				System.out.println("Input User Info : id, name, pwd");
				String id = sc.next();
				String pwd = sc.next();
				String name = sc.next();
				User u = new User(id,name,pwd);
				try {
					ubiz.register(u); // DB¿¡ ³Ö±â
				} catch (Exception e) {
					System.out.println("Insert Error");
					System.out.println(e.getMessage());
				}
				break;
			case "g":
				ArrayList<User> list = null;
				try {
					list = ubiz.get();
					for(User us:list) {
						System.out.println(us);
					}
				} catch (Exception e) {
					System.out.println("Select Error");
					System.out.println(e.getMessage());
				}
				break;
			case "q":
				System.out.println("Bye..");
				break out;
			}
		}
		sc.close();
	}

}
