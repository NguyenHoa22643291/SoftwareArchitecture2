package fit.se;

public class File implements FileSystemNote{
    private String name;
    private long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "- File: " + name + " (" + size + " KB)");
    }
}
