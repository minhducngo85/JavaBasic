package reflection;

public class MyOuterClass {

    public final static int TYPE_ONE = 1;

    public final static int TYPE_TWO = 2;

    private int id = 0;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public static class StaticInnerClass {
        public final static int STATIC_FIELD_1 = 1;
        public final static int STATIC_FIELD_2 = 2;
        public final static int STATIC_FIELD_3 = 3;
    }

    public class InnerClass {
        public final static int STATIC_FIELD_4 = 4;
        public final static int STATIC_FIELD_5 = 5;
        public final static int STATIC_FIELD_6 = 6;
    }
}
