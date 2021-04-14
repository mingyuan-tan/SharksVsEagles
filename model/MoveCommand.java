package model;

import model.piece.EagleFlag;
import model.piece.Piece.PieceType;
import model.tile.Tile;
import util.Utilities;
import model.piece.SharkFlag;

public class MoveCommand extends Command {

	public MoveCommand(Board board, Tile tile) {
		super(board, tile);
	}

	@Override
	public boolean execute() {
		var fromTile = getBoard().getFromTile();

		if (!getBoard().isPieceSelected() || fromTile.getPiece().getTYPE() == PieceType.FLAG) {
			return false;
		}

		// if the target is a valid move and has an enemy piece
		if (getBoard().getMovableTiles().contains(tile) && tile.getPiece() != null
				&& fromTile.getPiece().getLEAGUE() != tile.getPiece().getLEAGUE()) {

			// if the selected piece cannot attack
			if (fromTile.getPiece().getCanAttack() == false) {
				Utilities.infoAlert("Invalid action", "Your current piece cannot attack right now "
						+ "(due to its skill effect, or its current mode)." + "\nNOTE: Only NORMAL mode can attack.");

				getBoard().setPieceSelected(false);
				fromTile = null;
				return false;
			}

			// if enemy piece is standing on obstacle
			if (fromTile.getPiece().getLEAGUE() == League.SHARK && tile.getTileType().equals("Obstacle")
					&& tile.getTer().territoryName().equals("Eagle")) {
				Utilities.infoAlert("Invalid action",
						"The enemy is being sheltered by a massive structure. " + "You cannot penetrate its defenses.");

				getBoard().setPieceSelected(false);
				fromTile = null;
				return false;
			}

			if (fromTile.getPiece().getLEAGUE() == League.EAGLE && tile.getTileType().equals("Obstacle")
					&& tile.getTer().territoryName().equals("Shark")) {
				Utilities.infoAlert("Invalid action",
						"The enemy is being sheltered by a massive structure. " + "You cannot penetrate its defenses.");

				getBoard().setPieceSelected(false);
				fromTile = null;
				return false;
			}
			return tryAttack(fromTile);
		}

		// if the target is a valid move
		else if (getBoard().getMovableTiles().contains(tile) && tile.getPiece() == null && fromTile != tile) {
			// check for obstacle

			// obstacle in enemy territory
			if (fromTile.getPiece().getLEAGUE() == League.SHARK && tile.getTileType().equals("Obstacle")
					&& tile.getTer().territoryName().equals("Eagle")) {
				getBoard().setPieceSelected(false);
				fromTile = null;
				return false;
			}

			if (fromTile.getPiece().getLEAGUE() == League.EAGLE && tile.getTileType().equals("Obstacle")
					&& tile.getTer().territoryName().equals("Shark")) {
				getBoard().setPieceSelected(false);
				fromTile = null;
				return false;
			}

			// obstacle in own territory
			if (fromTile.getPiece().getLEAGUE() == League.SHARK && tile.getTileType().equals("Obstacle")
					&& tile.getTer().territoryName().equals("Shark")) {
				return movePiece(fromTile);
			}

			if (fromTile.getPiece().getLEAGUE() == League.EAGLE && tile.getTileType().equals("Obstacle")
					&& tile.getTer().territoryName().equals("Eagle")) {
				return movePiece(fromTile);
			}
			// no obstacle
			if (fromTile.getPiece().getLEAGUE() == League.SHARK && tile.getTileType().equals("Obstacle") == false) {
				return movePiece(fromTile);
			}

			if (fromTile.getPiece().getLEAGUE() == League.EAGLE && tile.getTileType().equals("Obstacle") == false) {
				return movePiece(fromTile);
			}
		}

		if (tile.getPiece() != null || fromTile == tile) {
			getBoard().setPieceSelected(false);
			fromTile = null;
			return false;
		}
		return false;
	}

