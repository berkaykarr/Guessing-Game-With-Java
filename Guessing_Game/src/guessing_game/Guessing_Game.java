package guessing_game;

import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.ArrayList;

public class Guessing_Game {
        // we have used static final int for making the coding easy if the programmer wants to chance the difficulty.
static final int EASY_ALPHABET_CHANCE = 15;
static final int MEDIUM_ALPHABET_CHANCE = 10;
static final int HARD_ALPHABET_CHANCE = 5;
static final int EASY_NUMBER_CHANCE = 5;
static final int MEDIUM_NUMBER_CHANCE = 4;
static final int HARD_NUMBER_CHANCE = 3;
        // We creat arraylist for alphabet score and number score.We use  static keywords for sharing all class
	static ArrayList<Integer> numberscore = new ArrayList<>(); 
	static ArrayList<Integer> alphabetscore = new ArrayList<>();

   
    public static void main(String[] args) {
        menu();
    }
    

		public static void menu()  {
			int choice=0; 
			Scanner console= new Scanner(System.in);
			do {  //We use  do-while loop to ensure that the user enters a valid selection.
				try {   
					System.out.println("1. Guess the Number.");
					System.out.println("2. Guess the Alphabet");
					System.out.println("3. High score");
					System.out.println("4. Credit");
					System.out.println("5. Exit");
					System.out.println("enter your choice: ");
					
					choice=console.nextInt(); 
                                       
                                        // we use switch statement to handle user's menu choice
				        switch(choice) {
				        
				        case 1 -> {
                                            startmenu();// it shows the difficulty level.
                                            int choice2=0;
                                            do {//We use  do-while loop to ensure that the user enters a valid selection.
                                                try {/* we use try catch statements for checking user's choice whether if it is valid or not.
                                                    we prevent  errors to occur if the user enters something different from  number "1 ,2, 3, 4"*/
                                                    choice2=console.nextInt();// read the user's choice for difficulty level
                                                    instruction();
                                                    if(choice2==1) {
                                                        easylevel(console);
                                                    }
                                                    if(choice2==2) {
                                                        mediumlevel(console);
                                                    }
                                                    if(choice2==3) {
                                                        hardlevel(console);
                                                    }
                                                    if(choice2==4) {
                                                        menu();
                                                    }
                                                    else {System.out.println("This is not valid.");
                                                    startmenu();
                                                    
                                                    }
                                                    
                                                    
                                                } catch (Exception e) {// If the user enters something other than an integer, catch the exception and prompt them again
                                                    System.out.println("Please insert a number");
                                                    console.next();
                                                    
                                                }
                                            }
                                            while(choice2==0 );
                                        }
				                
				        case 2 -> {
                                            startmenu();// call the method to display the difficulty level options
                                            int choice3=console.nextInt();
                                            instruction2(); // call the method to display the game instructions for alphabet guessing
                                            
                                            if(choice3==1) {
                                                easyalphabet(console); // call the method to start the game for alphabet guessing at the easy level
                                            }
                                            if(choice3==2) {
                                                mediumalphabet(console); // call the method to start the game for alphabet guessing at the medium level
                                            }
                                            if(choice3==3) {
                                                hardalphabet(console);// call the method to start the game for alphabet guessing at the hard level
                                            }
                                            if(choice3==4) {
                                                menu(); // return to the menu
                                            }
                                        }
				        
					    case 3 -> showHighestScores(numberscore, alphabetscore); // call the method to show the high scores for both games
					    
					    case 4 -> showcredit(); 
					    
					    case 5 -> {
                                                System.out.println("You Exited the Game.");
                                                System.exit(0);
                                        } 
					    
				       default -> {
                                           System.out.println("This selection is not valid.");
                                           System.out.println("-------------------");
                                           menu();
                                        }
				}
				}catch (InputMismatchException e) {// If the user enters something other than an integer, catch the exception and prompt them again
		                 System.out.println("Please insert a number");
		                 console.next();  
					
				}
			}
			        while(choice == 0 );
				
		}
			
		
		
		
		public static void startmenu()  {
			System.out.println("Start Menu");
			System.out.println("-----------");
			System.out.println("Select a difficulty level");
			System.out.println("1-Easy");
			System.out.println("2-Normal");
			System.out.println("3-Hard");
			System.out.println("4-Return to main menu");
			
		}
		public static void guessnumber(int chance, int tries)   { //
			
			Scanner consol = new Scanner(System.in);
			HashSet<Integer> Exist = new HashSet<>();//we use here hashset because we do not want to store same scores for guessing number
			int times=1;
			int number=(int) ((Math.random()*tries)+1); // generate the random number
			System.out.println();
			System.out.println("This level gives you "+chance+" tries to guess the number");
			System.out.println();
			int loop=chance;
			int yournumber=0;
                        // Run the guessing loop
			for(int i=1;i<=loop || yournumber==0;i++){
				
				
				System.out.println("enter a number between 1 and "+tries+ ".");
				System.out.println();
				

					try {  
                                                // Read the user input
						yournumber=consol.nextInt();
						if(yournumber>tries || yournumber<1) { // Check if the input is within the valid range
							System.out.println("This number is not valid. Try again ");
							System.out.println();
							i--;
						}
						else if(Exist.contains(yournumber)) { // Check if the input has already been guessed
							System.out.println("You have already guessed the number " + "\"" +yournumber+ "\" ");
				        	i--;
				        	continue;
						}
						else if(yournumber==number) { // Check if the input is correct
							System.out.println("Correct! You've won the game with just "+ times +" times");
							System.out.println();
							numberscore.add(times);
							break;
						}
						else if(number<yournumber) {// Check if the input is too high
							Exist.add(yournumber);
							chance--;
							times++;
							System.out.println("Wrong! Your number is too high. "  + chance +" chance left");
							System.out.println();
						}
						else if(number>yournumber) {// Check if the input is too low
							Exist.add(yournumber);
							chance--;
							times++;
							System.out.println("Wrong! Your number is too low. "  + chance +" chance left");
							System.out.println();
						}
					    if(chance==0) { // Check if the user has run out of chances
								System.out.println("You lost the game. The number was: "+number);
								System.out.println();
							}
						
						
					} catch (Exception e) {// if user enters invalid  inputs ,this code will be run. 
						System.out.println("Please insert a number");
			            consol.next();
			            i--;
					}
				
				
				
			
		}
		
			System.out.println("Play Again. (Press 1 for Easy Level, 2 for Medium Level, 3 for Hard Level)");
			System.out.println();
			System.out.println("Press 4 To Go Back To Menu");
			
					int selection = 0;
					
					do {// repeat the code block until the selection is between 1 and 4 inclusive
						try {
							selection=consol.nextInt();
							
							if(selection == 1) { // if selection is 1, call the easylevel method

								easylevel(consol);
							}
							if(selection == 2) {// if selection is 2, call the mediumlevel method
								mediumlevel(consol);
							}
							if(selection == 3) { // if selection is 3, call the hardlevel method
								hardlevel(consol);
							}
							if(selection == 4) {// if selection is 4, call the menu method
								menu();
							}
							
						}
						catch (Exception e) {// if the input invalid value , catch the exception
							
							System.out.println("Please insert a number");
				            consol.next();
						}
					}
						while(selection==0 || selection > 4 || selection<1);// continue the loop while selection is not between 1 and 4 inclusive
					
		}
		
		
		public static void easylevel(Scanner consol)  {
			System.out.println("Easy Level!");
			System.out.println("-----------");
			System.out.println();

		guessnumber(EASY_NUMBER_CHANCE,15);
		}
		public static void mediumlevel(Scanner consol)  { 
			System.out.println("Medium Level!");
			System.out.println("-----------");
			System.out.println();

			guessnumber(MEDIUM_NUMBER_CHANCE,30);
		}
		
