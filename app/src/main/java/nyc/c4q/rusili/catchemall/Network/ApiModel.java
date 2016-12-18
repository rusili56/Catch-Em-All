package nyc.c4q.rusili.catchemall.Network;

import java.util.List;

/**
 * Created by rusili on 12/16/16.
 */

public class ApiModel {

    private int id;
    private String name;
    private Sprites sprites;
    private List<Types> types;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public List<Types> getTypes() {
        return types;
    }

    public class Sprites {
        String front_default;
        String back_default;

        public String getFront_default() {
            return front_default;
        }

        public String getBack_default() {
            return back_default;
        }
    }

    public class Types {
        Type type;

        public Type getType() {
            return type;
        }

        public class Type {
            String name;

            public String getName() {
                return name;
            }
        }
    }
}
