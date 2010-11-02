import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;

public class s3HealthPlugin extends Plugin
{
	private String name = "s3HealthPlugin";
	private String version = "1.14";
	
	
	static boolean pvp = false;
	static boolean dropinventory = true;
	static int Combattimer = 700;
	static int woodensword = 6;
	static int stonesword = 7;
	static int ironsword = 8;
	static int goldsword = 10;
	static int diamondsword = 20;
	static int woodenspade = 4;
	static int stonespade = 5;
	static int ironspade = 6;
	static int goldspade = 8;
	static int diamondspade = 10;
	static int woodenpickaxe = 4;
	static int stonepickaxe = 5;
	static int ironpickaxe = 6;
	static int goldpickaxe = 8;
	static int diamondpickaxe = 10;
	static int woodenaxe = 5;
	static int stoneaxe = 6;
	static int ironaxe = 7;
	static int goldaxe = 10;
	static int diamondaxe = 18;
	static int basedamage = 3;
	
	private PropertiesFile properties = new PropertiesFile("s3HealthPlugin.properties");
	    
	static final Logger log = Logger.getLogger("Minecraft");
	static s3HealthListener listener;
	
	public void enable()
	{
		log.info(this.name + " " + this.version + " enabled");
		etc.getInstance().addCommand("/health", "- Shows your current health.");
		etc.getInstance().addCommand("/hp", "- Shows your current health.");
		etc.getInstance().addCommand("/pvpenable", "- Enable PVP");
		etc.getInstance().addCommand("/pvpdisable", "- Disable PVP");
		etc.getInstance().addCommand("/heal", "- Sets you to full health");
		etc.getInstance().addCommand("/enablecombatlog", "- Enables your combat log");
		etc.getInstance().addCommand("/disablecombatlog", "- Disables your combat log");
		loadProperties();
		listener = new s3HealthListener();
		log.info(this.name + " " + this.version + " loaded");
	}
	
	public void loadProperties()
    {
		properties = new PropertiesFile("s3HealthPlugin.properties");
		properties.load();
		try {
			pvp = properties.getBoolean("pvp", false);
			dropinventory = properties.getBoolean("drop-inventory", true);
			Combattimer = properties.getInt("combat-timer", 700);
			woodensword = properties.getInt("wooden-sword", 6);
			stonesword = properties.getInt("stone-sword", 7);
			ironsword = properties.getInt("iron-sword", 8);
			goldsword = properties.getInt("gold-sword", 10);
			diamondsword = properties.getInt("diamond-sword", 20);
			woodenspade = properties.getInt("wooden-spade", 4);
			stonespade = properties.getInt("stone-spade", 5);
			ironspade = properties.getInt("iron-spade", 6);
			goldspade = properties.getInt("gold-spade", 8);
			diamondspade = properties.getInt("diamond-spade", 10);
			woodenpickaxe = properties.getInt("wooden-pickaxe", 4);
			stonepickaxe = properties.getInt("stone-pickaxe", 5);
			ironpickaxe = properties.getInt("iron-pickaxe", 6);
			goldpickaxe = properties.getInt("gold-pickaxe", 8);
			diamondpickaxe = properties.getInt("diamond-pickaxe", 10);
			woodenaxe = properties.getInt("wooden-axe", 5);
			stoneaxe = properties.getInt("stone-axe", 6);
			ironaxe = properties.getInt("iron-axe", 7);
			goldaxe = properties.getInt("gold-axe", 10);
			diamondaxe = properties.getInt("diamond-axe", 18);
			log.info(this.name + " " + this.version + " - Properties Loader: pvp=" +this.pvp);
			log.info(this.name + " " + this.version + " - Properties Loader: drop inventory=" + this.dropinventory);
			log.info(this.name + " " + this.version + " - Properties Loader: combat timer=" + this.Combattimer);

		} catch (Exception e) {

		}
		// TODO : non-existant file
		//System.out.println(getDateTime() + " [DEBUG] s3HealthPlugin - Properties Loader: s3healthplugin.properties NOT FOUND!");
		
	}

	public void disable()
	{
		etc.getInstance().removeCommand("/health");
		etc.getInstance().removeCommand("/pvpenable");
		etc.getInstance().removeCommand("/pvpdisable");
		etc.getInstance().removeCommand("/heal");
		etc.getInstance().removeCommand("/enablecombatlog");
		etc.getInstance().removeCommand("/disablecombatlog");
		log.info(this.name + " " + this.version + " disabled");
	}

	public void initialize() 
	{
		etc.getLoader().addListener(PluginLoader.Hook.ARM_SWING, listener, this, PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener(PluginLoader.Hook.LOGIN, listener, this, PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener(PluginLoader.Hook.COMMAND, listener, this, PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener(PluginLoader.Hook.BLOCK_CREATED, listener, this, PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener(PluginLoader.Hook.DISCONNECT, listener, this, PluginListener.Priority.MEDIUM);
	}
}