		public static void hardlevel(Scanner consol)  {
			System.out.println("Hard Level!");
			System.out.println("-----------");
			System.out.println();

			guessnumber(HARD_NUMBER_CHANCE,40);
		}
		
		public static void easyalphabet(Scanner console)  {
			System.out.println("Easy Level!");
			System.out.println("-----------");
			System.out.println();

			guessalphabet(EASY_ALPHABET_CHANCE);
	         
			}
		public static void mediumalphabet(Scanner console)  {
			
			System.out.println("Medium Level!");
			System.out.println("-----------");
			System.out.println();

			guessalphabet(MEDIUM_ALPHABET_CHANCE);

			}
		public static void hardalphabet(Scanner console) { 
			System.out.println("Hard Level!");
			System.out.println("-----------");
			System.out.println();

			guessalphabet(HARD_ALPHABET_CHANCE);
			}
			
		public static void guessalphabet(int chance)  {
			
			    Scanner consol = new Scanner(System.in);
			    HashSet<String> Exist = new HashSet<>();  // HashSet to keep track of guessed letters to avoid repeating the same guess
			    String[] letters= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};// Array of alphabet letters to randomly select a letter from

			    int x = (int) (Math.random() * letters.length); // Randomly select a letter from the array
			    int times = 0;// Number of times user guesses the letter correctly
			    int chanceleft=chance; // Number of chances user has to guess the letter
                            
