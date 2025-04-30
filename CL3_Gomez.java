//Luis Andres Gomez
// [CS1101] Comprehensive Lab 3

// This work is to be done individually. It is not permitted to.
// share, reproduce, or alter any part of this assignment for any
// purpose. Students are not permitted to share code, upload
// this assignment online in any form, or view/receive/
// modifying code written by anyone else. This assignment is part.
// of an academic course at The University of Texas at El Paso and
// a grade will be assigned for the work produced individually by
// the student.

import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CL3_Gomez {

	static Scanner input = new Scanner (System.in);
	static Random randomGenerator = new Random();

	public static void main(String [] args) {
		
		Region kantoRegion = new Region ("Kanto", "Warm", 1);
		Pokemon [] pokedexList = new Pokemon[50];
		int pokedexCount = 0;

		Trainer[] trainerList = new Trainer [20];
		int trainerCount = 0;

		Pokemon[] wildPokemonList = new Pokemon[50];
		int wildPokemonCount = 0;

		try {
			
			File pokedexFile = new File ("pokedex.txt");
			Scanner fileScanner = new Scanner(pokedexFile);

			while (fileScanner.hasNextLine()) {
				String[] parts = fileScanner.nextLine().trim().split("\\s+");
					if(parts.length == 4) {
						Pokemon newPokemon = new Pokemon(parts[0], parts[1], parts[2], parts[3]);
						pokedexList[pokedexCount++] = newPokemon;
						kantoRegion.addToPokedex(newPokemon);
					}

				}

				fileScanner.close();
			} catch(FileNotFoundException e) {
				System.out.println("Error: pokedex.txt not found.");
				return;
			}

			int menuChoice;
			do {
				System.out.println("*****************************");
				System.out.println("*   Welcome to Poke Miner   *");
				System.out.println("*****************************");
				System.out.println("Options");
				System.out.println("1. Modify Region");
				System.out.println("2. Add/Remove Trainer to Region");
				System.out.println("3. Add/Remove Wild Pokemon to Region");
				System.out.println("4. Modify Trainer");
				System.out.println("5. Add/Remove Pokemon From Trainer");
				System.out.println("6. List Pokemon in Trainer");
				System.out.println("7. Simulate interaction between two trainers");
				System.out.println("8. Exit");
				System.out.println("*****************************");
				System.out.println("\nSelect Option");

				while (!input.hasNextLine()) {
					System.out.print("Invalid input. Enter a number from 1 to 8: ");
					input.next();
				}
				
				if(input.hasNextInt()) {
				menuChoice = input.nextInt();
				input.nextLine();

			} else {
				System.out.println("Invalid input. Please enter a number from 1 to 8. ");
				input.nextLine();
				menuChoice = -1;
			}

				switch (menuChoice) {

					case 1: 
						System.out.println("Region Name: " + kantoRegion.getName());
						System.out.println("Climate: " + kantoRegion.getClimate());
						System.out.println("Difficulty: " + kantoRegion.getDifficulty());
						System.out.print("Would you like to modify region name, climate, difficulty or continue? ");
						String modChoice = input.nextLine().toLowerCase();

						if(modChoice.equals("name")){
							System.out.print("New Region Name: ");
							kantoRegion.setName(input.nextLine());
						} else if (modChoice.equals("climate")){
							System.out.print("New Climate: ");
							kantoRegion.setClimate(input.nextLine());
						} else if (modChoice.equals("difficulty")){
							System.out.print("New Difficulty (1-5): ");
							if (input.hasNextInt()) {
							int diff = input.nextInt();
							input.nextLine();

							if (diff >= 1 && diff <= 5) {
								kantoRegion.setDifficulty(diff);
							} else {
								System.out.println("Invalid difficulty range. Must be integer (1-5)");
							}
						} else {
							System.out.println("Invalid input. Difficulty must be a number 1-5.");
							input.nextLine();

							}
						
						} else if (!modChoice.equals("continue")) {
							System.out.println("\nInvalid option. Returning to main menu. ");

						}

						break;

					case 2:

						System.out.print("Would you like to add or remove a trainer to/from the region? ");
						String trainerAction = input.nextLine().toLowerCase();
						if(trainerAction.equals("add")) {
							if (trainerCount >= trainerList.length) {
								System.out.println("Trainer list is full!");
								break;
							}

							System.out.print("Trainer Name: ");
							trainerList[trainerCount++] = new Trainer(input.nextLine());
							System.out.println("Trainer successfully added!");
						} else if (trainerAction.equals("remove")) {
							System.out.print("Trainer Name: ");
							String name = input.nextLine();
							boolean removed = false;
							for (int i = 0; i < trainerCount; i++) {
								if(trainerList[i] != null && trainerList[i].getName().equalsIgnoreCase(name)) { 
									for (int j=i; j < trainerCount -1; j++) {
										trainerList[j] = trainerList[j+1];
									}

									trainerList[--trainerCount] = null;
									removed = true;
									System.out.println("Trainer removed.");
									break;
								}
							}

							if (!removed) System.out.println("Trainer not found.");
								} else {
									System.out.println("Invalid Option.");
								}
							break;

						case 3:

						System.out.print("Would you like to add or remove wild Pokemon to/from region? ");
						String wildAction = input.nextLine().toLowerCase();
						if (wildAction.equals("add")) { 
							if(wildPokemonCount < wildPokemonList.length && pokedexCount > 0) {
								int index = randomGenerator.nextInt(pokedexCount);
											
								Pokemon wild = new Pokemon(
									pokedexList[index].getName(),
									pokedexList[index].getType(),
									1,
									pokedexList[index].getHealth(),
									pokedexList[index].getAttackDamage());
											
									wildPokemonList[wildPokemonCount++] = wild;
									System.out.println("There is a new wild pokemon called " + wild.getName() + " in the region!");
											
								}
							} else if (wildAction.equals("remove")) {
									System.out.print("Wild Pokemon: ");
									String name = input.nextLine();
									boolean found = false;
									for (int i=0; i < wildPokemonCount; i++){
										if(wildPokemonList[i] != null && wildPokemonList[i].getName().equalsIgnoreCase(name)) {
											for (int j = i; j < wildPokemonCount - 1; j++) {
												wildPokemonList[j] = wildPokemonList[j + 1];

									}

									wildPokemonList[--wildPokemonCount] = null;
									found = true;
									System.out.println(name + " has fled the region.");
									break;

								}
							}

							if(!found) System.out.println("Wild Pokemon not found. ");

						} else { 
							System.out.println("Invalid Option.");
						}
						break;
									
						case 4:
							System.out.print("Which trainer? ");
							String trainerToModify = input.nextLine();
							Trainer foundTrainer = null;
								for (int i = 0;i < trainerCount; i++)	{
									if(trainerList[i].getName().equalsIgnoreCase(trainerToModify)) {
										foundTrainer = trainerList[i];
										break;
								}
							}	
								if (foundTrainer == null) {
									System.out.println("Trainer not found.");
									break;
							}	
								String trainerMod;
									do {
										System.out.println("Name: " + foundTrainer.getName());
										System.out.println("Is Champion? " + foundTrainer.isChampion());
										System.out.println("Partner: " + (foundTrainer.getPartner() != null ? foundTrainer.getPartner().getName() : "None"));
										System.out.print("Would you like to modify name, champ status, partner or continue? ");
										trainerMod = input.nextLine().toLowerCase();
										
										if(trainerMod.equals("name")) {
											System.out.print("New Trainer Name: ");
											foundTrainer.setName(input.nextLine());
										} else if (trainerMod.equals("champ status")) {
											System.out.print("Is Champion (true/false): ");
											foundTrainer.setChampion(input.nextBoolean()); input.nextLine();
										} else if (trainerMod.equals("partner")) {
											System.out.print("Enter new partner name: ");
											String partnerName = input.nextLine();
											for (int i = 0; i < wildPokemonCount; i++) {
												if(wildPokemonList[i].getName().equalsIgnoreCase(partnerName)) {
													foundTrainer.setPartner(wildPokemonList[i]);
													break;
												}
											}
										} else if (!trainerMod.equals("continue")) {
											System.out.println("Invalid option.");
											break;
										}

									} while (!trainerMod.equals("continue"));
									
									break;

						case 5:
								System.out.print("Which trainer? ");
								String trainerName = input.nextLine();
								Trainer targetTrainer = null;
									for(int i=0; i< trainerCount; i++) {
										if(trainerList[i].getName().equalsIgnoreCase(trainerName)) {
											targetTrainer = trainerList[i];
											break;
										}
									}

									if (targetTrainer == null) {
										System.out.println("Trainer not found.");
										break;
									}
									System.out.print("Would you like to add or remove Pokemon from Trainer? ");
									String teamAction = input.nextLine().toLowerCase();
									if (teamAction.equals("add")) {
										if (wildPokemonCount == 0) {
											System.out.println("No wild Pokemon available. ");
											break;
										}

										Pokemon newPokemon = wildPokemonList[wildPokemonCount -1];
										System.out.println("Pokemon Encountered: " + newPokemon.getDetails());
										targetTrainer.addPokemon(newPokemon);
										wildPokemonList[--wildPokemonCount] = null;
										System.out.println("There is a new addition for " + targetTrainer.getName() + "!");
									} else if (teamAction.equals("remove")) {
										System.out.println("Enter name of Pokemon to remove: ");
										String removeName = input.nextLine();
										boolean removed = targetTrainer.removePokemon(removeName);
										if(!removed) 
											System.out.println("Pokemon not found in the team. ");
									} else {
										System.out.println("Invalid Option. ");
									}
									break;

						case 6:
									System.out.print("Which trainer? ");
									String tName = input.nextLine();
									Trainer trainerPrint = null;
									for (int i=0; i < trainerCount; i++) {
										if (trainerList[i].getName().equalsIgnoreCase(tName)) {
											trainerPrint = trainerList[i];
											break;
										}

									}

									if (trainerPrint == null) {
										System.out.println("Trainer not found");
										break;
									}

									trainerPrint.printTeam();
									break;

						case 7:
							System.out.println("Simulate interaction between two Trainers");
							System.out.print("First Trainer: ");
							String firstTrainerName = input.nextLine();
							System.out.print("Second Trainer: ");
							String secondTrainerName = input.nextLine();

							Trainer firstTrainer = null, secondTrainer = null;
								for (int i = 0; i < trainerCount; i++) {
									if(trainerList[i].getName().equalsIgnoreCase(firstTrainerName)) {
										firstTrainer = trainerList[i];
								}
									
								if(trainerList[i].getName().equalsIgnoreCase(secondTrainerName)) {
									secondTrainer = trainerList[i];
								}

							}
								if (firstTrainer == null || secondTrainer == null) {
									System.out.println("One or both trainers not found.");
									break;
							}
								if(firstTrainer.getTeamCount() == 0 || secondTrainer.getTeamCount() == 0) {
									System.out.println("Both trainers must have at least one Pokemon to battle. ");
									break;

							}

								System.out.println("Simulating interaction between " + firstTrainer.getName() + " and " + secondTrainer.getName() + "...");

									int firstIndex = 0;
									int secondIndex = 0;

									while (firstIndex < firstTrainer.getTeamCount() && secondIndex < secondTrainer.getTeamCount()) {
										Pokemon firstPokemon = firstTrainer.getPokemon(firstIndex);
										Pokemon secondPokemon = secondTrainer.getPokemon(secondIndex);

									while(firstPokemon.getHealth() > 0 && secondPokemon.getHealth() > 0) {
										System.out.println("- " + firstTrainer.getName() + " attacks with " + firstPokemon.getName() + " (Level " + firstPokemon.getLevel() + ") ");
										int newHealthSecond = secondPokemon.getHealth() - (int) firstPokemon.getAttackDamage();
										secondPokemon.setHealth(newHealthSecond);
										if(newHealthSecond <= 0) {
											System.out.println("- " + secondPokemon.getName() + " health reaches below 0 pts");
											break;
									} else {
										System.out.println("- " + secondPokemon.getName() + " health reaches " + newHealthSecond + " pts");
                                    }
								
								System.out.println("- " + secondTrainer.getName() + " attacks with " + secondPokemon.getName() + " (Level " + secondPokemon.getLevel() + ")");
								int newHealthFirst = firstPokemon.getHealth() - (int) secondPokemon.getAttackDamage();
								firstPokemon.setHealth(newHealthFirst);
								if(newHealthFirst <= 0) {
									System.out.println("- " + firstPokemon.getName() + " health reaches below 0 pts");
									break;
								} else {
									System.out.println("- " + firstPokemon.getName() + " health reaches " + newHealthFirst + " pts");
									}
								}

								if (firstPokemon.getHealth() <= 0) {
									firstIndex++;
								}

								if(secondPokemon.getHealth() <= 0) {
									secondIndex++;
								}
							}

							if (firstIndex >= firstTrainer.getTeamCount()) {
								System.out.println("- " + secondTrainer.getName() + " Wins!");

							} else if (secondIndex >= secondTrainer.getTeamCount()) {
								System.out.println ("- " + firstTrainer.getName() + " Wins!");

							}

							break; 

						}

								} while (menuChoice !=8);

								System.out.println("Exiting game. Goodbye!");
							}
						}
					