public class Trainer{

	private String trainerName;
	private boolean trainerIsChampion;
	private Pokemon trainerPartner;
	private Pokemon[] trainerTeam;
	private int trainerTeamCount;

	public Trainer(String trainerNameIn){
		this.trainerName = trainerNameIn;
		this.trainerIsChampion = false;
		this.trainerPartner = null;
		this.trainerTeam = new Pokemon[6];
		this.trainerTeamCount = 0;

	}

	//Getters

	public String getName(){
		return trainerName;
	}

	public boolean isChampion(){
		return trainerIsChampion;
	}

	public Pokemon getPartner(){
		return trainerPartner;
	}

	public int getTeamCount() {
		return trainerTeamCount;
	}

	public Pokemon getPokemon(int index) {
		if(index >= 0 && index < trainerTeamCount) {
			return trainerTeam[index];
		}

		return null;
	}

	//Setters

	public void setName(String trainerNameIn) {
		this.trainerName = trainerNameIn;
	}

		public void setChampion(boolean status) {
		trainerIsChampion = status;
	}

		public void setPartner(Pokemon newPartner) {
		trainerPartner = newPartner;
	}

	//Methods

	public void addPokemon(Pokemon newPokemon) {
		if(trainerTeamCount < trainerTeam.length) {
			trainerTeam[trainerTeamCount++] = newPokemon;
		} else {
			System.out.println("Team is full");
		}
	}

	public boolean removePokemon(String pokemonNameToRemove) {
		for(int i = 0; i < trainerTeamCount; i++) {
			if (trainerTeam[i] != null && trainerTeam[i].getName().equalsIgnoreCase(pokemonNameToRemove)) {
				for (int j = i; j < trainerTeamCount -1; j++) {
					trainerTeam[j] = trainerTeam[j+1];
				}
				trainerTeam[--trainerTeamCount] = null;
				return true;
			}
		}

		return false;
	}

	public void printTeam() {
		if(trainerTeamCount == 0) {
			System.out.println(trainerName + " 's team is empty.");
			return;

		}
		System.out.println(trainerName + "'s Pokemon Team: ");
		for (int i=0; i < trainerTeamCount; i++) {
			Pokemon teamPokemon = trainerTeam[i];
			System.out.println((i+1) + ". " + teamPokemon.getName() + ", Type: " + teamPokemon.getType() + ", Level: " + teamPokemon.getLevel() + ", Health: " + teamPokemon.getHealth());
		}
	}

	public void battle(Trainer opponentTrainer){
		if(trainerTeamCount == 0 || opponentTrainer.trainerTeamCount == 0) {
			System.out.println("Both tariners must have at least one Pokemon to battle. ");
			return;
		}

		Pokemon myFirstPokemon = trainerTeam[0];
		Pokemon opponentFirstPokemon = opponentTrainer.trainerTeam[0];
		System.out.println("Simulating interaction between " + trainerName + " and " + opponentTrainer.trainerName + "....");
		
		while(myFirstPokemon.getHealth() > 0 && opponentFirstPokemon.getHealth() > 0){
			System.out.println("- " + trainerName + " attacks with " + myFirstPokemon.getName() + " (Level " + myFirstPokemon.getLevel() + ")");
			int opponentNewHealth = opponentFirstPokemon.getHealth() - (int) myFirstPokemon.getAttackDamage();
			opponentFirstPokemon.setHealth(opponentNewHealth);
			System.out.println("- " + opponentFirstPokemon.getName() + " health reaches " + Math.max(opponentNewHealth, 0) + " pts");
			if (opponentNewHealth <= 0)
				break;

			System.out.println("- " + opponentTrainer.trainerName + " attacks with " + opponentFirstPokemon.getName() + " (Level " + opponentFirstPokemon.getLevel() + ")");
			int myNewHealth = myFirstPokemon.getHealth() - (int) opponentFirstPokemon.getAttackDamage();
			myFirstPokemon.setHealth(myNewHealth);
			System.out.println("- " + myFirstPokemon.getName() + " health reaches " + Math.max(myNewHealth,0) + " pts");
		}

	if (myFirstPokemon.getHealth() <= 0){
		System.out.println("- " + opponentFirstPokemon.getName() + " Wins!");
	
	} else {
		System.out.println("- " + myFirstPokemon.getName() + " Wins!");

		}
	}
}
