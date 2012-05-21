package net.worldoftomorrow.nala.ni;

import net.milkbowl.vault.item.ItemInfo;
import net.milkbowl.vault.item.Items;

public class StringHelper
{
  public static String replaceVars(String msg, String n, String w, int ix, int iy, int iz, int iid)
  {
    String x = Integer.toString(ix);
    String y = Integer.toString(iy);
    String z = Integer.toString(iz);
    msg = msg.replace("%n", n);
    msg = msg.replace("%w", w);
    msg = msg.replace("%x", x);
    msg = msg.replace("%y", y);
    msg = msg.replace("%z", z);
    if(Tools.getTool(iid) != null){
    	msg = msg.replace("%i", Tools.getTool(iid).getRealName());
    }
    else if(Armour.getArmour(iid) != null){
    	msg = msg.replace("%i", Armour.getArmour(iid).getRealName());
    } else if(VaultPerms.useVault){
    	ItemInfo info = Items.itemById(iid);
    	msg = msg.replace("%i", info.getName());
    } else {
        String id = Integer.toString(iid);
    	msg = msg.replace("%i", id);
    }
    return msg;
  }
  
  public static String replaceVars(String msg, String n, String w, int ix, int iy, int iz, String recipe)
  {
    String x = Integer.toString(ix);
    String y = Integer.toString(iy);
    String z = Integer.toString(iz);
    msg = msg.replace("%n", n);
    msg = msg.replace("%w", w);
    msg = msg.replace("%x", x);
    msg = msg.replace("%y", y);
    msg = msg.replace("%z", z);
    msg = msg.replace("%i", recipe);
    return msg;
  }
}