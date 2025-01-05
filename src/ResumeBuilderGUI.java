import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ResumeBuilderGUI extends JFrame {

    // Form fields
    private JTextField nameField, emailField, phoneField, ageField, countryField, internshipsField, languagesField, skillsField, programmingLanguagesField, languagesSpokenField;
    private JTextField tenthMarksField, tenthInstituteField, tenthYearField;
    private JTextField twelfthGpaField, twelfthInstituteField, twelfthYearField;
    private JTextField btechCgpaField, btechInstituteField, btechYearField;
    private JPanel experiencePanel;
    private List<ExperienceFields> experienceFieldsList;
    private JComboBox<String> bloodGroupComboBox, maritalStatusComboBox;
    private JLabel photoLabel;
    private BufferedImage photo;
    private JTextField objectiveField;
    private JTextField dateOfBirthField;

    public ResumeBuilderGUI() {
        // Frame settings
        setTitle("Resume Builder");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set white background color
        getContentPane().setBackground(Color.WHITE);

        // Initialize form fields
        experienceFieldsList = new ArrayList<>();

        // Create form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());
        formPanel.setBackground(Color.WHITE);

        // Create top panel for photo
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        // Photo
        photoLabel = createLabel("Photo:");
        topPanel.add(photoLabel, BorderLayout.WEST);
        JButton uploadPhotoButton = new JButton("Upload Photo");
        uploadPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        photo = ImageIO.read(selectedFile);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error uploading photo: " + ex.getMessage());
                    }
                }
            }
        });
        topPanel.add(uploadPhotoButton, BorderLayout.EAST);

        // Create center panel for form fields
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        // Customizing labels, text fields, and buttons
        JLabel mainHeadingLabel = createHeadingLabel("Resume");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(mainHeadingLabel, gbc);

        JLabel objectiveLabel = createLabel("Objective:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(objectiveLabel, gbc);
        objectiveField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(objectiveField, gbc);

        JLabel personalDetailsLabel = createHeadingLabel("Personal Details");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(personalDetailsLabel, gbc);

        JLabel nameLabel = createLabel("Full Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(nameLabel, gbc);
        nameField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(nameField, gbc);

        JLabel emailLabel = createLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(emailLabel, gbc);
        emailField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(emailField, gbc);

        JLabel phoneLabel = createLabel("Phone (+91):");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(phoneLabel, gbc);
        phoneField = createTextField();
        phoneField.setText("+91");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(phoneField, gbc);

        JLabel ageLabel = createLabel("Age (in years):");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(ageLabel, gbc);
        ageField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(ageField, gbc);

        JLabel dateOfBirthLabel = createLabel("Date of Birth (DD/MM/YYYY):");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(dateOfBirthLabel, gbc);
        dateOfBirthField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(dateOfBirthField, gbc);

        JLabel countryLabel = createLabel("Preferred Job Country:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(countryLabel, gbc);
        countryField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(countryField, gbc);

        JLabel bloodGroupLabel = createLabel("Blood Group:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(bloodGroupLabel, gbc);
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-", "Other", "Unknown"};
        bloodGroupComboBox = new JComboBox<>(bloodGroups);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(bloodGroupComboBox, gbc);

        JLabel maritalStatusLabel = createLabel("Marital Status:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(maritalStatusLabel, gbc);
        String[] maritalStatuses = {"Single", "Married", "Divorced", "Widowed"};
        maritalStatusComboBox = new JComboBox<>(maritalStatuses);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(maritalStatusComboBox, gbc);

        JLabel languagesSpokenLabel = createLabel("Languages Spoken:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(languagesSpokenLabel, gbc);
        languagesSpokenField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(languagesSpokenField, gbc);

        JLabel eduLabel = createHeadingLabel("Educational Details");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(eduLabel, gbc);

        JLabel tenthMarksLabel = createLabel("10th Marks (%):");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(tenthMarksLabel, gbc);
        tenthMarksField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(tenthMarksField, gbc);

        JLabel tenthInstituteLabel = createLabel("Institute Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(tenthInstituteLabel, gbc);
        tenthInstituteField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(tenthInstituteField, gbc);

        JLabel tenthYearLabel = createLabel("Year of Passing:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(tenthYearLabel, gbc);
        tenthYearField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 15;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(tenthYearField, gbc);

        JLabel twelfthGpaLabel = createLabel("12th GPA:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(twelfthGpaLabel, gbc);
        twelfthGpaField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 16;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(twelfthGpaField, gbc);

        JLabel twelfthInstituteLabel = createLabel("Institute Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 17;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(twelfthInstituteLabel, gbc);
        twelfthInstituteField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 17;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(twelfthInstituteField, gbc);

        JLabel twelfthYearLabel = createLabel("Year of Passing:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 18;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(twelfthYearLabel, gbc);
        twelfthYearField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 18;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(twelfthYearField, gbc);

        JLabel btechCgpaLabel = createLabel("BTech CGPA:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 19;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(btechCgpaLabel, gbc);
        btechCgpaField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 19;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btechCgpaField, gbc);

        JLabel btechInstituteLabel = createLabel("Institute Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(btechInstituteLabel, gbc);
        btechInstituteField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 20;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btechInstituteField, gbc);

        JLabel btechYearLabel = createLabel("Year of Passing:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 21;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(btechYearLabel, gbc);
        btechYearField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 21;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btechYearField, gbc);

        JLabel internshipsLabel = createLabel("Internships:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 22;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(internshipsLabel, gbc);
        internshipsField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 22;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(internshipsField, gbc);

        JLabel languagesLabel = createLabel("Languages:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 23;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(languagesLabel, gbc);
        languagesField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 23;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(languagesField, gbc);

        JLabel programmingLanguagesLabel = createLabel("Programming Languages:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 24;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(programmingLanguagesLabel, gbc);
        programmingLanguagesField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 24;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(programmingLanguagesField, gbc);

        JLabel skillsLabel = createLabel("Skills:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 25;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(skillsLabel, gbc);
        skillsField = createTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 25;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(skillsField, gbc);

        JLabel experienceLabel = createHeadingLabel("Experience");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 26;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(experienceLabel, gbc);

        experiencePanel = new JPanel();
        experiencePanel.setLayout(new BorderLayout());
        experiencePanel.setBackground(Color.WHITE);

        JButton addExperienceButton = new JButton("Add Experience");
        addExperienceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExperienceFields fields = new ExperienceFields();
                experiencePanel.add(fields.getPanel());
                experienceFieldsList.add(fields);
                revalidate();
                repaint();
            }
        });
        experiencePanel.add(addExperienceButton, BorderLayout.NORTH);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 27;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(experiencePanel, gbc);

        // Create a JScrollPane to wrap the center panel
        JScrollPane centerScrollPane = new JScrollPane(centerPanel);
        centerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        centerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        centerScrollPane.setPreferredSize(new Dimension(700, 500));

        // Add the JScrollPane to the form panel
        formPanel.add(centerScrollPane, BorderLayout.CENTER);

        // Create bottom panel for buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.WHITE);

        JButton generateResumeButton = new JButton("Generate Resume");
        generateResumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateResume();
            }
        });
        bottomPanel.add(generateResumeButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
        bottomPanel.add(resetButton);

        // Add panels to frame
        formPanel.add(topPanel, BorderLayout.NORTH);
        formPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.CENTER);

        // Center frame on screen
        setLocationRelativeTo(null);

        // Make frame visible
        setVisible(true);
    }

    // Method to create a label
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    // Method to create a heading label
    private JLabel createHeadingLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }

    // Method to create a text field with a fixed size
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25)); // Fixed size
        return textField;
    }

    // Method to save resume to the database
    private void saveResumeToDatabase(String name, String email, String phone, String age, String country,
                                      String dateOfBirth, String bloodGroup, String maritalStatus,
                                      String objective, String internships, String languages,
                                      String programmingLanguages, String skills, String tenthMarks,
                                      String tenthInstitute, String tenthYear, String twelfthGpa,
                                      String twelfthInstitute, String twelfthYear, String btechCgpa,
                                      String btechInstitute, String btechYear, String languagesSpoken) {
        // Update with your existing database connection details
        String url = "jdbc:mysql://localhost:3306/resume"; // Replace with your database name
        String user = "root"; // Your MySQL username
        String password = "4801"; // Your MySQL password

        // SQL query to insert data into the existing table
        String sql = "INSERT INTO resumes (name, email, phone, age, country, date_of_birth, blood_group, " +
                "marital_status, objective, internships, languages, programming_languages, skills, " +
                "tenth_marks, tenth_institute, tenth_year, twelfth_gpa, twelfth_institute, " +
                "twelfth_year, btech_cgpa, btech_institute, btech_year, languages_spoken) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setInt(4, Integer.parseInt(age));
            pstmt.setString(5, country);
            pstmt.setDate(6, java.sql.Date.valueOf(dateOfBirth)); // Ensure date format is correct
            pstmt.setString(7, bloodGroup);
            pstmt.setString(8, maritalStatus);
            pstmt.setString(9, objective);
            pstmt.setString(10, internships);
            pstmt.setString(11, languages);
            pstmt.setString(12, programmingLanguages);
            pstmt.setString(13, skills);
            pstmt.setBigDecimal(14, new BigDecimal(tenthMarks));
            pstmt.setString(15, tenthInstitute);
            pstmt.setInt(16, Integer.parseInt(tenthYear));
            pstmt.setBigDecimal(17, new BigDecimal(twelfthGpa));
            pstmt.setString(18, twelfthInstitute);
            pstmt.setInt(19, Integer.parseInt(twelfthYear));
            pstmt.setBigDecimal(20, new BigDecimal(btechCgpa));
            pstmt.setString(21, btechInstitute);
            pstmt.setInt(22, Integer.parseInt(btechYear));
            pstmt.setString(23, languagesSpoken);

            pstmt.executeUpdate(); // Execute the insert operation
            JOptionPane.showMessageDialog(null, "Resume saved to database successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saving resume to database: " + e.getMessage());
        }
    }

    // Method to generate the resume
    private void generateResume() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String age = ageField.getText();
        String country = countryField.getText();
        String tenthMarks = tenthMarksField.getText();
        String tenthInstitute = tenthInstituteField.getText();
        String tenthYear = tenthYearField.getText();
        String twelfthGpa = twelfthGpaField.getText();
        String twelfthInstitute = twelfthInstituteField.getText();
        String twelfthYear = twelfthYearField.getText();
        String btechCgpa = btechCgpaField.getText();
        String btechInstitute = btechInstituteField.getText();
        String btechYear = btechYearField.getText();
        String internships = internshipsField.getText();
        String languages = languagesField.getText();
        String programmingLanguages = programmingLanguagesField.getText();
        String skills = skillsField.getText();
        String bloodGroup = (String) bloodGroupComboBox.getSelectedItem();
        String maritalStatus = (String) maritalStatusComboBox.getSelectedItem();
        String objective = objectiveField.getText();
        String dateOfBirth = dateOfBirthField.getText();
        String languagesSpoken = languagesSpokenField.getText();

        // Save resume to database
        saveResumeToDatabase(name, email, phone, age, country, dateOfBirth, bloodGroup, maritalStatus,
                objective, internships, languages, programmingLanguages, skills,
                tenthMarks, tenthInstitute, tenthYear, twelfthGpa, twelfthInstitute,
                twelfthYear, btechCgpa, btechInstitute, btechYear, languagesSpoken);

        // Create a new window to display the resume
        JFrame resumeFrame = new JFrame("Generated Resume");
        resumeFrame.setSize(600, 800);
        resumeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Build the resume content with HTML
        StringBuilder resumeContent = new StringBuilder();
        resumeContent.append("<html><body style='font-family: Arial, sans-serif;'>");
        resumeContent.append("<h1 style='text-align: center;'>Resume</h1>");

        // Photo
        if (photo != null) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(photo, "png", bos);
                byte[] bytes = bos.toByteArray();
                String encodedPhoto = Base64.getEncoder().encodeToString(bytes);
                resumeContent.append("<table style='width: 100%;'><tr><td style='width: 80%; text-align: left;'>");
                resumeContent.append("<h2>Personal Details</h2>");
                resumeContent.append("<p>Name: ").append(name).append("</p>");
                resumeContent.append("<p>Email: ").append(email).append("</p>");
                resumeContent.append("<p>Phone: ").append(phone).append("</p>");
                resumeContent.append("<p>Age: ").append(age).append("</p>");
                resumeContent.append("<p>Country: ").append(country).append("</p>");
                resumeContent.append("<p>Date of Birth: ").append(dateOfBirth).append("</p>");
                resumeContent.append("<p>Blood Group: ").append(bloodGroup).append("</p>");
                resumeContent.append("<p>Marital Status: ").append(maritalStatus).append("</p>");
                resumeContent.append("<p>Languages Spoken: ").append(languagesSpoken).append("</p>");
                resumeContent.append("</td><td style='width: 20%; text-align: right;'>");
                resumeContent.append("<img src='data:image/png;base64,").append(encodedPhoto).append("' width='150' height='150'/>");
                resumeContent.append("</td></tr></table>");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error encoding photo: " + ex.getMessage());
            }
        } else {
            resumeContent.append("<h2>Personal Details</h2>");
            resumeContent.append("<p>Name: ").append(name).append("</p>");
            resumeContent.append("<p>Email: ").append(email).append("</p>");
            resumeContent.append("<p>Phone: ").append(phone).append("</p>");
            resumeContent.append("<p>Age: ").append(age).append("</p>");
            resumeContent.append("<p>Country: ").append(country).append("</p>");
            resumeContent.append("<p>Date of Birth: ").append(dateOfBirth).append("</p>");
            resumeContent.append("<p>Blood Group: ").append(bloodGroup).append("</p>");
            resumeContent.append("<p>Marital Status: ").append(maritalStatus).append("</p>");
            resumeContent.append("<p>Languages Spoken: ").append(languagesSpoken).append("</p>");
        }

        // Objective
        resumeContent.append("<h2>Objective</h2>");
        resumeContent.append("<p>").append(objective).append("</p>");

        // Educational Details
        resumeContent.append("<h2>Educational Details</h2>");
        resumeContent.append("<table border='1' cellpadding='5' cellspacing='0'>");
        resumeContent.append("<tr><th>Qualification</th><th>Institute</th><th>Year of Passing</th><th>Marks/CGPA</th></tr>");
        resumeContent.append("<tr><td>10th</td><td>").append(tenthInstitute).append("</td><td>").append(tenthYear).append("</td><td>").append(tenthMarks).append("%</td></tr>");
        resumeContent.append("<tr><td>12th</td><td>").append(twelfthInstitute).append("</td><td>").append(twelfthYear).append("</td><td>").append(twelfthGpa).append("</td></tr>");
        resumeContent.append("<tr><td>BTech</td><td>").append(btechInstitute).append("</td><td>").append(btechYear).append("</td><td>").append(btechCgpa).append("</td></tr>");
        resumeContent.append("</table>");

        // Internships
        resumeContent.append("<h2>Internships</h2>");
        resumeContent.append("<p>").append(internships).append("</p>");

        // Languages
        resumeContent.append("<h2>Languages</h2>");
        resumeContent.append("<p>").append(languages).append("</p>");

        // Programming Languages
        resumeContent.append("<h2>Programming Languages</h2>");
        resumeContent.append("<p>").append(programmingLanguages).append("</p>");

        // Skills
        resumeContent.append("<h2>Skills</h2>");
        resumeContent.append("<p>").append(skills).append("</p>");

        // Experience
        resumeContent.append("<h2>Experience</h2>");
        for (ExperienceFields fields : experienceFieldsList) {
            resumeContent.append("<p>Company: ").append(fields.companyField.getText()).append("</p>");
            resumeContent.append("<p>Role: ").append(fields.roleField.getText()).append("</p>");
            resumeContent.append("<p>Duration: ").append(fields.durationField.getText()).append("</p>");
            resumeContent.append("<p>Location: ").append(fields.locationField.getText()).append("</p>");
        }

        resumeContent.append("</body></html>");

        // Create a JLabel to display the resume content
        JLabel resumeLabel = new JLabel(resumeContent.toString());
        resumeLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Create a panel to hold the resume label and download button
        JPanel resumePanel = new JPanel();
        resumePanel.setLayout(new BorderLayout());
        resumePanel.add(resumeLabel, BorderLayout.CENTER);

        // Create a download button
        JButton downloadButton = new JButton("Download Resume");
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Resume");
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        FileWriter writer = new FileWriter(selectedFile);
                        writer.write(resumeContent.toString());
                        writer.close();
                        JOptionPane.showMessageDialog(null, "Resume saved successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error saving resume: " + ex.getMessage());
                    }
                }
            }
        });
        resumePanel.add(downloadButton, BorderLayout.SOUTH);

        // Create a JScrollPane to wrap the resume panel
        JScrollPane resumeScrollPane = new JScrollPane(resumePanel);
        resumeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resumeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        resumeScrollPane.setPreferredSize(new Dimension(500, 700));

        // Add the JScrollPane to the resume frame
        resumeFrame.getContentPane().add(resumeScrollPane);

        // Center the resume frame on screen
        resumeFrame.setLocationRelativeTo(null);

        // Make the resume frame visible
        resumeFrame.setVisible(true);
    }

    // Method to reset the form
    private void resetForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        ageField.setText("");
        countryField.setText("");
        tenthMarksField.setText("");
        tenthInstituteField.setText("");
        tenthYearField.setText("");
        twelfthGpaField.setText("");
        twelfthInstituteField.setText("");
        twelfthYearField.setText("");
        btechCgpaField.setText("");
        btechInstituteField.setText("");
        btechYearField.setText("");
        internshipsField.setText("");
        languagesField.setText("");
        programmingLanguagesField.setText("");
        skillsField.setText("");
        bloodGroupComboBox.setSelectedIndex(0);
        maritalStatusComboBox.setSelectedIndex(0);
        objectiveField.setText("");
        dateOfBirthField.setText("");
        languagesSpokenField.setText("");
        experienceFieldsList.clear();
        experiencePanel.removeAll();
        revalidate();
        repaint();
    }

    // Class to represent experience fields
    private class ExperienceFields {
        private JTextField companyField, roleField, durationField, locationField;

        public ExperienceFields() {
            companyField = createTextField();
            roleField = createTextField();
            durationField = createTextField();
            locationField = createTextField();
        }

        public JPanel getPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 2));
            panel.add(new JLabel("Company:"));
            panel.add(companyField);
            panel.add(new JLabel("Role:"));
            panel.add(roleField);
            panel.add(new JLabel("Duration:"));
            panel.add(durationField);
            panel.add(new JLabel("Location:"));
            panel.add(locationField);
            return panel;
        }
    }


    public static void main(String[] args) {
        new ResumeBuilderGUI();
    }
}