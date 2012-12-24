package net.worldoftomorrow.noitem.permissions;

public class Perm {
	public static final String ADMIN = "noitem.admin";
	public static final String CMD_RELOAD = "noitem.command.reload"; //
	public static final String CMD_CHECK = "noitem.command.check"; //
	public static final String ALLITEMS = "noitem.allitems"; //
	public static final String ONDEATH = "noitem.ondeath.keep";

	public static final String CRAFT = "noitem.nocraft.";
	public static final String PICKUP = "noitem.nopickup.";
	public static final String DROP = "noitem.nodrop.";
	public static final String BREW = "noitem.nobrew.";
	public static final String INTERACT = "noitem.nointeract.";
	public static final String INTERACT_L = "noitem.nointeract.left.";
	public static final String INTERACT_R = "noitem.nointeract.right.";
	public static final String HOLD = "noitem.nohold.";
	public static final String WEAR = "noitem.nowear.";
	public static final String COOK = "noitem.nocook.";
	public static final String PLACE = "noitem.noplace.";
	public static final String BREAK = "noitem.nobreak.";
	// Not actually needed because nointeract.right achieves the same thing
	@Deprecated
	public static final String OPEN = "noitem.noopen.";
	public static final String HAVE = "noitem.nohave.";
	public static final String ENCHANT = "noitem.noenchant.";
}
