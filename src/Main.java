import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Xsi0Game game = new Xsi0Game();
            game.setVisible(true);
        });
    }
}