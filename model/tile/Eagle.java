
package model.tile;

public class Eagle implements Territory {
	private static final long serialVersionUID = 1L;
	private final String territoryName;

	public Eagle(String territoryName) {
		this.territoryName = territoryName;
	}

	@Override
	public void createTile() {
		System.out.println("Tile is in eagle territory");
	}

	public String territoryName() {
		return territoryName;
	}
}