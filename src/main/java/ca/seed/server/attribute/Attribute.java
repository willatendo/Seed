package ca.seed.server.attribute;

import ca.seed.server.CreationType;

public record Attribute(int low, int high, CreationType creationType) {
}
