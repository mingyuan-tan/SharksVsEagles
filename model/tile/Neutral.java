
package model.tile;

public class Neutral implements Territory {
	private static final long serialVersionUID = 1L;
	private final String territoryName;

	public Neutral(String territoryName) {
		this.territoryName = territoryName;
	}

	@Override
	public void createTile() {
		System.out.println("Tile is in neutral territory");
	}

	public String territoryName() {
		return territoryName;
	}
}