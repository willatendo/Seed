package ca.seed.server.attribute;

import ca.seed.server.CreationType;
import ca.seed.server.registry.Registry;

public class Attributes {
    private static final Registry<Attribute> ATTRIBUTES = new Registry<>("attributes");

    public static final Attribute SING = register("sing", new Attribute(1, 5, CreationType.CREATURE));
    public static final Attribute DANCE = register("dance", new Attribute(1, 5, CreationType.CREATURE));
    public static final Attribute CHARM = register("charm", new Attribute(1, 5, CreationType.CREATURE));
    public static final Attribute POSE = register("pose", new Attribute(1, 5, CreationType.CREATURE));

    public static final Attribute BITE = register("bite", new Attribute(1, 5, CreationType.CREATURE));
    public static final Attribute CHARGE = register("charge", new Attribute(1, 5, CreationType.CREATURE));
    public static final Attribute STRIKE = register("strike", new Attribute(1, 5, CreationType.CREATURE));
    public static final Attribute POISON = register("poison", new Attribute(1, 5, CreationType.CREATURE));

    private static Attribute register(String name, Attribute attribute) {
        return ATTRIBUTES.register(name, attribute);
    }

    public static void init() {
    }
}
