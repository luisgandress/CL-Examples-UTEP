public class Pokemon{

	private String pokemonName;
	private String pokemonType;
	private int pokemonHealth;
	private double pokemonAttackDamage;
	private int pokemonLevel;

	public Pokemon(String pokemonNameIn, String pokemonTypeIn) {
		this.pokemonName = pokemonNameIn;
		this.pokemonType = pokemonTypeIn;
		this.pokemonHealth = 0;
		this.pokemonAttackDamage = 0;
		this.pokemonLevel = 1;

	}

	public Pokemon(String pokemonNameIn, String pokemonTypeIn, int pokemonLevelIn, int pokemonHealthIn, double pokemonAttackDamageIn) {
		this.pokemonName = pokemonNameIn;
		this.pokemonType = pokemonTypeIn;
		this.pokemonLevel = pokemonLevelIn;
		this.pokemonHealth = pokemonHealthIn;
		this.pokemonAttackDamage = pokemonAttackDamageIn;
		
	}

	public Pokemon(String pokemonNameIn, String pokemonTypeIn, String pokemonHealthIn, String pokemonAttackDamageIn) {
		this.pokemonName = pokemonNameIn;
		this.pokemonType = pokemonTypeIn;
		this.pokemonLevel = 1;
		this.pokemonHealth = Integer.valueOf(pokemonHealthIn);
		this.pokemonAttackDamage = Double.valueOf(pokemonAttackDamageIn);
	}
	
	//Getters

	
	public String getName(){
		return pokemonName;
	}

	public String getType(){
		return pokemonType;
	}

	public int getLevel(){
		return pokemonLevel;
	}

	public int getHealth(){
		return pokemonHealth;
	}

	public double getAttackDamage(){
		return pokemonAttackDamage;
	}


	//Setters

	public void setName(String pokemonNameIn) { 
		this.pokemonName = pokemonNameIn;
	}

	public void setType(String pokemonTypeIn) { 
		this.pokemonType = pokemonTypeIn;
	}

	public void setLevel(int pokemonLevelIn) { 
		this.pokemonLevel = pokemonLevelIn;
	}

	public void setHealth(int pokemonHealthIn) {
		this.pokemonHealth = pokemonHealthIn;
	}

	public void setAttackDamage(double pokemonAttackDamageIn) {
		this.pokemonAttackDamage = pokemonAttackDamageIn;

	}

	//Methods

	public void levelUp() {
		this.pokemonLevel += 1;
		this.pokemonHealth += 14;
		this.pokemonAttackDamage +=1;
	}

	public void speak() {
		System.out.println(pokemonName + "! " + pokemonName + "!");

	}

	public String getDetails(){
		return "Name: " + pokemonName + " (" + pokemonType + ") Health: " + pokemonHealth + ", Attack: " + pokemonAttackDamage + ", Level: " + pokemonLevel;
	}
}
	