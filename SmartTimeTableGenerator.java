
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class SmartTimetableGenerator extends JFrame {

    // Application colors
    Color cream = new Color(248, 247, 239);
    Color sage = new Color(190, 211, 190);
    Color darkGreen = new Color(48, 78, 65);
    Color lightGreen = new Color(230, 239, 228);
    Color white = Color.WHITE;
    Color grey = new Color(110, 120, 110);

    // Main screen layout
    CardLayout cardLayout = new CardLayout();
    JPanel contentPanel = new JPanel(cardLayout);

    String[] days = {
        "Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday"
    };

    String[] periods = {
        "Period 1", "Period 2", "Period 3",
        "Period 4", "Period 5", "Period 6"
    };

    public SmartTimetableGenerator() {

        setTitle("Smart Timetable Generator");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(cream);
        setLayout(new BorderLayout());

        // Create sidebar
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        // Create main content pages
        contentPanel.setBackground(cream);

        contentPanel.add(createDashboard(), "Dashboard");
        contentPanel.add(createPage("Subjects",
            "Manage your academic subjects here."), "Subjects");

        contentPanel.add(createPage("Faculty",
            "Manage your professor details here."), "Faculty");

        contentPanel.add(createPage("Classrooms",
            "Manage your classroom details here."), "Classrooms");

        contentPanel.add(createPage("Timetable",
            "Your weekly timetable will appear here."), "Timetable");

        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "Dashboard");

        setVisible(true);
    }

    // SIDEBAR
    JPanel createSidebar() {

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(235, 750));
        sidebar.setBackground(darkGreen);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        sidebar.setBorder(new EmptyBorder(25, 15, 20, 15));

        JLabel logo = new JLabel("SMART");
        logo.setFont(new Font("Arial", Font.BOLD, 27));
        logo.setForeground(white);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel logo2 = new JLabel("TIMETABLE");
        logo2.setFont(new Font("Arial", Font.BOLD, 20));
        logo2.setForeground(sage);
        logo2.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(logo2);

        sidebar.add(Box.createVerticalStrut(45));

        JLabel menu = new JLabel("MAIN MENU");
        menu.setFont(new Font("Arial", Font.BOLD, 12));
        menu.setForeground(sage);
        menu.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(menu);
        sidebar.add(Box.createVerticalStrut(15));

        addMenuButton(sidebar, "Dashboard", "Dashboard");
        addMenuButton(sidebar, "Subjects", "Subjects");
        addMenuButton(sidebar, "Faculty", "Faculty");
        addMenuButton(sidebar, "Classrooms", "Classrooms");
        addMenuButton(sidebar, "Timetable", "Timetable");

        sidebar.add(Box.createVerticalGlue());

        JLabel footer = new JLabel("JAVA SWING PROJECT");
        footer.setForeground(sage);
        footer.setFont(new Font("Arial", Font.PLAIN, 11));
        footer.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(footer);

        return sidebar;
    }

    // SIDEBAR BUTTONS
    void addMenuButton(JPanel sidebar,
                       String text, String page) {

        JButton button = new JButton(text);

        button.setMaximumSize(new Dimension(205, 43));
        button.setPreferredSize(new Dimension(205, 43));

        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(white);
        button.setBackground(darkGreen);

        button.setBorder(new EmptyBorder(10, 15, 10, 10));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> {
            cardLayout.show(contentPanel, page);
        });

        sidebar.add(button);
        sidebar.add(Box.createVerticalStrut(9));
    }

    // DASHBOARD
    JPanel createDashboard() {

        JPanel dashboard = new JPanel(new BorderLayout(20, 20));
        dashboard.setBackground(cream);

        dashboard.setBorder(
            new EmptyBorder(25, 25, 25, 25)
        );

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(cream);

        JPanel welcome = new JPanel();
        welcome.setLayout(new BoxLayout(welcome, BoxLayout.Y_AXIS));
        welcome.setBackground(cream);

        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(darkGreen);

        JLabel subtitle = new JLabel(
            "Welcome! Manage your weekly academic schedule."
        );

        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(grey);

        welcome.add(title);
        welcome.add(Box.createVerticalStrut(7));
        welcome.add(subtitle);

        JLabel tag = new JLabel("SMART PLANNER");
        tag.setFont(new Font("Arial", Font.BOLD, 12));
        tag.setForeground(darkGreen);
        tag.setOpaque(true);
        tag.setBackground(sage);
        tag.setBorder(new EmptyBorder(10, 14, 10, 14));

        header.add(welcome, BorderLayout.WEST);
        header.add(tag, BorderLayout.EAST);

        dashboard.add(header, BorderLayout.NORTH);

        // CENTER CONTENT
        JPanel center = new JPanel();
        center.setBackground(cream);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        // SUMMARY CARDS
        JPanel cards = new JPanel(new GridLayout(1, 4, 15, 0));
        cards.setBackground(cream);

        cards.add(createSummaryCard("SUBJECTS", "0",
            "Total subjects", new Color(225, 238, 222)));

        cards.add(createSummaryCard("FACULTY", "0",
            "Total professors", new Color(238, 232, 215)));

        cards.add(createSummaryCard("CLASSROOMS", "0",
            "Available rooms", new Color(225, 232, 239)));

        cards.add(createSummaryCard("PERIODS", "36",
            "Weekly time slots", new Color(239, 229, 221)));

        center.add(cards);
        center.add(Box.createVerticalStrut(25));

        // TIMETABLE HEADING
        JPanel tableHeading = new JPanel(new BorderLayout());
        tableHeading.setBackground(cream);

        JLabel weekly = new JLabel("Weekly Timetable");
        weekly.setFont(new Font("Arial", Font.BOLD, 21));
        weekly.setForeground(darkGreen);

        JLabel timing = new JLabel("Monday - Saturday");
        timing.setFont(new Font("Arial", Font.PLAIN, 13));
        timing.setForeground(grey);

        tableHeading.add(weekly, BorderLayout.WEST);
        tableHeading.add(timing, BorderLayout.EAST);

        center.add(tableHeading);
        center.add(Box.createVerticalStrut(15));

        // TIMETABLE GRID
        JPanel timetable = createTimetableGrid();

        center.add(timetable);

        dashboard.add(center, BorderLayout.CENTER);

        // FOOTER
        JLabel footer = new JLabel(
            "Smart Timetable Generator | Designed using Java Swing"
        );

        footer.setFont(new Font("Arial", Font.PLAIN, 11));
        footer.setForeground(grey);

        dashboard.add(footer, BorderLayout.SOUTH);

        return dashboard;
    }

    // SUMMARY CARD
    JPanel createSummaryCard(String heading,
                             String number,
                             String description,
                             Color color) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.setBackground(color);

        card.setBorder(new CompoundBorder(
            new LineBorder(sage, 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel name = new JLabel(heading);
        name.setFont(new Font("Arial", Font.BOLD, 12));
        name.setForeground(darkGreen);

        JLabel value = new JLabel(number);
        value.setFont(new Font("Arial", Font.BOLD, 29));
        value.setForeground(darkGreen);

        JLabel info = new JLabel(description);
        info.setFont(new Font("Arial", Font.PLAIN, 11));
        info.setForeground(grey);

        card.add(name);
        card.add(Box.createVerticalStrut(10));
        card.add(value);
        card.add(Box.createVerticalStrut(5));
        card.add(info);

        return card;
    }

    // TIMETABLE GRID
    JPanel createTimetableGrid() {

        JPanel grid = new JPanel(
            new GridLayout(7, 7, 5, 5)
        );

        grid.setBackground(cream);

        grid.setBorder(new EmptyBorder(5, 0, 5, 0));

        addTableCell(grid, "DAY", darkGreen, white);

        for (String period : periods) {
            addTableCell(grid, period, darkGreen, white);
        }

        for (String day : days) {

            addTableCell(grid, day, sage, darkGreen);

            for (int i = 0; i < 6; i++) {
                addTableCell(grid, "Free", white, grey);
            }
        }

        return grid;
    }

    // TABLE CELL
    void addTableCell(JPanel grid, String text,
                      Color background, Color foreground) {

        JLabel cell = new JLabel(text, SwingConstants.CENTER);

        cell.setOpaque(true);
        cell.setBackground(background);
        cell.setForeground(foreground);

        cell.setFont(new Font("Arial", Font.BOLD, 11));

        cell.setBorder(
            new LineBorder(new Color(220, 225, 215), 1)
        );

        grid.add(cell);
    }

    // OTHER PAGES
    JPanel createPage(String heading, String description) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(cream);
        panel.setBorder(new EmptyBorder(35, 35, 35, 35));

        JLabel title = new JLabel(heading);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(darkGreen);

        JLabel info = new JLabel(description);
        info.setFont(new Font("Arial", Font.PLAIN, 15));
        info.setForeground(grey);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(cream);

        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        info.setAlignmentX(Component.LEFT_ALIGNMENT);

        content.add(title);
        content.add(Box.createVerticalStrut(15));
        content.add(info);

        panel.add(content, BorderLayout.NORTH);

        return panel;
    }

    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new SmartTimetableGenerator();
        });
    }
}
