package skyxnetwork.customDeathMessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CustomDeathMessage extends JavaPlugin implements Listener {

    private File userdataFile;
    private FileConfiguration userdataConfig;

    private final Map<String, String> deathMessages = new HashMap<>();
    private String pluginPrefix;
    private List<String> badWordsFilter;
    private static final String ANSI_MAGENTA = "\u001B[35m";
    private static final String ANSI_LIGHT_GRAY = "\u001B[37m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_LIGHT_GREEN = "\u001B[92m";
    private static final String ANSI_RED = "\u001B[31m";

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(ANSI_LIGHT_GRAY + "︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹");
        Bukkit.getLogger().info(ANSI_MAGENTA + " _______  ___   _  __   __    __   __    __    _  _______  _______ " + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + "|       ||   | | ||  | |  |  |  |_|  |  |  |  | ||       ||       |" + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + "|  _____||   |_| ||  |_|  |  |       |  |   |_| ||    ___||_     _|" + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + "| |_____ |      _||       |  |       |  |       ||   |___   |   |  " + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + "|_____  ||     |_ |_     _|   |     |   |  _    ||    ___|  |   |  " + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + " _____| ||    _  |  |   |    |   _   |  | | |   ||   |___   |   |  " + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + "|_______||___| |_|  |___|    |__| |__|  |_|  |__||_______|  |___|  " + ANSI_RESET);
        Bukkit.getLogger().info("   ");
        Bukkit.getLogger().info(ANSI_LIGHT_GREEN + "Plugin CustomDeathMessage enabled !");
        Bukkit.getLogger().info(ANSI_LIGHT_GRAY + "︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺");
        // Sauvegarde de la configuration par défaut
        saveDefaultConfig();

        // Chargement des configurations
        pluginPrefix = getConfig().getString("Prefix", "§dSky X §9Network §eDeathMessage §8●⏺ ");
        badWordsFilter = getConfig().getStringList("Filter");

        // Initialisation du fichier userdata
        createUserDataFile();

        // Chargement des messages personnalisés
        loadMessages();

        // Enregistrement des événements
        Bukkit.getPluginManager().registerEvents(this, this);

        getLogger().info(pluginPrefix + "CustomDeathMessage has been enabled.");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ANSI_LIGHT_GRAY + "︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹︹");
        Bukkit.getLogger().info(ANSI_MAGENTA + " _______  ___   _  __   __    __   __    __    _  _______  _______ " + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + "|       ||   | | ||  | |  |  |  |_|  |  |  |  | ||       ||       |" + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + "|  _____||   |_| ||  |_|  |  |       |  |   |_| ||    ___||_     _|" + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + "| |_____ |      _||       |  |       |  |       ||   |___   |   |  " + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + "|_____  ||     |_ |_     _|   |     |   |  _    ||    ___|  |   |  " + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + " _____| ||    _  |  |   |    |   _   |  | | |   ||   |___   |   |  " + ANSI_RESET);
        Bukkit.getLogger().info(ANSI_MAGENTA + "|_______||___| |_|  |___|    |__| |__|  |_|  |__||_______|  |___|  " + ANSI_RESET);
        Bukkit.getLogger().info("   ");
        Bukkit.getLogger().info(ANSI_RED + "  Plugin CustomDeathMessage disabled !");
        Bukkit.getLogger().info(ANSI_LIGHT_GRAY + "︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺︺");
        // Sauvegarde des messages personnalisés
        saveMessages();
        getLogger().info(pluginPrefix + "CustomDeathMessage has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("deathmessage")) {
            if (args.length < 1) {
                sender.sendMessage(pluginPrefix + ChatColor.RED + "Usage: /deathmessage [set/reset/show/reload] [Only for show : playername] [message]");
                return true;
            }

            String action = args[0];

            if ("set".equalsIgnoreCase(action)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(pluginPrefix + ChatColor.RED + "This command can only be used by players.");
                    return true;
                }
                Player player = (Player) sender;

                if (!player.hasPermission("skyxnetwork.deathmessage.set")) {
                    player.sendMessage(pluginPrefix + ChatColor.RED + "You do not have permission to set a custom death message.");
                    return true;
                }

                if (args.length < 2) {
                    player.sendMessage(pluginPrefix + ChatColor.RED + "Usage: /deathmessage set [message]");
                    return true;
                }

                String message = String.join(" ", args).substring(action.length() + 1);

                // Validation : longueur maximale
                if (message.length() > 30) {
                    player.sendMessage(pluginPrefix + ChatColor.RED + "Your custom death message cannot exceed 30 characters!");
                    return true;
                }
                if (containsUnicode(message)) {
                    player.sendMessage(pluginPrefix + ChatColor.RED + "You can't set Unicode characters in your custom message!");
                    return true;
                }
                if (containsProhibitedFormatting(message)) {
                    player.sendMessage(pluginPrefix + ChatColor.RED + "You can't use prohibited formatting codes (&k, &o, &l, &n, &m) in your custom message!");
                    return true;
                }
                // Validation : caractères autorisés uniquement
                if (!isMessageValid(message)) {
                    player.sendMessage(pluginPrefix + ChatColor.RED + "Your message can only contain letters, numbers, spaces, and common symbols.");
                    return true;
                }

                // Validation : mots interdits
                if (containsBadWords(message)) {
                    player.sendMessage(pluginPrefix + ChatColor.RED + "Your message contains inappropriate language and cannot be set!");
                    return true;
                }

                // Enregistrement du message
                deathMessages.put(player.getName(), message);
                userdataConfig.set("deathMessages." + player.getName(), message);
                saveUserDataFile();

                // Prévisualisation du message
                String preview = ChatColor.translateAlternateColorCodes('&',
                        "&7" + player.getName() + " " + message);
                player.sendMessage(pluginPrefix + ChatColor.GREEN + "Custom death message set!\n" + ChatColor.RESET + preview);

            } else if ("reset".equalsIgnoreCase(action)) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(pluginPrefix + ChatColor.RED + "This command can only be used by players.");
                    return true;
                }
                Player player = (Player) sender;

                if (!player.hasPermission("skyxnetwork.deathmessage.reset")) {
                    player.sendMessage(pluginPrefix + ChatColor.RED + "You do not have permission to reset your custom death message.");
                    return true;
                }

                // Suppression du message personnalisé
                deathMessages.remove(player.getName());
                userdataConfig.set("deathMessages." + player.getName(), null);
                saveUserDataFile();

                player.sendMessage(pluginPrefix + ChatColor.GREEN + "Custom death message reset!");

            } else if ("show".equalsIgnoreCase(action)) {
                if (!sender.hasPermission("skyxnetwork.deathmessage.show")) {
                    sender.sendMessage(pluginPrefix + ChatColor.RED + "You do not have permission to view custom death messages.");
                    return true;
                }

                if (args.length < 2) {
                    sender.sendMessage(pluginPrefix + ChatColor.RED + "Usage: /dm show {playername}");
                    return true;
                }

                String targetName = args[1];
                String deathMessage = userdataConfig.getString("deathMessages." + targetName);

                if (deathMessage != null) {
                    sender.sendMessage(pluginPrefix + ChatColor.YELLOW + "Custom death message for " + targetName + ":");
                    sender.sendMessage(ChatColor.GRAY + targetName + " " + ChatColor.RESET + deathMessage);
                } else {
                    sender.sendMessage(pluginPrefix + ChatColor.RED + "No custom death message set for " + targetName + ".");
                }
                return true;

            } else if ("reload".equalsIgnoreCase(action)) {
                if (!sender.hasPermission("skyxnetwork.deathmessage.reload")) {
                    sender.sendMessage(pluginPrefix + ChatColor.RED + "You do not have permission to reload the configuration.");
                    return true;
                }

                // Recharger la configuration et les données utilisateur
                reloadConfig();
                pluginPrefix = getConfig().getString("Prefix", "§dSky X §9Network §eDeathMessage §8●⏺ ");
                badWordsFilter = getConfig().getStringList("Filter");
                userdataConfig = YamlConfiguration.loadConfiguration(userdataFile);
                loadMessages();

                sender.sendMessage(pluginPrefix + ChatColor.GREEN + "Configuration and userdata successfully reloaded!");
            } else {
                sender.sendMessage(pluginPrefix + ChatColor.RED + "Invalid action. Usage: /deathmessage [set/reset/reload]");
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        String customMessage = deathMessages.get(player.getName());

        if (customMessage != null) {
            // Formater le message avec le nom du joueur en gris
            String formattedMessage = ChatColor.translateAlternateColorCodes('&',
                    "&7" + player.getName() + " " + customMessage);
            event.setDeathMessage(formattedMessage);
        } else {
            // Si aucun message personnalisé, laisser un message par défaut
            event.setDeathMessage("&7" + player.getName() + " died.");
        }
    }

    private void createUserDataFile() {
        userdataFile = new File(getDataFolder(), "userdata.yml");
        if (!userdataFile.exists()) {
            userdataFile.getParentFile().mkdirs();
            saveResource("userdata.yml", false);
        }
        userdataConfig = YamlConfiguration.loadConfiguration(userdataFile);
    }

    private void saveUserDataFile() {
        try {
            userdataConfig.save(userdataFile);
        } catch (IOException e) {
            getLogger().severe("Could not save userdata.yml!");
            e.printStackTrace();
        }
    }

    private void loadMessages() {
        if (userdataConfig.contains("deathMessages")) {
            userdataConfig.getConfigurationSection("deathMessages").getKeys(false).forEach(key ->
                    deathMessages.put(key, userdataConfig.getString("deathMessages." + key)));
        }
    }

    private void saveMessages() {
        userdataConfig.set("deathMessages", deathMessages);
        saveUserDataFile();
    }

    private boolean containsUnicode(String message) {
        for (char c : message.toCharArray()) {
            if (c > 127) {
                return true;
            }
        }
        return false;
    }

    private boolean isMessageValid(String message) {
        return message.matches("^[a-zA-Z0-9 .,!?\"'()@#$%^&*_-]+$");
    }

    private boolean containsProhibitedFormatting(String message) {
        return message.contains("&k") || message.contains("&o") || message.contains("&l") || message.contains("&n") || message.contains("&m");
    }

    private boolean containsBadWords(String message) {
        for (String badWord : badWordsFilter) {
            if (message.toLowerCase().contains(badWord.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
