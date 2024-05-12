package CustomCompontents;

import java.awt.Color;

public class CustomColors {
    public Color getColor(String colorString) {
        switch (colorString) {
            case "Green":
                return new Color(0, 40, 20);
            case "Yellow":
                return new Color(80, 95, 27);
            default:
                return Color.BLACK; // Standardfarbe, falls kein Übereinstimmung gefunden wird
        }
    }
}
