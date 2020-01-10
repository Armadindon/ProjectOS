package fr.upem.dut.info.autoConfigurator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fr.upem.dut.info.autoConfigurator.components.MotherBoard;
import fr.upem.dut.info.autoConfigurator.components.Shop;
import fr.upem.dut.info.autoConfigurator.components.Slots;

public class Computer {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private static MotherBoard mb;
	private static boolean on = true;
	

	public static void main(String[] args) throws IOException, InterruptedException {
		//On initialise une carte mère (Basé sur une 
		Map<Slots, Integer> numberOfSlots = new HashMap<>();
		
		new Shop(); //initialise un shop
		
		numberOfSlots.put(Slots.PCIE1, 3);
		numberOfSlots.put(Slots.PCIE16, 2);
		
		mb = new MotherBoard(numberOfSlots);
		
		printBoot();

		Scanner scanner =  new Scanner(System.in);
		
		//On lance la boucle principale
		while (scanner.hasNext()) {
			String command = scanner.nextLine();
			if(on) {
				if(command.equals("help")) {
					System.out.println("-lsHard\n"+
										"-poweroff\n"
										+ "-Other base linux commands");
				}else if(command.equals("poweroff")){
					System.out.println("shutdown of your system ...");
					on = false;
					System.out.print("> ");
				}else if(command.equals("lsHard")){
					mb.printCards();
				}else {
					try {
						Process p = Runtime.getRuntime().exec(command.split(" "));
						p.waitFor();
						Scanner output = new Scanner(p.getInputStream());
						while(output.hasNext()) System.out.println(output.nextLine());
						output.close();
						Scanner errorOutput = new Scanner(p.getErrorStream());
						while(errorOutput.hasNext()) System.err.println(errorOutput.nextLine());
						errorOutput.close();
					} catch (IOException e) {
						System.out.println(command+" n'est pas reconnu comme un programme");
					} catch (InterruptedException e) {
						//SignalHandler
					}
				}
				if(on) System.out.print(ANSI_GREEN+"Ch3rr13r@b3stPc1nTh3W0rld"+ANSI_BLUE+" ~ "+ANSI_RESET);
			}else {
				if(command.equals("help")) {
					System.out.println("-addCard\n"+
										"-rmCard\n"+
										"-poweron");
				}else if(command.equals("poweron")){
					System.out.println("boot of your system !");
					try {
						mb.configure();
						on = true;
						printBoot();
					}catch (Exception e) {
						System.out.println("Plus d'irq de libre, veuillez de régler le problème avant de boot ...");
					}
				}else if(command.equals("addCard")) {
					Shop.showCatalog();
					while(scanner.hasNext()) {
						if(scanner.hasNextInt()) {
							mb.addCard(Shop.getCard(scanner.nextInt()));
							break;
						}
					}
				}else if(command.equals("rmCard")) {
					mb.printCards();
					while(scanner.hasNext()) {
						if(!mb.haveCards()) break;
						if(scanner.hasNextInt()) {
							mb.removeCard(mb.getCard(scanner.nextInt()));
							break;
						}
					}
				}
				if(!on) System.out.print("> ");
			}
		}
		
		scanner.close();
		
	}
	

	private static void printBoot() {
		System.out.println("==============================\n"
				+ANSI_YELLOW +"  Ubuntu 20.04 - Focal Fossa\n"
				+ "  "+LocalDateTime.now().toString() + ANSI_RESET + "\n"
				+ "==============================\n"
				+ "Welcome user Ch3rr13r !\n"
				+ "(type help for the list of commands)\n");

		System.out.print(ANSI_GREEN+"Ch3rr13r@b3stPc1nTh3W0rld"+ANSI_BLUE+" ~ "+ANSI_RESET);
	}

}
