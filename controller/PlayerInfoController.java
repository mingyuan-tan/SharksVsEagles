package controller;

import javafx.event.ActionEvent;
import model.Game;
import model.piece.CrownEagle;
import model.piece.GoldenEagle;
import model.piece.GreatWhiteShark;
import model.piece.HammerheadShark;
import model.piece.HarpyEagle;
import model.piece.NormalMode;
import model.piece.Piece;
import model.piece.ScoutMode;
import model.piece.ShieldMode;
import model.piece.TigerShark;
import model.piece.Piece.PieceType;
import model.player.Player;
import util.Utilities;
import view.GameView;
import view.PlayerInfoView;

public class PlayerInfoController {

	// Grabs info from model and view
	private final Player model;
	private final PlayerInfoView view;

	public PlayerInfoController(Player model) {
		this.model = model;
		this.view = new PlayerInfoView(this, model);
	}

	public PlayerInfoView getView() {
		return view;
	}

	// decorates the selected piece with Normal mode (Decorator Pattern)
	public void onNormalModeButtonClicked(ActionEvent e) {
		var fromTile = Game.getInstance().getBoard().getFromTile();

		if (fromTile != null) {
			var pieceSelected = fromTile.getPiece();
			var pieceSelectedHasFlag = pieceSelected.getHasEnemyFlag();
			var pieceSelectedSkillUsed = pieceSelected.getSkillUsed();
			Piece freshPiece = freshPieceCreator(pieceSelected);

			fromTile.setPiece(new NormalMode(freshPiece));
			fromTile.getPiece().setHasEnemyFlag(pieceSelectedHasFlag);

			if (pieceSelectedSkillUsed == true) {
				fromTile.getPiece().setSkillUsed();
				fromTile.getPiece().useSkill(fromTile.getPiece().getPower(), fromTile.getPiece().getMovement());
			}
			
			Game.getInstance().getBoard().setSelectedPiece(fromTile.getPiece());

			var gameView = ((GameView) view.getParent());
			gameView.centerGrid.refresh();

			Utilities.infoAlert("Normal Mode Activated",
					"Power and Movement is returned to its original levels. \nYou can now attack.");
		}
	}

	// decorates the selected piece with Scout mode (Decorator Pattern)
	public void onScoutModeButtonClicked(ActionEvent e) {
		var fromTile = Game.getInstance().getBoard().getFromTile();

		if (fromTile != null) {
			var pieceSelected = fromTile.getPiece();
			var pieceSelectedHasFlag = pieceSelected.getHasEnemyFlag();
			var pieceSelectedSkillUsed = pieceSelected.getSkillUsed();
			Piece freshPiece = freshPieceCreator(pieceSelected);

			fromTile.setPiece(new ScoutMode(freshPiece));
			fromTile.getPiece().setHasEnemyFlag(pieceSelectedHasFlag);

			if (pieceSelectedSkillUsed == true) {
				fromTile.getPiece().setSkillUsed();
				fromTile.getPiece().useSkill(fromTile.getPiece().getPower(), fromTile.getPiece().getMovement());
			}

			Game.getInstance().getBoard().setSelectedPiece(fromTile.getPiece());

			var gameView = ((GameView) view.getParent());
			gameView.centerGrid.refresh();

			Utilities.infoAlert("Scout Mode Activated", "Movement is increased by 1 level, but you cannot attack.");
		}
	}

	// decorates the selected piece with Shield mode (Decorator Pattern)
	public void onShieldModeButtonClicked(ActionEvent e) {
		var fromTile = Game.getInstance().getBoard().getFromTile();

		if (fromTile != null) {
			var pieceSelected = fromTile.getPiece();
			var pieceSelectedHasFlag = pieceSelected.getHasEnemyFlag();
			var pieceSelectedSkillUsed = pieceSelected.getSkillUsed();
			Piece freshPiece = freshPieceCreator(pieceSelected);

			fromTile.setPiece(new ShieldMode(freshPiece));
			fromTile.getPiece().setHasEnemyFlag(pieceSelectedHasFlag);

			if (pieceSelectedSkillUsed == true) {
				fromTile.getPiece().setSkillUsed();
				fromTile.getPiece().useSkill(fromTile.getPiece().getPower(), fromTile.getPiece().getMovement());
			}

			Game.getInstance().getBoard().setSelectedPiece(fromTile.getPiece());

			var gameView = ((GameView) view.getParent());
			gameView.centerGrid.refresh();

			Utilities.infoAlert("Shield Mode Activated", "Power is increased by 1 level, but you cannot attack.");
		}

	}

	// use the piece's unique skill
	public void onSkillButtonClicked(ActionEvent e) {
		var fromTile = Game.getInstance().getBoard().getFromTile();

		if (fromTile != null) {
			if (fromTile.getPiece().getSkillUsed() == false) {
				fromTile.getPiece().useSkill(fromTile.getPiece().getPower(), fromTile.getPiece().getMovement());
			} else {

				Utilities.infoAlert("Invalid action",
						"This piece has already used its skill. \nA piece can only activate a skill once per game.");
			}
			
			Game.getInstance().getBoard().setSelectedPiece(fromTile.getPiece());

			var gameView = ((GameView) view.getParent());
			gameView.centerGrid.refresh();
		}
	}

	// creates a fresh piece of the same type
	private Piece freshPieceCreator(Piece pieceSelected) {
		Piece freshPiece = null;

		if (pieceSelected.getTYPE() == PieceType.CROWN_EAGLE) {
			freshPiece = new CrownEagle();
		} else if (pieceSelected.getTYPE() == PieceType.GOLDEN_EAGLE) {
			freshPiece = new GoldenEagle();
		} else if (pieceSelected.getTYPE() == PieceType.HARPY_EAGLE) {
			freshPiece = new HarpyEagle();
		} else if (pieceSelected.getTYPE() == PieceType.GREATWHITE_SHARK) {
			freshPiece = new GreatWhiteShark();
		} else if (pieceSelected.getTYPE() == PieceType.HAMMERHEAD_SHARK) {
			freshPiece = new HammerheadShark();
		} else if (pieceSelected.getTYPE() == PieceType.TIGER_SHARK) {
			freshPiece = new TigerShark();
		}

		return freshPiece;
	}
}
