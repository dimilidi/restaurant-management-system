package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.Articles;

public class Article {
    private String title;
    private String content;
    private String author;

    public Article (String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String edit (String newContent) {
        content = newContent;
        return newContent;
    }
    public String changeAuthor (String newAuthor) {
        author = newAuthor;
        return newAuthor;
    }
    public String rename (String newTitle) {
        title = newTitle;
        return  newTitle;
    }

    @Override
    public String toString () {
        return String.format("%s - %s: %s", title, content, author);
    }

}
