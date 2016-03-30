package ro.techstars.chatelectronica;

/**
 * Created by horiaacalin on 29/03/16.
 */
public class ChatMessage {
    private String name;
    private String text;

    public ChatMessage() {

    }
    public ChatMessage(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
