public class Region{

	private String regionName;
	private String regionClimate;
	private int regionDifficulty;
	private Pokemon[] pokedexList;
	private int pokedexCount;

	public Region(String regionNameIn, String regionClimateIn, int regionDifficultyIn) {
		this.regionName = regionNameIn;
		this.regionClimate = regionClimateIn;
		this.regionDifficulty = regionDifficultyIn;
		this.pokedexList = new Pokemon[50];
		pokedexCount = 0; 

	}

	//Getters

	public String getName(){
		return regionName;
	}

	public String getClimate(){
		return regionClimate;
	}

	public int getDifficulty(){
		return regionDifficulty;
	}

	//Setters

	public void setName(String newRegionName) {
		regionName = newRegionName;
	}

		public void setClimate(String newRegionClimate) {
		regionClimate = newRegionClimate;
	}

		public void setDifficulty(int newDifficulty) {
		regionDifficulty = newDifficulty;
	}

	public void addToPokedex(Pokemon newPokemon) {
		if (pokedexCount < pokedexList.length) {
			pokedexList[pokedexCount++] = newPokemon;
		} else {
			System.out.println("Pokedex is full");
		}
	}

	public void printPokedex() {
		System.out.println("Pokedex Entries: ");
		for (int i = 0; i < pokedexCount; i++) {
			System.out.println((i+1) + " . " + pokedexList[i].getDetails());
		}
	}
}