                            // Print instructions for the user
			    System.out.println("This level gives you " + chance + " tries to guess the alphabet");
			    System.out.println("You can press ? to have a hint (vowel or consonant)");

                            
                             // Loop for the number of chances user has
			    for (int i = 1; i <= chance; i++) {
                                
                                // Ask user to enter an alphabet
			        System.out.println("Enter an alphabet:");
			        System.out.println();

			        String alphabetString=consol.next();
			        
			        boolean validLetter = false;
                                // Check if user input is a valid letter or a hint
                                for (String letter : letters) {
                                    if (alphabetString.equalsIgnoreCase(letter) || alphabetString.equals("?")) {
                                        validLetter = true;
                                        break;
                                    }
                                }
                                
                                // If user input is not a valid letter or a hint, print error message and ask for input again
			        if (!validLetter || alphabetString.length()>1 ) {
			            System.out.println("This is not valid ");
			            i--;
			            continue;
			        }
                                
                                // If user input is a hint, print a hint and ask for input again
			        if (alphabetString.equalsIgnoreCase("?")) {
			            System.out.println("Hint: " + ("A".equals(letters[x]) || "E".equals(letters[x]) || "I".equals(letters[x]) || "O".equals(letters[x]) || "U".equals(letters[x]) ? "Vowel" : "Consonant"));
			             i--;
			            continue;
			        }
                                
                                // If user input matches the randomly selected letter, print success message and exit the loop
			        if(alphabetString.equalsIgnoreCase(letters[x])){
			            System.out.println("Correct! You win the game " + (times + 1) + " times");
			            times++;
			            break;
			        } 
                                
                                  // If user input was already guessed before, print error message and ask for input again
			        if(Exist.contains(alphabetString)) {
			        	System.out.println("You have already guessed the letter " + "\"" +alphabetString.toUpperCase()+ "\" ");
			        	i--;
			        	continue;
			        }
                                
                                 // If user input does not match the randomly selected letter and was not guessed before, add it to the guessed letters set, decrease the remaining chances by 1, print error message, and ask for input again
			        else {
			            Exist.add(alphabetString.toLowerCase());
			        	Exist.add(alphabetString.toUpperCase());
			        	chanceleft--;
			            System.out.println("Wrong! You have " + (chanceleft) + " chance(s) left.");
			            times++;

			        }
                                    
                                // If you used all your right and didn't guess the letter correctly, you lost.

			        if(chanceleft==0) {
		            	System.out.println("You Have Lost! The Letter Was: "+ "\"" +letters[x]+ "\" ");
		            }
			    }
	            
			    alphabetscore.add(times);// Add the number of tries it took the player to guess the letter to the list of scores.

                            // Prompts the user to play again and presents the options

			    System.out.println("Play Again? Press 1 for Easy Level, 2 for Medium Level, 3 for Hard Level");
			    System.out.println("Press 4 To Go Back To Menu");


				int selection = 0;
				