	// try to attack the enemy piece on the tile that the current piece wants to
	// move to
	private boolean tryAttack(Tile fromTile) {
		int size = getBoard().getTiles().length;

		// if attacker is on power-up tile
		if (fromTile.getTileType().equals("PowerUp")) {
			if (fromTile.getPiece().getLEAGUE() == League.SHARK && fromTile.getTer().territoryName().equals("Shark")) {
				fromTile.getPiece().setPower(fromTile.getPiece().getPower() + 1);
			}

			if (fromTile.getPiece().getLEAGUE() == League.EAGLE && fromTile.getTer().territoryName().equals("Eagle")) {
				fromTile.getPiece().setPower(fromTile.getPiece().getPower() + 1);
			} else if (fromTile.getTer().territoryName().equals("Neutral")) {
				fromTile.getPiece().setPower(fromTile.getPiece().getPower() + 1);
			}

		}
		// if the defender is on power-up tile
		if (tile.getTileType().equals("PowerUp")) {
			if (tile.getPiece().getLEAGUE() == League.SHARK && tile.getTer().territoryName().equals("Shark")) {
				tile.getPiece().setPower(tile.getPiece().getPower() + 1);
			}

			if (tile.getPiece().getLEAGUE() == League.EAGLE && tile.getTer().territoryName().equals("Eagle")) {
				tile.getPiece().setPower(tile.getPiece().getPower() + 1);
			} else if (tile.getTer().territoryName().equals("Neutral")) {
				tile.getPiece().setPower(tile.getPiece().getPower() + 1);
			}

		}

		if (fromTile.getPiece().getPower() >= tile.getPiece().getPower()) {
			if (tile.getPiece().getTYPE() == PieceType.FLAG) {
				fromTile.getPiece().setHasEnemyFlag(true);
				Utilities.infoAlert("Enemy Flag Captured!", "Get that piece with the flag back to your territory quickly!");
			} else {
				if (tile.getPiece().getHasEnemyFlag() == true) {
					Utilities.infoAlert("Flag Recaptured!", "Enemy piece carrying your flag has been killed.\nYour flag has respawned in your territory.");

					// generate the dropped flag at its original position
					// or generate the dropped flag on first available tile on first or last row
					// if the default position has a piece on it
					if (tile.getPiece().getLEAGUE() == League.EAGLE) {
						if (getBoard().getTiles()[size - 1][(size / 2) - 1].getPiece() == null) {
							getBoard().getTiles()[size - 1][(size / 2) - 1].setPiece(new SharkFlag());
						} else {
							for (int sharkCol = 0; sharkCol < getBoard().getTiles().length; sharkCol++) {
								if (getBoard().getTiles()[size - 1][sharkCol].getPiece() == null) {
									getBoard().getTiles()[size - 1][sharkCol].setPiece(new SharkFlag());
									break;
								}
							}
						}
					} else {
						if (getBoard().getTiles()[0][size / 2].getPiece() == null) {
							getBoard().getTiles()[0][size / 2].setPiece(new EagleFlag());
						} else {
							for (int eagleCol = 0; eagleCol < getBoard().getTiles().length; eagleCol++) {
								if (getBoard().getTiles()[0][eagleCol].getPiece() == null) {
									getBoard().getTiles()[0][eagleCol].setPiece(new EagleFlag());
									break;
								}
							}
						}
					}
				}
			}
			return movePiece(fromTile);
		} else {
			Utilities.infoAlert("Invalid action", "Enemy power level higher at level " + tile.getPiece().getPower()
					+ ". " + "\nYour current piece is not able to defeat it and has cowered back in fear.");
		}
		return false;
	}

	// move the piece to new position
	private boolean movePiece(Tile fromTile) {
		getBoard().setToTile(tile);
		makeBackup();

		tile.setPiece(fromTile.getPiece());

		// League wins the game if the piece carrying the flag returns to its territory
		if (tile.getPiece().getHasEnemyFlag() == true && tile.getPiece().getLEAGUE().toString().toLowerCase()
				.equals(tile.getTer().territoryName().toLowerCase())) {
			Game.getInstance().setGameOver(true);
		}

		// reset the old position
		fromTile.setPiece(null);
		getBoard().setFromTile(null);

		// reset the selection
		getBoard().setPieceSelected(false);

		// fromTile = null;
		getBoard().setToTile(null);

		return true;
	}
}