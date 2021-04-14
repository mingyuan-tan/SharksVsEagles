package model.tile;

import java.io.Serializable;

public interface Territory extends Serializable {

	public void createTile();

	public String territoryName();
}