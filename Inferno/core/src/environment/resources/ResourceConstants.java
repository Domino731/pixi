package environment.resources;

import utils.vectors.DimensionVector;

public class ResourceConstants {
    public static final DimensionVector<Byte> res16Dim = new DimensionVector<>((byte) 16, (byte) 16);
    public static final DimensionVector<Byte> res32Dim = new DimensionVector<>((byte) 32, (byte) 32);
    public static final byte bigMineSize = 32;
    public static final byte normalMineSize = 16;

    public static final String RES_CONFIG_SRC = "config/mines.json";

}