				do {
					try {
						selection=consol.nextInt();  // Get the player's selection for what to do next
						
						if(selection == 1) {

							easylevel(consol);  // If they select 1, start a new game on the easy level

						}
						if(selection == 2) {

							mediumlevel(consol); // If they select 2, start a new game on the medium level

						}
						if(selection == 3) {

							hardlevel(consol); // If they select 3, start a new game on the hard level

						}
						if(selection == 4) {
							menu();  // If they select 4, return to the main menu
						}
						
					}
					catch (Exception e) {  // If the player enters something that's not a number, prompt them to try again

						System.out.println("Please insert a number");
			                        consol.next();
					}
				}
					while(selection==0 || selection > 4 || selection<1);
				
	}
	
	
			

		
		
		public static void instruction() { //instruction for guessing number
			System.out.println(" 1. Guess the number based on the gaven range.");
			System.out.println(" 2. You are allowed to make one guess at a time.");
			System.out.println(" 3. Each game has different chances regarding to the level (Easy: "+EASY_NUMBER_CHANCE+" Medium: "+MEDIUM_NUMBER_CHANCE+" Hard: "+HARD_NUMBER_CHANCE+")");
			System.out.println(" 4. Once you have used up all your chances, you lose the game.");
			System.out.println("-----------------------");
			System.out.println("Good Luck!");
			System.out.println();

		}
		public static void instruction2() {//instruction for  guessing alphabet
			System.out.println(" 1. Guess the alphabet ");
			System.out.println(" 2. You are allowed to make one guess at a time.");
			System.out.println(" 3. Each game has different chances regarding to the level (Easy:" +EASY_ALPHABET_CHANCE+" Medium: "+MEDIUM_ALPHABET_CHANCE+" Hard: "+HARD_ALPHABET_CHANCE+")");
			System.out.println(" 4. Once you have used up all your chances, you lose the game.");
			System.out.println("-----------------------");
			System.out.println("Good Luck!");
			System.out.println();

			
		}

	public static void showcredit()  {
	    Scanner console= new Scanner(System.in);
            
                //Display information about founders this game
		System.out.println("Student id: 200209027 - Student Name: Simay Avcu");
                System.out.println("Student id: 200209021 - Student Name: Ebrar Durucan");
		System.out.println("Student id: 200209004 - Student Name: Vedat Yıldırım");
                System.out.println("Student id: 200209040 - Student Name: Berkay Karadeniz ");
	        System.out.println("Press 1 To Go Back To Menu");
	    
		int selection = 0;
		
		do {
			try {
				selection=console.nextInt();
				if (selection == 1) {   // If the user entered 1, go back to the menu
			    	menu();
			}
				else {  // If the user entered different from "1" , display an error message

					System.out.println("please enter a valid number  ");
				}
			
		}catch (Exception e) {  // If the user entered a non-numeric input, display an error message and clear the scanner
			System.out.println("Please insert a number");
                        console.next();
		}
		}
			while (selection==0) ;
				
		
	   
	}

	public static void showHighestScores(ArrayList<Integer> numberScores, ArrayList<Integer> alphabetScores) { 
		 Scanner console = new Scanner(System.in);
		    if (numberScores.isEmpty() && alphabetScores.isEmpty()){ // // check if there are any  scores yet .

		        System.out.println("There are no high scores yet.");
		        System.out.println();
		       
		    }
		    System.out.println("Number Guessing Game Highest Scores:");
		    if (numberScores.isEmpty()) {  // display highest number scores

		        System.out.println("There are no high scores yet.");
		    } else {
		        Collections.sort(numberScores); // we use collection sort for sorting scores
		        numberScores = new ArrayList<>(new LinkedHashSet<>(numberScores)); // Remove duplicate scores

		        for (int i = 0; i < numberScores.size(); i++) {
		            System.out.println((i + 1) + ". " + numberScores.get(i) + " attempts to find!");
		        }
		    }
		    System.out.println();
		    System.out.println("Alphabet Guessing Game Highest Scores:");
		    if (alphabetScores.isEmpty()) {  // display highest alphabet scores
		        System.out.println("There are no high scores yet.");
		    } else {
		        Collections.sort(alphabetScores);// Sort "alphabetScores" ArrayList from smallest to larges
		        alphabetScores = new ArrayList<>(new LinkedHashSet<>(alphabetScores)); // Convert "alphabetScores" ArrayList to LinkedHashSet to remove duplicate elements
		        for (int i = 0; i < alphabetScores.size(); i++) {
		            System.out.println((i + 1) + ". " + alphabetScores.get(i) + " attempts to find!");
		        }
		    }
		    System.out.println();
		    System.out.println("Press 1 To Go Back To Menu");
		    int selection = 0;
		    
			while(selection==0) { // loop until a valid selection is made

				try { 
					selection=console.nextInt();
					if (selection == 1) { 
				    	menu();
				}
					else {
						System.out.println("Please enter a valid number  ");
					}
				
			}catch (Exception e) {  // if an exception occurs, display an error message

				System.out.println("Please insert a number");
	                        console.next();  // clear the input
			}
			}
			
		    
		}

}