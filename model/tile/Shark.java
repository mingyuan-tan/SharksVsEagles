package model.tile;

public class Shark implements Territory {
	private static final long serialVersionUID = 1L;
	private final String territoryName;

	public Shark(String territoryName) {
		this.territoryName = territoryName;
	}

	@Override
	public void createTile() {
		System.out.println("Tile is in shark territory");
	}

	public String territoryName() {
		return territoryName;
	}
}