package net.cyanwool.core.entity.metadata;

public class MetadataHelper {

	public static final byte STATUS = 0;
	public static final byte AIR_TIME = 1;
	public static final byte NAME_TAG = 2;
	public static final byte SHOW_NAME_TAG = 3;

	public static final byte HEALTH = 6;
	public static final byte POTION_COLOR = 7;
	public static final byte POTION_AMBIENT = 8;
	public static final byte ARROW_COUNT = 9;

	public static final byte NO_AI = 15;

	public static final byte ARMOR_STAND = 10;
	public static final byte HORSE = 16;

	public class StatusFlags {

		public static final int ON_FIRE = 0x01;
		public static final int SNEAKING = 0x02;
		public static final int SPRINTING = 0x08;
		public static final int ARM_UP = 0x10;
		public static final int INVISIBLE = 0x20;
	}

	public class ArmorFlags {

		public static final int SMALL = 0x01;
		public static final int GRAVITY = 0x02;
		public static final int ARMS = 0x04;
		public static final int BASEPLATE = 0x08;
	}

	public class HorseFlags {

		public static final int TAME = 0x02;
		public static final int SADDLE = 0x04;
		public static final int CHEST = 0x08;
		public static final int BRED = 0x10;
		public static final int EATING = 0x20;
		public static final int REARING = 0x40;
		public static final int MOUTH_OPEN = 0x80;
	}

	public class ColorFlags {

		public static final byte WHITE = (byte) 0;
		public static final byte ORANGE = (byte) 1;
		public static final byte MAGENTA = (byte) 2;
		public static final byte LIGHT_BLUE = (byte) 3;
		public static final byte YELLOW = (byte) 4;
		public static final byte LIME = (byte) 5;
		public static final byte PINK = (byte) 6;
		public static final byte GRAY = (byte) 7;
		public static final byte LIGHT_GRAY = (byte) 8;
		public static final byte CYAN = (byte) 9;
		public static final byte PURPLE = (byte) 10;
		public static final byte BLUE = (byte) 11;
		public static final byte BROWN = (byte) 12;
		public static final byte GREEN = (byte) 13;
		public static final byte RED = (byte) 14;
		public static final byte BLACK = (byte) 15;
	}
}
