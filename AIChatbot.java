import javax.swing.*;
import java.awt.*;
import java.util.*;
// Explicitly import the Swing Timer to resolve the ambiguity
import javax.swing.Timer; 

public class AIChatbot extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private Map<String, String> knowledgeBase;

    public AIChatbot() {
        knowledgeBase = new HashMap<>();
        seedData();

        // UI Setup
        setTitle("AI Assistant - Java Edition");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setBackground(new Color(245, 245, 245));
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        sendButton.addActionListener(e -> processMessage());
        inputField.addActionListener(e -> processMessage());

        appendMessage("Bot: Hello! I am your AI assistant. How can I help you today?");
    }

    private void seedData() {
        knowledgeBase.put("hello", "Greetings! How can I assist you?");
        knowledgeBase.put("hi", "Hi there! What's on your mind?");
        knowledgeBase.put("name", "I am a Java-based AI Chatbot.");
        knowledgeBase.put("java", "Java is a powerful object-oriented language!");
        knowledgeBase.put("bye", "Goodbye! Have a great day!");
    }

    private void processMessage() {
        String userText = inputField.getText().trim().toLowerCase();
        if (userText.isEmpty()) return;

        appendMessage("You: " + userText);
        inputField.setText("");

        // Logic to find response
        String response = getResponse(userText);
        
        // FIXED LINE: Explicitly using javax.swing.Timer
        Timer timer = new Timer(500, e -> appendMessage("Bot: " + response));
        timer.setRepeats(false);
        timer.start();
    }

    private String getResponse(String input) {
        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) return knowledgeBase.get(key);
        }
        return "I'm sorry, I don't understand that yet.";
    }

    private void appendMessage(String msg) {
        chatArea.append(msg + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AIChatbot().setVisible(true);
        });
    }
}