package ui;
	

	import model.Comment;
	import model.CulturalSite;
	import model.HeritageSite;
	import model.Location;
	import model.NaturalSite;
	import service.CommentService;
	import service.TourismManager;

	import javax.swing.*;
	import javax.swing.border.EmptyBorder;
	import javax.swing.event.DocumentEvent;
	import javax.swing.event.DocumentListener;
	import java.awt.*;

	public class MainGUI  extends JFrame {

	    private TourismManager manager;
	    private CommentService commentService;

	    private DefaultListModel<Location> listModel;
	    private JList<Location> locationList;

	    private JTextArea detailsArea;

	    private DefaultListModel<Comment> commentListModel;
	    private JList<Comment> commentList;

	    private JTextField searchField;
	    private JComboBox<String> categoryFilter;

	    private JTextField authorField;
	    private JTextField commentField;

	    private static final Color BG_COLOR = new Color(245, 245, 240);
	    private static final Color ACCENT_COLOR = new Color(0, 102, 51);

	    public MainGUI() {
	        super("Ethiopian Tourism Management System");

	        manager = new TourismManager();
	        commentService = new CommentService();
	        loadSampleData();

	        listModel = new DefaultListModel<>();
	        commentListModel = new DefaultListModel<>();

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(1000, 650);
	        setMinimumSize(new Dimension(850, 550));
	        setLocationRelativeTo(null);

	        setLayout(new BorderLayout(8, 8));
	        getContentPane().setBackground(BG_COLOR);

	        add(buildHeaderPanel(), BorderLayout.NORTH);
	        add(buildCenterPanel(), BorderLayout.CENTER);
	        add(buildToolbarPanel(), BorderLayout.SOUTH);

	        filterList();
	    }

	    // Pre-populate with the same sample data used in the console version
	    private void loadSampleData() {
	        manager.addLocation(new HeritageSite(
	                "Lalibela", "Amhara",
	                "Rock-hewn churches of Lalibela",
	                "Built in the 12th century under King Lalibela",
	                "One of Ethiopia's most important religious sites",
	                "Visit during religious festivals for best experience"));

	        manager.addLocation(new HeritageSite(
	                "Axum Obelisks", "Tigray",
	                "Ancient stone obelisks",
	                "Remains of the Axumite Empire over 2000 years old",
	                "Symbol of ancient Ethiopian civilization",
	                "Visit archaeological museum nearby"));

	        manager.addLocation(new HeritageSite(
	                "Harar Jugol", "Harar",
	                "Walled historic city",
	                "Developed between 7th-16th century as trade hub",
	                "Important Islamic cultural heritage site",
	                "Walk inside the old city to explore fully"));

	        manager.addLocation(new NaturalSite(
	                "Simien Mountains", "Amhara",
	                "Mountain range with dramatic landscapes",
	                "Formed by volcanic activity millions of years ago",
	                "Home to rare wildlife like Gelada baboons",
	                "Best for hiking and trekking"));

	        manager.addLocation(new NaturalSite(
	                "Sof Omar Caves", "Oromia",
	                "Huge limestone cave system",
	                "Formed by Weyib River over thousands of years",
	                "One of Africa's largest cave systems",
	                "Bring a guide for safe exploration"));

	        manager.addLocation(new CulturalSite(
	                "Timkat Festival Grounds", "Addis Ababa",
	                "Site of the annual Timkat celebration",
	                "Celebrated for centuries to mark Ethiopian Epiphany",
	                "One of Ethiopia's most colorful cultural events",
	                "Arrive early to get a good viewing spot"));
	    }

	    // ---------------------------------------------------------------
	    // Layout building
	    // ---------------------------------------------------------------

	    private JPanel buildHeaderPanel() {
	        JPanel panel = new JPanel(new BorderLayout());
	        panel.setBackground(ACCENT_COLOR);
	        panel.setBorder(new EmptyBorder(12, 16, 12, 16));

	        JLabel title = new JLabel("Ethiopian Tourism Management System");
	        title.setFont(new Font("SansSerif", Font.BOLD, 20));
	        title.setForeground(Color.WHITE);
	        panel.add(title, BorderLayout.WEST);

	        JLabel subtitle = new JLabel("Heritage, Natural & Cultural Sites");
	        subtitle.setFont(new Font("SansSerif", Font.ITALIC, 12));
	        subtitle.setForeground(new Color(230, 240, 230));
	        panel.add(subtitle, BorderLayout.EAST);

	        return panel;
	    }

	    private JSplitPane buildCenterPanel() {
	        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	        splitPane.setLeftComponent(buildLeftPanel());
	        splitPane.setRightComponent(buildRightPanel());
	        splitPane.setDividerLocation(320);
	        return splitPane;
	    }

	    private JPanel buildLeftPanel() {
	        JPanel panel = new JPanel(new BorderLayout(6, 6));
	        panel.setBorder(new EmptyBorder(10, 10, 10, 5));
	        panel.setBackground(BG_COLOR);

	        JPanel controls = new JPanel();
	        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
	        controls.setBackground(BG_COLOR);

	        searchField = new JTextField();
	        searchField.setBorder(BorderFactory.createTitledBorder("Search by name"));
	        searchField.getDocument().addDocumentListener(new DocumentListener() {
	            public void insertUpdate(DocumentEvent e) { filterList(); }
	            public void removeUpdate(DocumentEvent e) { filterList(); }
	            public void changedUpdate(DocumentEvent e) { filterList(); }
	        });

	        categoryFilter = new JComboBox<>(new String[]{"All", "Historical", "Natural", "Cultural"});
	        categoryFilter.setBorder(BorderFactory.createTitledBorder("Filter by category"));
	        categoryFilter.addActionListener(e -> filterList());

	        controls.add(searchField);
	        controls.add(Box.createVerticalStrut(8));
	        controls.add(categoryFilter);

	        panel.add(controls, BorderLayout.NORTH);

	        locationList = new JList<>(listModel);
	        locationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        locationList.setFont(new Font("SansSerif", Font.PLAIN, 14));
	        locationList.setCellRenderer(new LocationCellRenderer());
	        locationList.addListSelectionListener(e -> {
	            if (!e.getValueIsAdjusting()) {
	                showSelectedDetails();
	            }
	        });

	        JScrollPane scrollPane = new JScrollPane(locationList);
	        scrollPane.setBorder(BorderFactory.createTitledBorder("Locations"));
	        panel.add(scrollPane, BorderLayout.CENTER);

	        return panel;
	    }

	    private JPanel buildRightPanel() {
	        JPanel panel = new JPanel(new BorderLayout(6, 6));
	        panel.setBorder(new EmptyBorder(10, 5, 10, 10));
	        panel.setBackground(BG_COLOR);

	        detailsArea = new JTextArea();
	        detailsArea.setEditable(false);
	        detailsArea.setLineWrap(true);
	        detailsArea.setWrapStyleWord(true);
	        detailsArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
	        detailsArea.setMargin(new Insets(8, 8, 8, 8));
	        detailsArea.setText("Select a location from the list to view details.");

	        JScrollPane detailsScroll = new JScrollPane(detailsArea);
	        detailsScroll.setBorder(BorderFactory.createTitledBorder("Location Details"));

	        JPanel commentsPanel = buildCommentsPanel();

	        JSplitPane verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, detailsScroll, commentsPanel);
	        verticalSplit.setResizeWeight(0.55);
	        verticalSplit.setDividerLocation(260);

	        panel.add(verticalSplit, BorderLayout.CENTER);
	        return panel;
	    }

	    private JPanel buildCommentsPanel() {
	        JPanel panel = new JPanel(new BorderLayout(5, 5));
	        panel.setBackground(BG_COLOR);

	        commentList = new JList<>(commentListModel);
	        commentList.setFont(new Font("SansSerif", Font.PLAIN, 12));
	        JScrollPane commentScroll = new JScrollPane(commentList);
	        commentScroll.setBorder(BorderFactory.createTitledBorder("Visitor Comments"));
	        panel.add(commentScroll, BorderLayout.CENTER);

	        JPanel form = new JPanel(new GridBagLayout());
	        form.setBackground(BG_COLOR);
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(3, 3, 3, 3);
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        authorField = new JTextField(10);
	        commentField = new JTextField(20);
	        JButton addCommentButton = new JButton("Add Comment");
	        addCommentButton.addActionListener(e -> addComment());

	        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
	        form.add(new JLabel("Your name:"), gbc);
	        gbc.gridx = 1; gbc.weightx = 0.4;
	        form.add(authorField, gbc);

	        gbc.gridx = 2; gbc.weightx = 0;
	        form.add(new JLabel("Comment:"), gbc);
	        gbc.gridx = 3; gbc.weightx = 0.6;
	        form.add(commentField, gbc);

	        gbc.gridx = 4; gbc.weightx = 0;
	        form.add(addCommentButton, gbc);

	        panel.add(form, BorderLayout.SOUTH);
	        return panel;
	    }

	    private JPanel buildToolbarPanel() {
	        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
	        panel.setBackground(BG_COLOR);
	        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

	        JButton addButton = new JButton("Add Location");
	        addButton.addActionListener(e -> openAddLocationDialog());

	        JButton deleteButton = new JButton("Delete Selected");
	        deleteButton.addActionListener(e -> deleteSelected());

	        JButton clearFiltersButton = new JButton("Clear Filters");
	        clearFiltersButton.addActionListener(e -> {
	            searchField.setText("");
	            categoryFilter.setSelectedIndex(0);
	            filterList();
	        });

	        panel.add(addButton);
	        panel.add(deleteButton);
	        panel.add(clearFiltersButton);

	        return panel;
	    }

	    // ---------------------------------------------------------------
	    // Behaviour
	    // ---------------------------------------------------------------

	    private void filterList() {
	        String query = searchField == null ? "" : searchField.getText().trim().toLowerCase();
	        String category = categoryFilter == null ? "All" : (String) categoryFilter.getSelectedItem();

	        listModel.clear();
	        for (Location loc : manager.getAllLocations()) {
	            boolean matchesQuery = query.isEmpty() || loc.getName().toLowerCase().contains(query);
	            boolean matchesCategory = category.equals("All") || loc.getCategory().equalsIgnoreCase(category);
	            if (matchesQuery && matchesCategory) {
	                listModel.addElement(loc);
	            }
	        }

	        if (!listModel.isEmpty()) {
	            locationList.setSelectedIndex(0);
	        } else {
	            detailsArea.setText("No locations match your search/filter.");
	            commentListModel.clear();
	        }
	    }

	    private void showSelectedDetails() {
	        Location loc = locationList.getSelectedValue();
	        if (loc == null) {
	            detailsArea.setText("Select a location from the list to view details.");
	            commentListModel.clear();
	            return;
	        }

	        StringBuilder sb = new StringBuilder();
	        sb.append("Name: ").append(loc.getName()).append("\n");
	        sb.append("Region: ").append(loc.getRegion()).append("\n");
	        sb.append("Category: ").append(loc.getCategory()).append("\n\n");
	        sb.append("Description:\n").append(loc.getShortDescription()).append("\n\n");
	        sb.append("History:\n").append(loc.getFullHistory()).append("\n\n");
	        sb.append("Significance:\n").append(loc.getSignificance()).append("\n\n");
	        sb.append("Tourist Tips:\n").append(loc.getTouristTips());

	        detailsArea.setText(sb.toString());
	        detailsArea.setCaretPosition(0);

	        refreshComments(loc.getName());
	    }

	    private void refreshComments(String locationName) {
	        commentListModel.clear();
	        for (Comment c : commentService.getAllComments()) {
	            commentListModel.addElement(c);
	        }
	    }

	    private void addComment() {
	        Location loc = locationList.getSelectedValue();
	        if (loc == null) {
	            JOptionPane.showMessageDialog(this, "Please select a location first.",
	                    "No Location Selected", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        String text = commentField.getText().trim();
	        if (text.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please enter a comment.",
	                    "Empty Comment", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        String author = authorField.getText().trim();
	        commentService.addComment(loc.getName(), author, text);
	        commentField.setText("");
	        refreshComments(loc.getName());
	    }

	    private void deleteSelected() {
	        Location loc = locationList.getSelectedValue();
	        if (loc == null) {
	            JOptionPane.showMessageDialog(this, "Please select a location to delete.",
	                    "No Selection", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        int confirm = JOptionPane.showConfirmDialog(this,
	                "Delete \"" + loc.getName() + "\"? This cannot be undone.",
	                "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

	        if (confirm == JOptionPane.YES_OPTION) {
	            manager.deleteLocationByName(loc.getName());
	            filterList();
	        }
	    }

	    private void openAddLocationDialog() {
	        JDialog dialog = new JDialog(this, "Add New Location", true);
	        dialog.setSize(480, 480);
	        dialog.setLocationRelativeTo(this);
	        dialog.setLayout(new GridBagLayout());

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(6, 10, 6, 10);
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        JTextField nameField = new JTextField(20);
	        JTextField regionField = new JTextField(20);
	        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Historical", "Natural", "Cultural"});
	        JTextField shortDescField = new JTextField(20);
	        JTextArea historyArea = new JTextArea(3, 20);
	        JTextArea significanceArea = new JTextArea(3, 20);
	        JTextArea tipsArea = new JTextArea(3, 20);

	        historyArea.setLineWrap(true);
	        significanceArea.setLineWrap(true);
	        tipsArea.setLineWrap(true);

	        int row = 0;
	        addFormRow(dialog, gbc, row++, "Name:", nameField);
	        addFormRow(dialog, gbc, row++, "Region:", regionField);
	        addFormRow(dialog, gbc, row++, "Category:", categoryBox);
	        addFormRow(dialog, gbc, row++, "Short Description:", shortDescField);
	        addFormRow(dialog, gbc, row++, "Full History:", new JScrollPane(historyArea));
	        addFormRow(dialog, gbc, row++, "Significance:", new JScrollPane(significanceArea));
	        addFormRow(dialog, gbc, row++, "Tourist Tips:", new JScrollPane(tipsArea));

	        JButton saveButton = new JButton("Save");
	        JButton cancelButton = new JButton("Cancel");

	        saveButton.addActionListener(e -> {
	            String name = nameField.getText().trim();
	            String region = regionField.getText().trim();
	            String shortDesc = shortDescField.getText().trim();
	            String history = historyArea.getText().trim();
	            String significance = significanceArea.getText().trim();
	            String tips = tipsArea.getText().trim();
	            String category = (String) categoryBox.getSelectedItem();

	            if (name.isEmpty() || region.isEmpty()) {
	                JOptionPane.showMessageDialog(dialog, "Name and Region are required.",
	                        "Missing Information", JOptionPane.WARNING_MESSAGE);
	                return;
	            }

	            Location newLocation;
	            switch (category) {
	                case "Natural":
	                    newLocation = new NaturalSite(name, region, shortDesc, history, significance, tips);
	                    break;
	                case "Cultural":
	                    newLocation = new CulturalSite(name, region, shortDesc, history, significance, tips);
	                    break;
	                default:
	                    newLocation = new HeritageSite(name, region, shortDesc, history, significance, tips);
	                    break;
	            }

	            manager.addLocation(newLocation);
	            filterList();
	            dialog.dispose();
	        });

	        cancelButton.addActionListener(e -> dialog.dispose());

	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        buttonPanel.add(saveButton);
	        buttonPanel.add(cancelButton);

	        gbc.gridx = 0;
	        gbc.gridy = row;
	        gbc.gridwidth = 2;
	        dialog.add(buttonPanel, gbc);

	        dialog.setVisible(true);
	    }

	    private void addFormRow(JDialog dialog, GridBagConstraints gbc, int row, String label, JComponent field) {
	        gbc.gridx = 0;
	        gbc.gridy = row;
	        gbc.gridwidth = 1;
	        gbc.weightx = 0;
	        dialog.add(new JLabel(label), gbc);

	        gbc.gridx = 1;
	        gbc.weightx = 1;
	        dialog.add(field, gbc);
	    }

	    // Custom renderer so each row shows "Name (Category)"
	    private static class LocationCellRenderer extends DefaultListCellRenderer {
	        @Override
	        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
	                                                        boolean isSelected, boolean cellHasFocus) {
	            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	            if (value instanceof Location) {
	                Location loc = (Location) value;
	                label.setText(loc.getName() + "  (" + loc.getCategory() + ")");
	                label.setBorder(new EmptyBorder(4, 6, 4, 6));
	            }
	            return label;
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        	MainGUI gui = new MainGUI();
	            gui.setVisible(true);
	        });
	    }
	}




