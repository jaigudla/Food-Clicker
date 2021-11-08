import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Game extends JPanel {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    private BufferedImage image;
    private Graphics g;
    private Timer timer;

    private ImageIcon burger;
    private ImageIcon pizza;
    private ImageIcon taco;
    private static Player p;
    private static int iconWidth = WIDTH/10;

    public Game() {

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();

        //Image icons
        burger = new ImageIcon("Food Icons/burger.png");
        pizza = new ImageIcon("Food Icons/pizza.png");
        taco = new ImageIcon("Food Icons/taco.png");

        p = new Player();

        timer = new Timer(1, new TimerListener());
        timer.start();
        addMouseListener(new Mouse());

    }

    private class Mouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {


        }

        @Override
        public void mousePressed(MouseEvent e) {

            //lowers the width and height of the image icon  when pressed
            if (((e.getX() >= 3 * WIDTH/6 - WIDTH/20 && e.getX() <= 3 * WIDTH/6 - WIDTH/20 + WIDTH/10) && (e.getY() > HEIGHT/2 - WIDTH/20 && e.getY() < HEIGHT/2 - WIDTH/20 + WIDTH/10)) && e.getButton() == MouseEvent.BUTTON1) {
                iconWidth -= 10;
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {

            //The following statements are used to check if the player has clicked any of the upgrade buttons

            if (((e.getX() >= 3 * WIDTH/6 - WIDTH/20 && e.getX() <= 3 * WIDTH/6 - WIDTH/20 + WIDTH/10) && (e.getY() > HEIGHT/2 - WIDTH/20 && e.getY() < HEIGHT/2 - WIDTH/20 + WIDTH/10)) && !e.isMetaDown()) {
                p.setPoints(p.getPoints() + 1);
                iconWidth += 10;
            }

            if (e.getX() >= WIDTH/3 - 500/2 - WIDTH/6 && e.getX() <= WIDTH/3 - 500/2 - WIDTH/6 + 500 && e.getY() >= 100 && e.getY() <= 170 && !e.isMetaDown()) {
                p.buyUpgrade("CHEF");
            }

            if (e.getX() >= WIDTH/3 - 500/2 - WIDTH/6 && e.getX() <= WIDTH/3 - 500/2 - WIDTH/6 + 500 && e.getY() >= 190 && e.getY() <= 240 && !e.isMetaDown()) {
                p.buyUpgrade("FOOD_TRUCK");
            }

            if (e.getX() >= WIDTH/3 - 500/2 - WIDTH/6 && e.getX() <= WIDTH/3 - 500/2 - WIDTH/6 + 500 && e.getY() >= 280 && e.getY() <= 350 && !e.isMetaDown()) {
                p.buyUpgrade("STORE");
            }

            if (e.getX() >= WIDTH/3 - 500/2 - WIDTH/6 && e.getX() <= WIDTH/3 - 500/2 - WIDTH/6 + 500 && e.getY() >= 370 && e.getY() <= 440 && !e.isMetaDown()) {
                p.buyUpgrade("MARKET");
            }

            if (e.getX() >= WIDTH/3 - 500/2 - WIDTH/6 && e.getX() <= WIDTH/3 - 500/2 - WIDTH/6 + 500 && e.getY() >= 460 && e.getY() <= 530 && !e.isMetaDown()) {
                p.buyUpgrade("SUPERMARKET");
            }

            if (e.getX() >= WIDTH/3 - 500/2 - WIDTH/6 && e.getX() <= WIDTH/3 - 500/2 - WIDTH/6 + 500 && e.getY() >= 550 && e.getY() <= 620 && !e.isMetaDown()) {
                p.buyUpgrade("FACTORY");
            }

            if (e.getX() >= WIDTH/3 - 500/2 - WIDTH/6 && e.getX() <= WIDTH/3 - 500/2 - WIDTH/6 + 500 && e.getY() >= 640 && e.getY() <= 710 && !e.isMetaDown()) {
                p.buyUpgrade("INDUSTRY");
            }

            if (e.getX() >= WIDTH/3 - 500/2 - WIDTH/6 && e.getX() <= WIDTH/3 - 500/2 - WIDTH/6 + 500 && e.getY() >= 820 && e.getY() <= 890 && !e.isMetaDown()) {
                p.prestige();
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {


        }

        @Override
        public void mouseExited(MouseEvent e) {


        }

    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            p.setCPS(0);

            //background
            g.setColor(new Color(251, 167, 12));
            g.fillRect(0, 0, WIDTH/3, HEIGHT);
            g.setColor(new Color(247, 219, 198));
            g.fillRect(WIDTH/3, 0, WIDTH/3, HEIGHT);
            g.setColor(new Color(251, 123, 12));
            g.fillRect(2 * WIDTH/3, 0, WIDTH/3, HEIGHT);

            //The boxes around the upgrades
            g.setColor(new Color(150, 35, 205));
            g.drawRect(WIDTH/3 - 500/2 - WIDTH/6, 100, 500, 70);
            g.drawRect(WIDTH/3 - 500/2 - WIDTH/6, 190, 500, 70);
            g.drawRect(WIDTH/3 - 500/2 - WIDTH/6, 280, 500, 70);
            g.drawRect(WIDTH/3 - 500/2 - WIDTH/6, 370, 500, 70);
            g.drawRect(WIDTH/3 - 500/2 - WIDTH/6, 460, 500, 70);
            g.drawRect(WIDTH/3 - 500/2 - WIDTH/6, 550, 500, 70);
            g.drawRect(WIDTH/3 - 500/2 - WIDTH/6, 640, 500, 70);
            g.drawRect(WIDTH/3 - 500/2 - WIDTH/6, 820, 500, 70);

            //Sets color and font for the strings
            g.setColor(new Color(150, 35, 205));
            g.setFont(new Font("Agency FB", Font.BOLD, 25));

            //display of the time
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy, HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            g.drawString(dtf.format(now), WIDTH-250, HEIGHT-50);

            //displaying the amount of upgrades bought
            g.drawString("CHEF: " + p.getNumUpgrades().get("CHEF"), 2*WIDTH/3 + 300, 150);
            g.drawString("FOOD TRUCK: " + p.getNumUpgrades().get("FOOD_TRUCK"), 2*WIDTH/3 + 300, 240);
            g.drawString("STORE: " + p.getNumUpgrades().get("STORE"), 2*WIDTH/3 + 300, 330);
            g.drawString("MARKET: " + p.getNumUpgrades().get("MARKET"), 2*WIDTH/3 + 300, 420);
            g.drawString("SUPERMARKET: " + p.getNumUpgrades().get("SUPERMARKET"), 2*WIDTH/3 + 300, 510);
            g.drawString("FACTORY: " + p.getNumUpgrades().get("FACTORY"), 2*WIDTH/3 + 300, 600);
            g.drawString("INDUSTRY: " + p.getNumUpgrades().get("INDUSTRY"), 2*WIDTH/3 + 300, 690);

            //displaying the upgrades and their current prices
            g.drawString("CHEF: " + p.getCurrentPrices().get("CHEF"), WIDTH/3 - 500/2 - WIDTH/6 + 20, 150);
            g.drawString("FOOD TRUCK: " + p.getCurrentPrices().get("FOOD_TRUCK"), WIDTH/3 - 500/2 - WIDTH/6 + 20, 240);
            g.drawString("STORE: " + p.getCurrentPrices().get("STORE"), WIDTH/3 - 500/2 - WIDTH/6 + 20, 330);
            g.drawString("MARKET: " + p.getCurrentPrices().get("MARKET"), WIDTH/3 - 500/2 - WIDTH/6 + 20, 420);
            g.drawString("SUPERMARKET: " + p.getCurrentPrices().get("SUPERMARKET"), WIDTH/3 - 500/2 - WIDTH/6 + 20, 510);
            g.drawString("FACTORY: " + p.getCurrentPrices().get("FACTORY"), WIDTH/3 - 500/2 - WIDTH/6 + 20, 600);
            g.drawString("INDUSTRY: " + p.getCurrentPrices().get("INDUSTRY"), WIDTH/3 - 500/2 - WIDTH/6 + 20, 690);

            //displays the prestige button
            g.drawString("PRESTIGE", WIDTH/3 - 500/2 - WIDTH/5 + 250, 860);

            //displays the image icon
            if (p.getFood().equals("BURGER")) {
                g.drawImage(burger.getImage(), 3 * WIDTH/6 - WIDTH/20, HEIGHT/2 - WIDTH/20, iconWidth, iconWidth, null);
            } else if (p.getFood().equals("PIZZA")) {
                g.drawImage(pizza.getImage(), 3 * WIDTH/6 - WIDTH/20, HEIGHT/2 - WIDTH/20, iconWidth, iconWidth, null);
            } else {
                g.drawImage(taco.getImage(), 3 * WIDTH/6 - WIDTH/20, HEIGHT/2 - WIDTH/20, iconWidth, iconWidth, null);
            }

            //sets the color for the CPS String and Points String
            g.setColor(Color.BLUE);

            //displays the Points
            g.drawString("Points: " + (int)p.getPoints(), WIDTH/3 + WIDTH/7 - WIDTH/80, 30);

            //gets all strings in arraylist getUpgrades()
            //then sets the amount of the CPS upgrade to original CPS
            for (String up : p.getUpgrades()) {
                p.setCPS(p.getCPS() + p.getUpgradesCPS().get(up));
            }

            //displays the cps
            g.drawString("CPS: " + (int)(p.getCPS() * 100), 2*WIDTH/3 + 300, 30);

            //sets the points to the original points + cps
            p.setPoints(p.getPoints() + p.getCPS());

            //uses function to upgrade the prices
            p.updateCurrentPrices();

            //repaints panel
            repaint();

        }

    }

    //draws panel
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clicking game"); //creates frame called Clicking Game
        frame.setSize(WIDTH, HEIGHT); //sets the size to (width, height)
        frame.setLocationRelativeTo(null); //sets the frame to start at the middle of the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the defualt close operation to exit when clicked
        frame.setContentPane(new Game()); //sets the main component
        frame.setVisible(true); //sets the frame to visible
    }

}
