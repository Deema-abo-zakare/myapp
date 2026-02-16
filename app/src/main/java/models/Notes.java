package models;

public class Notes {
    private int id;
    private String name;
    private int image;
    public static final String TABLE_NAME ="Note";
    // static  تعني بمجرد ما تعمل run يروح يحفظ مكان بالذاكرة باسم الجدول
    // the colums
    public static  final  String  ID ="id";
    public static final String NAME ="name";
    public static final  String IMAGE="image";

    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+
            "("+ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +NAME+ " TEXT NOT NULL,"
            +IMAGE+" INTEGER" +")";

    public Notes(int id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Notes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
