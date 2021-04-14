package view;

import java.util.HashMap;
import java.util.Map;

import model.piece.Piece.PieceType;
import model.piece.Piece;

public class PieceDisplay {

	private static final Map<PieceType, String> SYMBOLS = new HashMap<>();
	private static final Map<String, String> SYMBOLS_TEMP = new HashMap<>();

	// add symbols to the piece types as representations on the board
	static {
		// symbols from https://graphemica.com/
		SYMBOLS.put(PieceType.CROWN_EAGLE, "\uD83E\uDD85");
		SYMBOLS.put(PieceType.GOLDEN_EAGLE, "\uD83D\uDC26");
		SYMBOLS.put(PieceType.HARPY_EAGLE, "\uD83D\uDC14");
		SYMBOLS.put(PieceType.GREATWHITE_SHARK, "\uD83E\uDD88");
		SYMBOLS.put(PieceType.HAMMERHEAD_SHARK, "\uD83D\uDC1F");
		SYMBOLS.put(PieceType.TIGER_SHARK, "\uD83D\uDC2C");
		SYMBOLS.put(PieceType.FLAG, "\uD83C\uDFF4");

		SYMBOLS_TEMP.put("CROWN_EAGLE", "\uD83E\uDD85");
		SYMBOLS_TEMP.put("GOLDEN_EAGLE", "\uD83D\uDC26");
		SYMBOLS_TEMP.put("HARPY_EAGLE", "\uD83D\uDC14");
		SYMBOLS_TEMP.put("GREATWHITE_SHARK", "\uD83E\uDD88");
		SYMBOLS_TEMP.put("HAMMERHEAD_SHARK", "\uD83D\uDC1F");
		SYMBOLS_TEMP.put("TIGER_SHARK", "\uD83D\uDC2C");
		SYMBOLS_TEMP.put("FLAG", "\uD83C\uDFF4");
	}

	public static String getPieceDisplay(Piece piece) {
		return piece == null ? "" : SYMBOLS.get(piece.getTYPE());
	}

	public static String getPieceDisplay_temp(String text) {
		return SYMBOLS_TEMP.get(text);
	}
}
