package model.tile;

public class Obstacle extends Tile {
	private static final long serialVersionUID = 1L;
	private Territory territory;
	private String tileType;
	private int row;
	private int col;
	
	public Obstacle(Territory territory, String tileType, int row, int col) {
		super(territory,tileType, row, col);
		this.territory = territory;
		this.tileType = tileType;
		this.row = row;
		this.col = col;
	}

	@Override
	public void createTile() {
		System.out.println("Creating " + territory.territoryName()+ " for " + tileType + " at " + row + " , " + col);
	}
}